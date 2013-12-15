<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/roadgovern.js"></script>
		<script>
			var realpath	=	"<%=request.getContextPath()%>";
		</script>
		<style type="text/css">
		.tabSelect{
			background-color:#fff;
			border-top:1px solid;
			border-left:1px solid;
			border-right:1px solid;
			margin-bottom:-1px;
			margin-left:5px;
		}
		
		.tabNoSelect{
			background-color:#ccc;
			border-top:1px solid;
			border-left:1px solid;
			border-right:1px solid;
			margin-top:-1px;
			margin-left:5px;
			cursor:pointer;
		}
		</style>
	</head>
	<body>
		<c:if test="${itemDeleted}">
			<script>
				refreshItemsList();
				alert('Item Successfully deleted!');
				window.close();
			</script>
		</c:if>
		<c:if test="${not itemDeleted}">
			<div>
				<c:if test="${itemSaved}">
					<script>
						refreshItemsList();
					</script>
				</c:if>
				<h3>Details</h3>
				<div>
					<table width="100%" style="padding-left:30px;">
						<tr>
							<td><strong>Description : </strong></td>
							<td>${itemVO.complaintDesc}</td>
						</tr>
					</table>
				</div><br/>
				<div style="padding-left:50px;width:100%;border-bottom:1px solid;">		
					<span id="detailsCaption" class="tabSelect" style="float:left">&nbsp;Details&nbsp;</span>
					<span id="statusCaption" onclick="addRemarks('${itemVO.complaintId}')" class="tabNoSelect" style="float:left">&nbsp;Status & Remarks&nbsp;</span>
					<br/>
				</div>
				 <form:form modelAttribute="itemVO" method="post" enctype="multipart/form-data">
					<input type="hidden" id="itemId" name="complaintId" value="${itemVO.complaintId}">
					<input type="hidden" id="itemCreatedBy" name="complaintCreatedBy.userId" value="${itemVO.complaintCreatedBy.userId}">
					<input type="hidden" id="assignedTo" name="assignedTo" value="${itemVO.assignedTo}">
					<input type="hidden" id="statusId" name="statusId" value="${itemVO.statusId}">
					<input type="hidden" id="stateId" name="stateId" value="${itemVO.stateId}">
					<input type="hidden" id="cityId" name="cityId" value="${itemVO.cityId}">
					<input type="hidden" id="districtId" name="districtId" value="${itemVO.districtId}">
					<input type="hidden" id="roadId" name="roadId" value="${itemVO.roadId}">
					
					<input type="hidden" id="address1" name="complaintUser.address1" value="${itemVO.complaintUser.address1}">
					<input type="hidden" id="firstName" name="complaintUser.firstName" value="${itemVO.complaintUser.firstName}">
					<input type="hidden" id="lastName" name="complaintUser.lastName" value="${itemVO.complaintUser.lastName}">
					<input type="hidden" id="address2" name="complaintUser.address2" value="${itemVO.complaintUser.address2}">
					<input type="hidden" id="city" name="complaintUser.city" value="${itemVO.complaintUser.city}">
					<input type="hidden" id="state" name="complaintUser.state" value="${itemVO.complaintUser.state}">
					<input type="hidden" id="postalCode" name="complaintUser.postalCode" value="${itemVO.complaintUser.postalCode}">
					<input type="hidden" id="contactNo" name="complaintUser.contactNo" value="${itemVO.complaintUser.contactNo}">
					<input type="hidden" id="email" name="complaintUser.email" value="${itemVO.complaintUser.email}">
					<form:hidden path="createTS" id="createTs"/>
					<font color="green">${successMessage}</font>
					<br/>
					<table style="padding-left:40px;">
						<tr>
							<td width="150px;">Priority</td>
							<td>
								<form:select path="priority" id="priority" >
									<form:option value="Low" label="Low"/>
									<form:option value="Medium" label="Medium"/>
									<form:option value="High" label="High"/>
								</form:select>
							</td>
							<td>Type</td>
							<td>
								<form:select path="issueTypeVO.issueTypeId" cssClass="field select addr" id="issueTypeId">
									<form:options items="${itemVO.issueTypes}" itemLabel="issueTypeName" itemValue="issueTypeId"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td rowspan=2>Description <font color="red">*</font></td>
							<td rowspan=2><textarea cols="25" rows="4" name="complaintDesc" id="itemDesc">${itemVO.complaintDesc}</textarea></td>
							<td>Logged by</td>
							<td><input type="text" name="itemUpdatedBy" id="itemUpdatedBy" maxLength="45" value="${sessionScope.userVO.userId}" readonly="readonly"/></td>
						</tr>
						<tr>
							<td>Attachments</td>
							<td>
								<c:forEach items="${itemVO.itemAttachments}" var="itemAttachments">
									<a href="<%=request.getContextPath()%>/getAttachment.do?attachmentId=${itemAttachments.attachmentId}">${itemAttachments.attachmentName}</a><br/>
								</c:forEach>
								<form:input path="attachmentVO.fileData" type="file"/><br/>(Max File Size - 16mb)
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<br/>
								<input type="button" value="Save" onclick="updateItem()"/>
								<input type="button" value="Delete" onclick="deleteItem()"/>
							</td>
						</tr>			
					</table>
				</form:form>
			</div>
		</c:if>
	</body>
</html>