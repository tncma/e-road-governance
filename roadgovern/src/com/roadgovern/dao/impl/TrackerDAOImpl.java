package com.roadgovern.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.roadgovern.dao.BaseDAO;
import com.roadgovern.dao.TrackerDAO;
import com.roadgovern.vo.AttachmentVO;
import com.roadgovern.vo.ComplaintVO;
import com.roadgovern.vo.FilterVO;
import com.roadgovern.vo.IssueTypeVO;
import com.roadgovern.vo.ItemLogVO;
import com.roadgovern.vo.ItemVO;
import com.roadgovern.vo.StatusVO;
import com.roadgovern.vo.UserVO;
import com.roadgovern.hbm.ItemAttachments;
import com.roadgovern.hbm.Items;
import com.roadgovern.hbm.ItemLog;
import com.roadgovern.hbm.Status;

public class TrackerDAOImpl extends BaseDAO implements TrackerDAO {

	@Override
	public List<ComplaintVO> getDashBoard(UserVO userVO) {
		
		List<ComplaintVO> itemsList	=	new ArrayList<ComplaintVO>();
		StringBuffer searchQuery	=	new StringBuffer();
		try{
			searchQuery.append(RETRIEVE_ITEMS);
			if(userVO.getUserRole().equals("OFFICER")||userVO.getUserRole().equals("FIELD_OFFICER")){
				searchQuery.append(" AND item_id in (SELECT item_id FROM item_log " +
						"WHERE (assigned_to='"+userVO.getUserId()+"' || assigned_to in (SELECT distinct user_id from users where reportee='"+userVO.getUserId()+"')) AND " +
								"create_ts=(select max(create_ts) FROM item_log where item_id=A.item_id)) ");
			}
			System.out.println(RETRIEVE_ITEMS);
			itemsList	=	this.getJdbcTemplate().query(searchQuery.toString(), null, null,
					new RowMapper<ComplaintVO>(){
						public ComplaintVO mapRow(ResultSet rs, int rowNum)
							throws SQLException{
							ComplaintVO	itemVO	=	new ComplaintVO();
							List<ItemLogVO> itemLogList	=	new ArrayList<ItemLogVO>();
							List<AttachmentVO> attachmentList	=	new ArrayList<AttachmentVO>();
							itemVO.setComplaintId(rs.getInt("item_id"));
							itemVO.setStateId(rs.getInt("state_id"));
							itemVO.setCityId(rs.getInt("district_id"));
							itemVO.setDistrictId(rs.getInt("city_id"));
							itemVO.setRoadId(rs.getInt("road_id"));
							itemVO.setCityName(rs.getString("city_name"));
							itemVO.setRoadName(rs.getString("road_name"));
							itemVO.setComplaintDesc(rs.getString("item_desc"));
							IssueTypeVO issueTypeVO	=	 new IssueTypeVO();
							issueTypeVO.setIssueTypeId(rs.getInt("issue_type_id"));
							issueTypeVO.setIssueTypeName(rs.getString("issue_type_desc"));
							itemVO.setIssueTypeVO(issueTypeVO);
							itemVO.setCreateTS(rs.getTimestamp("create_ts"));	
							itemVO.setPriority(rs.getString("priority"));
							itemVO.setLastChangeTS(rs.getTimestamp("last_change_ts"));	
							itemLogList	=	getItemLog(itemVO);
							itemVO.setItemLogs(itemLogList);
							if(itemLogList!=null&&itemLogList.size()>0){
								itemVO.setStatus(itemLogList.get(0).getStatus());
								itemVO.setStatusId(itemLogList.get(0).getStatusId());
								itemVO.setAssignedTo(itemLogList.get(0).getAssignedTo());
								itemVO.setLastChangeTS(itemLogList.get(0).getCreateTS());
							}
							attachmentList	=	getItemAttachments(itemVO);
							itemVO.setItemAttachments(attachmentList);
							UserVO complainedBy	=	new UserVO();
							complainedBy.setAddress1(rs.getString("createdby_address1"));
							complainedBy.setFirstName(rs.getString("createdby_first_name"));
							complainedBy.setLastName(rs.getString("createdby_last_name"));
							complainedBy.setAddress2(rs.getString("createdby_address2"));
							complainedBy.setCity(rs.getString("createdby_city"));
							complainedBy.setState(rs.getString("createdby_state"));
							complainedBy.setPostalCode(rs.getString("createdby_zip"));
							complainedBy.setContactNo(rs.getString("createdby_phone"));
							complainedBy.setEmail(rs.getString("createdby_email"));
							itemVO.setComplaintUser(complainedBy);
							return itemVO;
						}
					});
			System.out.println(itemsList.size());
		}
		catch(DataAccessException dataAccessException){
			System.out.println(dataAccessException.getMessage());
		}
		return itemsList;
	}

