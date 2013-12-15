package com.roadgovern.dao;

import java.util.List;

import com.roadgovern.vo.AttachmentVO;
import com.roadgovern.vo.ComplaintVO;
import com.roadgovern.vo.ItemLogVO;
import com.roadgovern.vo.ItemVO;
import com.roadgovern.vo.UserVO;


/**
 * @author arun_christopher
 *
 */
public interface TrackerDAO {
	
	

	public static final String RETRIEVE_ITEMS 		= "SELECT item_id, item_desc, item_createdBy, create_ts, last_change_ts, item_status, priority, issue_type_desc, " +
														"A.state_id, A.district_id, D.district_name, A.city_id, city_name, A.road_id, E.road_name, A.issue_type_id, createdby_first_name, createdby_last_name, createdby_address1, " +
														"createdby_address2, createdby_city, createdby_state, createdby_zip, createdby_phone, createdby_email " +
														"FROM items A, issue_type B, city C, district D, road E  WHERE A.issue_type_id=B.issue_type_id AND A.city_id=C.city_id " +
														"AND A.district_id=D.district_id AND A.road_id=E.road_id ";
	
	String RETRIEVE_STATUS 		= "SELECT STATUS_ID,STATUS_NAME FROM status";
	
	String RETRIEVE_ITEM_LOGS 	= "SELECT LOG_ID, ITEM_ID, A.STATUS_ID, LOGGED_BY, REMARKS, CREATE_TS, ASSIGNED_TO, " +
									"DISPLAYFLAG, STATUS_NAME FROM item_log A LEFT OUTER JOIN status B " +
									"on A.STATUS_ID=B.STATUS_ID WHERE A.ITEM_ID=? ORDER BY CREATE_TS DESC";
	
	String RETRIEVE_ITEM_ATTACHMENTS	=	"SELECT ATTACHMENT_ID, A.LOG_ID, ATTACHMENT_DESC, ATTACHMENT_NAME FROM " +
												"item_attachments A,item_log B WHERE A.LOG_ID=B.LOG_ID AND B.ITEM_ID=? " +
												"AND A.LOG_ID=(SELECT MIN(LOG_ID) FROM item_log WHERE ITEM_ID=?) ";
	
	String RETRIEVE_MAX_ITEM_NO	=	"SELECT MAX(ITEM_ID) AS MAXITEM FROM items";
	
	String RETRIEVE_CREATE_LOG_ID	=	"SELECT MIN(LOG_ID) FROM item_log WHERE ITEM_ID=?";
	
	String RETRIEVE_LOG_ATTACHMENTS	=	"SELECT ATTACHMENT_ID, LOG_ID, ATTACHMENT_DESC, ATTACHMENT_NAME FROM item_attachments WHERE LOG_ID=?";
	
	String DELETE_ITEM	=	"UPDATE items SET ITEM_STATUS='0' WHERE ITEM_ID=?";
	
	String RETRIEVE_ATTACHMENT	=	"SELECT ATTACHMENT_ID, LOG_ID, ATTACHMENT, ATTACHMENT_DESC, ATTACHMENT_NAME FROM item_attachments WHERE ATTACHMENT_ID=?";
	
	String RETRIEVE_POCS	=	"SELECT user_id,first_name, last_name from users where user_id in (select user_id from issue_poc where issue_type_id=(select issue_type_id from items where item_id=?) AND city_id in (SELECT city_id FROM items where item_id=?))";
		
	String RETRIEVE_REPORTEES	=	"SELECT user_id,first_name, last_name from users where reportee =?";
	
	public List<ComplaintVO> getDashBoard(UserVO userVO);
	public ItemLogVO getItemLogDetails();
	public void saveItem(ComplaintVO complaintVO);
	public ComplaintVO updateItem(ComplaintVO itemVO);
	public ComplaintVO saveRemarks(ItemLogVO itemLogVO);
	public void deleteItem(ItemVO itemVO);
	public AttachmentVO getAttachment(AttachmentVO attachmentVO);
	public ComplaintVO getItemDetails(ComplaintVO itemVO);
	public List<UserVO> getPOCsList(ComplaintVO itemVO);
}
