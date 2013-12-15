<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

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
		<div>
			<c:if test="${logSaved}">
				<script>
					refreshItemsList();
				</script>
			</c:if>
			<h3>Details</h3>
			<div>
				<table width="100%" style="padding-left:30px;">
					<tr>
						<td width="120px"><strong>Complaint No : </strong></td>
						<td>${itemVO.complaintId}</td>
					</tr>
					<tr>
						<td><strong>Description : </strong></td>
						<td>${itemVO.complaintDesc}</td>
					</tr>
				</table>
			</div><br/>
			<div style="padding-left:50px;border-bottom:1px solid;">		
				<span id="detailsCaption" onclick="editItem('${itemVO.complaintId}')"  class="tabNoSelect" style="float:left">&nbsp;Details&nbsp;</span>
				<span id="statusCaption" class="tabSelect" style="float:left">&nbsp;Status & Remarks&nbsp;</span>
				<br/>
			</div>
			 <form:form modelAttribute="itemLogVO" method="post" enctype="multipart/form-data">
				<input type="hidden" id="itemId" name="itemId" value="${itemVO.complaintId}">
				<br/>
				<font color="green">${successMessage}</font>
				<table style="padding-left:40px;">
					<tr>
						<td>Assigned To </td>
						<td><form:select path="assignedTo" id="assignedTo" items="${itemLogVO.pocs}" itemLabel="firstName" itemValue="userId"/></td>
						<td>Status</td>
						<td>
							<form:select path="statusId" id="statusId" items="${itemLogVO.statusList}" itemLabel="statusName" itemValue="statusId"/>
						</td>
					</tr>	
					<tr>
						<td>Logged by </td>
						<td><input type="text" name="loggedBy" id="itemUpdatedBy" maxLength="45" value="${sessionScope.userVO.userId}" readonly="readonly"/>
						<td>Remarks </td>
						<td><textarea cols="25" rows="4" name="remarks" id="remarks"></textarea></td>
					</tr>
					<tr>
						<td>Attachment</td>
						<td>
							<form:input path="attachmentVO.fileData" type="file"/>(Max File Size - 16mb)
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<br/>
							<input type="button" value="Save" onclick="saveRemarks()"/>
						</td>
					</tr>			
				</table>
				<hr/>
				<div style="width:90%;text-align:center">
					<div style="padding:10px;"><strong><u>History</u></strong></div>
					<table border="1" align="center">
						<thead>
							<tr>
								<th>Date</th>
								<th>Logged By</th>
								<th>Status</th>
								<th>Attachment</th>
								<th>Remark</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${itemVO.itemLogs}" var="itemLog">
							<tr>
								<td><fmt:formatDate pattern="dd MMM" value="${itemLog.createTS}" /></td>
								<td>${itemLog.loggedBy}</td>
								<td>${itemLog.status}</td>
								<td>
									<c:forEach items="${itemLog.logAttachments}" var="logAttachments">
										<a href="<%=request.getContextPath()%>/getAttachment.do?attachmentId=${logAttachments.attachmentId}">${logAttachments.attachmentName}</a><br/>
									</c:forEach>
								</td>
								<td>${itemLog.remarks}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</form:form>
		</div>
	</body>
</html>