	public List<ItemLogVO> getItemLog(ComplaintVO itemVO) {
		
		List<ItemLogVO> itemLogList	=	new ArrayList<ItemLogVO>();
		Object[] args			=	new Object[1];
		int[] argTypes			=	new int[1];
		try{
			args[0]		=	itemVO.getComplaintId();
			argTypes[0]	=	Types.INTEGER;
			itemLogList	=	this.getJdbcTemplate().query(RETRIEVE_ITEM_LOGS, args, argTypes,
					new RowMapper<ItemLogVO>(){
						public ItemLogVO mapRow(ResultSet rs, int rowNum)
							throws SQLException{
							ItemLogVO	itemLogVO	=	new ItemLogVO();
							List<AttachmentVO> attachmentList	=	new ArrayList<AttachmentVO>();
							itemLogVO.setItemId(rs.getInt("ITEM_ID"));
							itemLogVO.setLogId(rs.getInt("LOG_ID"));
							itemLogVO.setLoggedBy(rs.getString("LOGGED_BY"));
							itemLogVO.setRemarks(rs.getString("REMARKS"));
							itemLogVO.setStatusId(rs.getInt("STATUS_ID"));
							itemLogVO.setStatus(rs.getString("STATUS_NAME"));
							itemLogVO.setAssignedTo(rs.getString("ASSIGNED_TO"));
							itemLogVO.setDisplayFlag(rs.getBoolean("DISPLAYFLAG"));
							itemLogVO.setCreateTS(rs.getTimestamp("CREATE_TS"));
							attachmentList	=	getLogAttachments(itemLogVO);
							itemLogVO.setLogAttachments(attachmentList);
							return itemLogVO;
						}
					});
		}
		catch(DataAccessException dataAccessException){
			System.out.println(dataAccessException.getMessage());
		}
		return itemLogList;
	}

	@Override
	public ItemLogVO getItemLogDetails() {
		
		List<StatusVO> statusList	=	new ArrayList<StatusVO>();
		ItemLogVO	itemLogVO	=	new ItemLogVO();
		try{
			statusList	=	this.getJdbcTemplate().query(RETRIEVE_STATUS, null, null,
					new RowMapper<StatusVO>(){
						public StatusVO mapRow(ResultSet rs, int rowNum)
							throws SQLException{
							StatusVO statusVO	=	new StatusVO();
							statusVO.setStatusId(rs.getInt("STATUS_ID"));
							statusVO.setStatusName(rs.getString("STATUS_NAME"));
							return statusVO;
						}
					});
			itemLogVO.setStatusList(statusList);
		}
		catch(DataAccessException dataAccessException){
			System.out.println(dataAccessException.getMessage());
		}
		
		return itemLogVO;
	}

