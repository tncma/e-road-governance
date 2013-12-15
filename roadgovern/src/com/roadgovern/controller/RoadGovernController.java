package com.roadgovern.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.validator.routines.EmailValidator;

import com.roadgovern.dao.RoadGovernDAO;
import com.roadgovern.dao.TrackerDAO;
import com.roadgovern.utils.MailClient;
import com.roadgovern.vo.AttachmentVO;
import com.roadgovern.vo.ComplaintVO;
import com.roadgovern.vo.FilterVO;
import com.roadgovern.vo.ItemLogVO;
import com.roadgovern.vo.ItemVO;
import com.roadgovern.vo.MailUtilVO;
import com.roadgovern.vo.StatusVO;
import com.roadgovern.vo.UserVO;


public class RoadGovernController {
	
	private RoadGovernDAO roadGovernDAO;
	private MailUtilVO mailUtil;
	private MailClient mailClient;


	public void setMailClient(MailClient mailClient) {
		this.mailClient = mailClient;
	}


	private TrackerDAO trackerDAO;

	public void setMailUtil(MailUtilVO mailUtil) {
		this.mailUtil = mailUtil;
	}
	public void setTrackerDAO(TrackerDAO trackerDAO) {
		this.trackerDAO = trackerDAO;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index.do")
	public ModelAndView getHome(HttpServletRequest request) {
		
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("stateId", 1);
		return new ModelAndView("homePage", modelMap);
	}

	@RequestMapping(value = "/selectDistrict.do")
	public ModelAndView selectDistrict(HttpServletRequest request,  @RequestParam int stateId, @RequestParam int districtId) {
		
		if(districtId!=0){
			if(districtId==2){
				System.out.println("Coimbatore District Selected");
			}
		}
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("stateId", stateId);
		modelMap.addAttribute("districtId", districtId);
		return new ModelAndView("district", modelMap);
	}
	


	@RequestMapping(value = "/createComplaint.do")
	public ModelAndView createComplaint(HttpServletRequest request,  @RequestParam int stateId, @RequestParam int districtId, @RequestParam int cityId) {
		
		if(cityId!=0){
			if(cityId==1){
				System.out.println("Coimbatore City Selected");
			}
		}
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("stateId", stateId);
		modelMap.addAttribute("districtId", districtId);
		modelMap.addAttribute("cityId", cityId);
		
		ComplaintVO complaintVO 	=	new ComplaintVO();
		complaintVO.setStateId(stateId);
		complaintVO.setCityId(cityId);
		complaintVO.setDistrictId(districtId);
		
		complaintVO.setStates(roadGovernDAO.getStates());
		complaintVO.setCities(roadGovernDAO.getCities(districtId));
		complaintVO.setDistricts(roadGovernDAO.getDistricts(stateId));
		complaintVO.setRoads(roadGovernDAO.getRoads(cityId));
		complaintVO.setIssueTypes(roadGovernDAO.getIssuseTypes());

		modelMap.addAttribute("complaintVO", complaintVO);

		
		return new ModelAndView("complaint", modelMap);
	}


	@RequestMapping(value = "/logon.do")
	public ModelAndView logon(HttpServletRequest request, @ModelAttribute UserVO userVO) {
		
		ModelMap modelMap = new ModelMap();
		
		if(roadGovernDAO.logon(userVO)){
			
			request.getSession().setAttribute("userVO", userVO);
			
			return getDashboard(request, new FilterVO());
		}
		else{
			modelMap.addAttribute("loginError", "Invalid Username or Password");
			modelMap.addAttribute("stateId", 1);
			return new ModelAndView("homePage", modelMap);
		}
		
	}
	@RequestMapping(value = "/logout.do")
	public ModelAndView logout(HttpServletRequest request) {

		ModelMap modelMap = new ModelMap();

		request.getSession().removeAttribute("userVO");
		request.getSession().invalidate();
		modelMap.addAttribute("stateId", 1);
		
		return new ModelAndView("homePage", modelMap);

	}


	@RequestMapping(value = "/getDashboard.do")
	public ModelAndView getDashboard(HttpServletRequest request, @ModelAttribute FilterVO filterVO) {
		
		ModelMap modelMap = new ModelMap();
		UserVO userVO	=	(UserVO)request.getSession().getAttribute("userVO");
		
		List<ComplaintVO> itemsList	=	trackerDAO.getDashBoard(userVO);
		List<ComplaintVO> itemsTempList	=	new ArrayList<ComplaintVO>();
		TreeSet<String> moduleList	=	new TreeSet<String>(); 
		TreeSet<String> subModuleList	=	new TreeSet<String>(); 
		TreeSet<String> assignedToList	=	new TreeSet<String>(); 
		TreeSet<String> typeList	=	new TreeSet<String>();  
		TreeSet<String> severityList	=	new TreeSet<String>();  
		StatusDuplicateComparator comparator	=	new StatusDuplicateComparator();
		TreeSet<StatusVO> statusSet	=	new TreeSet<StatusVO>(comparator);  
		List<String>	tempList	=	null;
		List<StatusVO>	statusList	=	new ArrayList<StatusVO>();
		StatusVO	statusVO	=	null;
		boolean addFlag	=	false;
		
		for(ComplaintVO itemVO : itemsList){
			addFlag	=	false;
			if(filterVO.getStatusId()!=0){
				if(itemVO.getStatusId()==filterVO.getStatusId()){
					addFlag	=	true;
				}
				else{
					addFlag	=	false;
					continue;
				}
			}
			else{
				addFlag	=	true;
			}
			if(addFlag && filterVO.getAssignedTo()!=null && !filterVO.getAssignedTo().equalsIgnoreCase("")){
				if(itemVO.getAssignedTo()!=null&&itemVO.getAssignedTo().equals(filterVO.getAssignedTo())){
					addFlag	=	true;
				}
				else{
					addFlag	=	false;
					continue;
				}
			}
			else{
				addFlag	=	true;
			}
			if(addFlag && filterVO.getType()!=null && !filterVO.getType().equalsIgnoreCase("")){
				if(itemVO.getIssueTypeVO().getIssueTypeName()!=null&&itemVO.getIssueTypeVO().getIssueTypeName().equals(filterVO.getType())){
					addFlag	=	true;
				}
				else{
					addFlag	=	false;
					continue;
				}
			}
			else{
				addFlag	=	true;
			}
			if(addFlag && filterVO.getSeverity()!=null && !filterVO.getSeverity().equalsIgnoreCase("")){
				if(itemVO.getSeverity()!=null&&itemVO.getSeverity().equals(filterVO.getSeverity())){
					addFlag	=	true;
				}
				else{
					addFlag	=	false;
					continue;
				}
			}
			else{
				addFlag	=	true;
			}
			if(addFlag){
				itemsTempList.add(itemVO);
			}
		}
		for(ComplaintVO itemTempVO : itemsList){
			if(itemTempVO.getAssignedTo()!=null&&!itemTempVO.getAssignedTo().equalsIgnoreCase("")){
				assignedToList.add(itemTempVO.getAssignedTo());
			}
			if(itemTempVO.getIssueTypeVO().getIssueTypeName()!=null&&!itemTempVO.getIssueTypeVO().getIssueTypeName().equalsIgnoreCase("")){
				typeList.add(itemTempVO.getIssueTypeVO().getIssueTypeName());
			}
			if(itemTempVO.getSeverity()!=null&&!itemTempVO.getSeverity().equalsIgnoreCase("")){
				severityList.add(itemTempVO.getSeverity());
			}
			statusVO	=	new StatusVO();
			statusVO.setStatusId(itemTempVO.getStatusId());
			statusVO.setStatusName(itemTempVO.getStatus());
			statusSet.add(statusVO);
		}
		tempList	=	new ArrayList<String>();
		tempList.addAll(assignedToList);
		filterVO.setAssignedToList(tempList);
		tempList	=	new ArrayList<String>();
		tempList.addAll(typeList);
		filterVO.setTypeList(tempList);
		tempList	=	new ArrayList<String>();
		tempList.addAll(severityList);
		filterVO.setSeverityList(tempList);
		statusList.addAll(statusSet);
		filterVO.setStatusList(statusList);
		
		modelMap.addAttribute("complaints",itemsTempList);
		modelMap.addAttribute("filterVO",filterVO);
		return new ModelAndView("dashboard", modelMap);
	}

	@RequestMapping(value = "/createItem.do")
	public ModelAndView createNewItem(HttpServletRequest request, @RequestParam int groupId, @ModelAttribute("createNewForm") ItemVO itemVO) {
		
		ModelMap modelMap = new ModelMap();
		itemVO.setGroupId(groupId);
		modelMap.addAttribute("itemVO",itemVO);
		return new ModelAndView("createItem", modelMap);
	}

	@RequestMapping(value = "/submitComplaint.do")
	public ModelAndView saveItem(HttpServletRequest request, @ModelAttribute("complaintVO") ComplaintVO complaintVO) {
		
		ModelMap modelMap = new ModelMap();
		if(request.getSession().getAttribute("userVO")!=null){

			UserVO userVO	=	(UserVO)request.getSession().getAttribute("userVO");
			complaintVO.setComplaintCreatedBy(userVO);
		}
		trackerDAO.saveItem(complaintVO);
		modelMap.addAttribute("successMessage", "Your complaint has been created and will be shortly assigned to the concerned authority. We will keep you posted through email/sms about the actions taken. " +
				"Your complaint number is "+complaintVO.getComplaintId()+". Please use this number to check the status of your complaint.");
		modelMap.addAttribute("itemSaved", true);
		modelMap.addAttribute("itemVO",complaintVO);

		try{
			if(EmailValidator.getInstance().isValid(complaintVO.getComplaintUser().getEmail())){
	
				System.out.println("Sending email from "+mailUtil.getFrom()+" to "+complaintVO.getComplaintUser().getEmail());
				MailUtilVO mailUtilVO = new MailUtilVO();
				mailUtilVO.setFrom(mailUtil.getFrom());
				mailUtilVO.setTo(complaintVO.getComplaintUser().getEmail());
				mailUtilVO.setSubject("eGrievance Portal - Complaint Created <"+complaintVO.getComplaintId()+">");
				mailUtilVO.setBodyContent("Your complaint has been created and will be shortly assigned to the concerned authority. We will keep you posted through email/sms about the actions taken. " +
						"Your complaint number is "+complaintVO.getComplaintId()+". Please use this number to check the status of your complaint.");
				mailClient.sendMail(mailUtilVO);
				System.out.println("Email Sent....");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("summaryCreation", modelMap);
	}
	public class StatusDuplicateComparator implements Comparator<StatusVO>{

	    @Override
	    public int compare(StatusVO statusA, StatusVO statusB) {

	        int status1 = statusA.getStatusId();
	        int status2 = statusB.getStatusId();
	        
	        if(status1==status2){
	        	return 0;
	        }
	        else{
	        	return 1;
	        }
	        
	    }
	}
	@RequestMapping(value = "/editItem.do")
	public ModelAndView editItem(HttpServletRequest request, @ModelAttribute("editForm") ComplaintVO itemVO) {
		
		itemVO	=	trackerDAO.getItemDetails(itemVO);
		itemVO.setIssueTypes(roadGovernDAO.getIssuseTypes());
		return new ModelAndView("editItem", "itemVO", itemVO);
	}

	@RequestMapping(value = "/updateItem.do")
	public ModelAndView updateItem(HttpServletRequest request, @ModelAttribute("editForm") ComplaintVO itemVO) {
		
		ModelMap modelMap = new ModelMap();
		itemVO	=	trackerDAO.updateItem(itemVO);
		itemVO.setIssueTypes(roadGovernDAO.getIssuseTypes());
		modelMap.addAttribute("successMessage", "Item successfully updated!");
		modelMap.addAttribute("itemSaved", true);
		modelMap.addAttribute("itemVO", itemVO);
		return new ModelAndView("editItem", modelMap);
	}
	
	@RequestMapping(value = "/addRemarks.do")
	public ModelAndView addRemarks(HttpServletRequest request, @ModelAttribute("addRemarks") ItemLogVO itemLogVO) {

		ModelMap modelMap = new ModelMap();
		ComplaintVO itemVO	=	new ComplaintVO();
		itemVO.setAllLogsRequired(true);
		itemVO.setComplaintId(itemLogVO.getItemId());
		itemVO	=	trackerDAO.getItemDetails(itemVO);
		itemLogVO.setStatusList(trackerDAO.getItemLogDetails().getStatusList());
		itemLogVO.setPocs(trackerDAO.getPOCsList(itemVO));
		itemLogVO.setStatusId(itemVO.getStatusId());
		itemLogVO.setAssignedTo(itemVO.getAssignedTo());
		modelMap.addAttribute("itemVO", itemVO);
		modelMap.addAttribute("itemLogVO", itemLogVO);
		
		return new ModelAndView("remarks", modelMap);
	}
	
	@RequestMapping(value = "/saveRemarks.do")
	public ModelAndView saveRemarks(HttpServletRequest request, @ModelAttribute("addRemarks") ItemLogVO itemLogVO) {

		ModelMap modelMap = new ModelMap();		
		ComplaintVO itemVO	=	trackerDAO.saveRemarks(itemLogVO);
		modelMap.addAttribute("successMessage", "Remark/Status updated!");
		modelMap.addAttribute("logSaved", true);
		
		itemLogVO.setStatusList(trackerDAO.getItemLogDetails().getStatusList());
		itemLogVO.setPocs(trackerDAO.getPOCsList(itemVO));
		itemLogVO.setStatusId(itemVO.getStatusId());
		itemLogVO.setAssignedTo(itemVO.getAssignedTo());
		modelMap.addAttribute("itemVO", itemVO);
		modelMap.addAttribute("itemLogVO", itemLogVO);
		
		return new ModelAndView("remarks", modelMap);
	}
	
	@RequestMapping(value = "/deleteItem.do")
	public ModelAndView deleteItem(HttpServletRequest request, @ModelAttribute("editForm") ItemVO itemVO) {
		
		ModelMap modelMap = new ModelMap();
		trackerDAO.deleteItem(itemVO);
		modelMap.addAttribute("successMessage", "Item successfully deleted!");
		modelMap.addAttribute("itemDeleted", true);
		modelMap.addAttribute("itemVO", itemVO);
		return new ModelAndView("editItem", modelMap);
	}
	
	@RequestMapping(value = "/getAttachment.do")
	public void getAttachment(HttpServletRequest request,HttpServletResponse response,
			@RequestParam int attachmentId) {

		try{
			ServletOutputStream op = response.getOutputStream();
			AttachmentVO attachmentVO	=	new AttachmentVO();
			attachmentVO.setAttachmentId(attachmentId);
			attachmentVO	=	trackerDAO.getAttachment(attachmentVO);
			response.setContentType(new MimetypesFileTypeMap().getContentType(attachmentVO.getAttachmentName()));
			response.setContentLength(attachmentVO.getAttachment().length);
			response.setHeader("Content-Disposition","attachment; filename=\"" + attachmentVO.getAttachmentName() +"\"");
			FileCopyUtils.copy(attachmentVO.getAttachment(), response.getOutputStream());
			byte[] bbuf = attachmentVO.getAttachment();
			op.write(bbuf, 0, attachmentVO.getAttachment().length);
			op.flush();
			op.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/track.do")
	public ModelAndView track(HttpServletRequest request, @ModelAttribute ItemLogVO itemLogVO) {

		ModelMap modelMap = new ModelMap();
		try{
			ComplaintVO itemVO	=	new ComplaintVO();
			itemVO.setAllLogsRequired(true);
			itemVO.setComplaintId(itemLogVO.getItemId());
			itemVO	=	trackerDAO.getItemDetails(itemVO);
			itemLogVO.setStatusId(itemVO.getStatusId());
			itemLogVO.setAssignedTo(itemVO.getAssignedTo());
			modelMap.addAttribute("itemVO", itemVO);
			modelMap.addAttribute("itemLogVO", itemLogVO);
			
			return new ModelAndView("track", modelMap);
		}
		catch(Exception e){
			modelMap.addAttribute("trackError", "Invalid Complaint Number");
			modelMap.addAttribute("stateId", 1);
			return new ModelAndView("homePage", modelMap);
		}
	}
	
	@RequestMapping(value = "/addPOC.do")
	public ModelAndView addPOC(HttpServletRequest request){
		
		ModelMap modelMap = new ModelMap();
		UserVO userVO	=	new UserVO();
		userVO.setCities(roadGovernDAO.getCities(0));
		userVO.setIssueTypes(roadGovernDAO.getIssuseTypes());
		modelMap.addAttribute("userVO", userVO);
		return new ModelAndView("user", modelMap);
	}
	
	@RequestMapping(value = "/saveUser.do")
	public ModelAndView saveUser(HttpServletRequest request, @ModelAttribute UserVO userVO) {

		ModelMap modelMap = new ModelMap();		
		roadGovernDAO.saveUser(userVO);
		request.setAttribute("successMessage", "User added!");
		return addPOC(request);
	}
	
	public void setRoadGovernDAO(RoadGovernDAO roadGovernDAO) {
		this.roadGovernDAO = roadGovernDAO;
	}
	
	
}