	@Override
	public void saveItem(ComplaintVO itemVO) {
		
		Items item	=	new Items();
		if(itemVO.getComplaintCreatedBy()!=null){
			item.setItemCreatedBy(itemVO.getComplaintCreatedBy().getUserId());
		}
		else{
			item.setItemCreatedBy("SYSTEM");
		}
		item.setItemDesc(itemVO.getComplaintDesc());
		item.setIssueTypeId(itemVO.getIssueTypeVO().getIssueTypeId());
		item.setPriority(itemVO.getPriority());
		item.setCreateTs(new Date());
		item.setLastChangeTs(new Date());
		item.setItemStatus(true);
		
		item.setStateId(itemVO.getStateId());
		item.setCityId(itemVO.getCityId());
		item.setDistrictId(itemVO.getDistrictId());
		item.setRoadId(itemVO.getRoadId());
		
		item.setCreatedbyAddress1(itemVO.getComplaintUser().getAddress1());
		item.setCreatedbyAddress2(itemVO.getComplaintUser().getAddress2());
		item.setCreatedbyCity(itemVO.getComplaintUser().getCity());
		item.setCreatedbyEmail(itemVO.getComplaintUser().getEmail());
		item.setCreatedbyFirstName(itemVO.getComplaintUser().getFirstName());
		item.setCreatedbyLastName(itemVO.getComplaintUser().getLastName());
		item.setCreatedbyPhone(itemVO.getComplaintUser().getContactNo());
		item.setCreatedbyState(itemVO.getComplaintUser().getState());
		item.setCreatedbyZip(itemVO.getComplaintUser().getPostalCode());
		this.getHibernateTemplate().save(item);
		
		ItemLogVO itemLogVO	=	new ItemLogVO();
		itemLogVO.setItemId(item.getItemId());
		itemVO.setComplaintId(item.getItemId());
		itemLogVO.setLoggedBy("SYSTEM");
		itemLogVO.setRemarks("Created");
		itemLogVO.setStatusId(1);//Open Status
		
		itemLogVO.setDisplayFlag(false);
		ItemLog itemLog	=	saveItemLog(itemLogVO);
		
		if(itemVO.getAttachmentVO().getFileData()!=null&&
				itemVO.getAttachmentVO().getFileData().getBytes().length>0){
			ItemAttachments itemAttachment	=	new ItemAttachments();
			itemAttachment.setAttachment(itemVO.getAttachmentVO().getFileData().getBytes());
			itemAttachment.setAttachmentName(itemVO.getAttachmentVO().getFileData().getOriginalFilename());
			itemAttachment.setItemLog(itemLog);
			this.getHibernateTemplate().save(itemAttachment);
		}
	}	
	
	public ItemLog saveItemLog(ItemLogVO itemLogVO) {
		
		ItemLog itemLog	=	new ItemLog();
		itemLog.setCreateTs(new Date());
		Items item	=	new Items();
		item.setItemId(itemLogVO.getItemId());
		itemLog.setItems(item);
		itemLog.setLoggedBy(itemLogVO.getLoggedBy());
		itemLog.setRemarks(itemLogVO.getRemarks());
		if(itemLogVO.getAssignedTo()!=null){
			itemLog.setAssignedTo(itemLogVO.getAssignedTo());
		}
		if(itemLogVO.getStatusId()!=0){
			Status status	=	new Status();
			status.setStatusId(itemLogVO.getStatusId());
			itemLog.setStatus(status);
		}
		itemLog.setDisplayFlag(itemLogVO.isDisplayFlag());
		this.getHibernateTemplate().save(itemLog);
		return itemLog;
	}

	@Override
	public ComplaintVO getItemDetails(ComplaintVO itemVO) {
		
		List<ComplaintVO> itemsList	=	new ArrayList<ComplaintVO>();
		StringBuffer searchQuery	=	new StringBuffer();
		try{
			searchQuery.append(RETRIEVE_ITEMS);
			if(itemVO.getComplaintId()!=0){
				searchQuery.append(" AND item_id="+itemVO.getComplaintId()+" ");
			}
			System.out.println(searchQuery);
			itemsList	=	this.getJdbcTemplate().query(searchQuery.toString(), null, null,
					new RowMapper<ComplaintVO>(){
				public ComplaintVO mapRow(ResultSet rs, int rowNum)
						throws SQLException{
						ComplaintVO	itemVO	=	new ComplaintVO();
						List<ItemLogVO> itemLogList	=	new ArrayList<ItemLogVO>();
						List<AttachmentVO> attachmentList	=	new ArrayList<AttachmentVO>();
						itemVO.setComplaintId(rs.getInt("item_id"));
						itemVO.setComplaintDesc(rs.getString("item_desc"));
						IssueTypeVO issueTypeVO	=	 new IssueTypeVO();
						issueTypeVO.setIssueTypeId(rs.getInt("issue_type_id"));
						issueTypeVO.setIssueTypeName(rs.getString("issue_type_desc"));
						itemVO.setIssueTypeVO(issueTypeVO);
						itemVO.setCreateTS(rs.getTimestamp("create_ts"));	
						itemVO.setPriority(rs.getString("priority"));
						itemVO.setLastChangeTS(rs.getTimestamp("last_change_ts"));	
						itemVO.setStateId(rs.getInt("state_id"));
						itemVO.setCityId(rs.getInt("district_id"));
						itemVO.setDistrictId(rs.getInt("city_id"));
						itemVO.setRoadId(rs.getInt("road_id"));
						itemVO.setCityName(rs.getString("city_name"));
						itemVO.setRoadName(rs.getString("road_name"));
						itemLogList	=	getItemLog(itemVO);
						itemVO.setItemLogs(itemLogList);
						if(itemLogList!=null&&itemLogList.size()>0){
							itemVO.setStatus(itemLogList.get(0).getStatus());
							itemVO.setStatusId(itemLogList.get(0).getStatusId());
							itemVO.setAssignedTo(itemLogList.get(0).getAssignedTo());
							itemVO.setLastChangeTS(itemLogList.get(0).getCreateTS());
						}
						attachmentList	=	getItemAttachments(itemVO);
						itemVO.setItemAttachments(attachmentList);
						UserVO complainedBy	=	new UserVO();
						complainedBy.setAddress1(rs.getString("createdby_address1"));
						complainedBy.setFirstName(rs.getString("createdby_first_name"));
						complainedBy.setLastName(rs.getString("createdby_last_name"));
						complainedBy.setAddress2(rs.getString("createdby_address2"));
						complainedBy.setCity(rs.getString("createdby_city"));
						complainedBy.setState(rs.getString("createdby_state"));
						complainedBy.setPostalCode(rs.getString("createdby_zip"));
						complainedBy.setContactNo(rs.getString("createdby_phone"));
						complainedBy.setEmail(rs.getString("createdby_email"));
						itemVO.setComplaintUser(complainedBy);
						return itemVO;
					}
				});
		}
		catch(DataAccessException dataAccessException){
			System.out.println(dataAccessException.getMessage());
		}
		return itemsList.get(0);
	}

	public List<AttachmentVO> getItemAttachments(ComplaintVO itemVO) {
		
		List<AttachmentVO> itemAttachmentList	=	new ArrayList<AttachmentVO>();
		Object[] args			=	new Object[2];
		int[] argTypes			=	new int[2];
		try{
			args[0]		=	itemVO.getComplaintId();
			argTypes[0]	=	Types.INTEGER;
			args[1]		=	itemVO.getComplaintId();
			argTypes[1]	=	Types.INTEGER;
			itemAttachmentList	=	this.getJdbcTemplate().query(RETRIEVE_ITEM_ATTACHMENTS, args, argTypes,
					new RowMapper<AttachmentVO>(){
						public AttachmentVO mapRow(ResultSet rs, int rowNum)
							throws SQLException{
							AttachmentVO	itemAttachmentVO	=	new AttachmentVO();
							itemAttachmentVO.setLogId(rs.getInt("LOG_ID"));
							itemAttachmentVO.setAttachmentId(rs.getInt("ATTACHMENT_ID"));
							itemAttachmentVO.setAttachmentDesc(rs.getString("ATTACHMENT_DESC"));
							itemAttachmentVO.setAttachmentName(rs.getString("ATTACHMENT_NAME"));
							return itemAttachmentVO;
						}
					});
		}
		catch(DataAccessException dataAccessException){
			System.out.println(dataAccessException.getMessage());
		}
		return itemAttachmentList;
	}

	public int getItemCreateLog(ItemVO itemVO) {
		
		int createLogId	=	0;
		Object[] args			=	new Object[1];
		int[] argTypes			=	new int[1];
		try{
			args[0]		=	itemVO.getItemId();
			argTypes[0]	=	Types.INTEGER;
			createLogId	=	this.getJdbcTemplate().queryForInt(RETRIEVE_CREATE_LOG_ID, args, argTypes);
		}
		catch(Exception dataAccessException){
			System.out.println(dataAccessException.getMessage());
		}
		return createLogId;
	}

	@Override
	public ComplaintVO updateItem(ComplaintVO itemVO) {

		try {
			Items item	=	new Items();
			SimpleDateFormat sdf	=	new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			item.setItemId(itemVO.getComplaintId());
			item.setItemDesc(itemVO.getComplaintDesc());
			item.setPriority(itemVO.getPriority());
			item.setItemCreatedBy(itemVO.getComplaintCreatedBy().getUserId());
			item.setCreateTs(itemVO.getCreateTS());
			item.setLastChangeTs(new Date());
			item.setItemStatus(true);
			this.getHibernateTemplate().update(item);

			ItemLogVO itemLogVO	=	new ItemLogVO();
			itemLogVO.setItemId(item.getItemId());
			itemLogVO.setLoggedBy(itemVO.getLoggedBy().getUserId());
			itemLogVO.setRemarks("Details Updated");		
			itemLogVO.setDisplayFlag(false);
			itemLogVO.setAssignedTo(itemVO.getAssignedTo());
			itemLogVO.setStatusId(itemVO.getStatusId());
			ItemLog itemLog	= saveItemLog(itemLogVO);

			if(itemVO.getAttachmentVO().getFileData()!=null&&
					itemVO.getAttachmentVO().getFileData().getBytes().length>0){
				ItemAttachments itemAttachment	=	new ItemAttachments();
				itemAttachment.setAttachment(itemVO.getAttachmentVO().getFileData().getBytes());
				itemAttachment.setAttachmentName(itemVO.getAttachmentVO().getFileData().getOriginalFilename());
				itemAttachment.setItemLog(itemLog);
				this.getHibernateTemplate().save(itemAttachment);
			}
			itemVO	=	getItemDetails(itemVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemVO;
	}
	
	public List<AttachmentVO> getLogAttachments(ItemLogVO itemLogVO) {
		
		List<AttachmentVO> itemAttachmentList	=	new ArrayList<AttachmentVO>();
		Object[] args			=	new Object[1];
		int[] argTypes			=	new int[1];
		try{
			args[0]		=	itemLogVO.getLogId();
			argTypes[0]	=	Types.INTEGER;
			System.out.println(itemLogVO.getLogId());
			itemAttachmentList	=	this.getJdbcTemplate().query(RETRIEVE_LOG_ATTACHMENTS, args, argTypes,
					new RowMapper<AttachmentVO>(){
						public AttachmentVO mapRow(ResultSet rs, int rowNum)
							throws SQLException{
							AttachmentVO	itemAttachmentVO	=	new AttachmentVO();
							itemAttachmentVO.setLogId(rs.getInt("LOG_ID"));
							itemAttachmentVO.setAttachmentId(rs.getInt("ATTACHMENT_ID"));
							itemAttachmentVO.setAttachmentDesc(rs.getString("ATTACHMENT_DESC"));
							itemAttachmentVO.setAttachmentName(rs.getString("ATTACHMENT_NAME"));
							return itemAttachmentVO;
						}
					});
		}
		catch(DataAccessException dataAccessException){
			System.out.println(dataAccessException.getMessage());
		}
		return itemAttachmentList;
	}

	@Override
	public ComplaintVO saveRemarks(ItemLogVO itemLogVO) {

		ComplaintVO itemVO	= new ComplaintVO();
		itemVO.setComplaintId(itemLogVO.getItemId());
		itemLogVO.setDisplayFlag(true);
		ItemLog itemLog	= saveItemLog(itemLogVO);

		if(itemLogVO.getAttachmentVO().getFileData()!=null&&
				itemLogVO.getAttachmentVO().getFileData().getBytes().length>0){
			ItemAttachments itemAttachment	=	new ItemAttachments();
			itemAttachment.setAttachment(itemLogVO.getAttachmentVO().getFileData().getBytes());
			itemAttachment.setAttachmentName(itemLogVO.getAttachmentVO().getFileData().getOriginalFilename());
			itemAttachment.setItemLog(itemLog);
			this.getHibernateTemplate().save(itemAttachment);
		}
		itemVO	=	getItemDetails(itemVO);
		return itemVO;
	}

	@Override
	public void deleteItem(ItemVO itemVO) {

		try {
			
			Object[] args			=	new Object[1];
			int[] argTypes			=	new int[1];
			try{
				args[0]		=	itemVO.getItemId();
				argTypes[0]	=	Types.INTEGER;
				this.getJdbcTemplate().update(DELETE_ITEM, args, argTypes);
			}
			catch(DataAccessException dataAccessException){
				System.out.println(dataAccessException.getMessage());
			}
			ItemLogVO itemLogVO	=	new ItemLogVO();
			itemLogVO.setItemId(itemVO.getItemId());
			itemLogVO.setLoggedBy(itemVO.getItemCreatedBy());
			itemLogVO.setRemarks("Inactive");		
			itemLogVO.setDisplayFlag(false);
			itemLogVO.setAssignedTo(itemVO.getAssignedTo());
			itemLogVO.setStatusId(itemVO.getStatusId());
			saveItemLog(itemLogVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public AttachmentVO getAttachment(AttachmentVO attachmentVO) {
		
		List<AttachmentVO> itemAttachmentList	=	new ArrayList<AttachmentVO>();
		Object[] args			=	new Object[1];
		int[] argTypes			=	new int[1];
		try{
			args[0]		=	attachmentVO.getAttachmentId();
			argTypes[0]	=	Types.INTEGER;
			itemAttachmentList	=	this.getJdbcTemplate().query(RETRIEVE_ATTACHMENT, args, argTypes,
					new RowMapper<AttachmentVO>(){
						public AttachmentVO mapRow(ResultSet rs, int rowNum)
							throws SQLException{
							AttachmentVO	itemAttachmentVO	=	new AttachmentVO();
							itemAttachmentVO.setLogId(rs.getInt("LOG_ID"));
							itemAttachmentVO.setAttachmentId(rs.getInt("ATTACHMENT_ID"));
							itemAttachmentVO.setAttachment(rs.getBytes("ATTACHMENT"));
							itemAttachmentVO.setAttachmentDesc(rs.getString("ATTACHMENT_DESC"));
							itemAttachmentVO.setAttachmentName(rs.getString("ATTACHMENT_NAME"));
							return itemAttachmentVO;
						}
					});
		}
		catch(DataAccessException dataAccessException){
			System.out.println(dataAccessException.getMessage());
		}
		return itemAttachmentList.get(0);
	}

	@Override
	public List<UserVO> getPOCsList(ComplaintVO itemVO) {
		
		List<UserVO> pocList	=	new ArrayList<UserVO>();
		List<UserVO> reporteeList	=	new ArrayList<UserVO>();
		Object[] args			=	new Object[2];
		int[] argTypes			=	new int[2];
		try{
			args[0]		=	itemVO.getComplaintId();
			argTypes[0]	=	Types.INTEGER;
			args[1]		=	itemVO.getComplaintId();
			argTypes[1]	=	Types.INTEGER;
			pocList	=	this.getJdbcTemplate().query(RETRIEVE_POCS, args, argTypes,
					new RowMapper<UserVO>(){
						public UserVO mapRow(ResultSet rs, int rowNum)
							throws SQLException{
							UserVO	userVO	=	new UserVO();
							userVO.setUserId(rs.getString("user_id"));
							userVO.setFirstName(rs.getString("first_name") + "(POC)");
							userVO.setLastName(rs.getString("last_name"));
							return userVO;
						}
					});
			if(pocList!=null && pocList.size()>0){
				for(UserVO	userVO : pocList){
					args			=	new Object[1];
					argTypes		=	new int[1];
					args[0]		=	userVO.getUserId();
					argTypes[0]	=	Types.VARCHAR;
					reporteeList.addAll(this.getJdbcTemplate().query(RETRIEVE_REPORTEES, args, argTypes,
					new RowMapper<UserVO>(){
						public UserVO mapRow(ResultSet rs, int rowNum)
							throws SQLException{
							UserVO	userVO	=	new UserVO();
							userVO.setUserId(rs.getString("user_id"));
							userVO.setFirstName(rs.getString("first_name"));
							userVO.setLastName(rs.getString("last_name"));
							return userVO;
						}
					}));
				}
			}
			if(reporteeList!=null && reporteeList.size()>0){
				pocList.addAll(reporteeList);
			}
		}
		catch(DataAccessException dataAccessException){
			System.out.println(dataAccessException.getMessage());
		}
		return pocList;
	}
	
	
}
