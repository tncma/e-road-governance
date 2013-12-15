<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/tracker.js"></script>
		<script>
			var realpath	=	"<%=request.getContextPath()%>";
		</script>
	</head>
	<body>
		<div>
			<c:if test="${itemSaved}">
				<script>
					refreshItemsList('${itemVO.groupId}');
				</script>
			</c:if>
			<h3 style="padding-left:30px;">Create new Defect</h3>
			 <form:form modelAttribute="createNewForm" method="post" enctype="multipart/form-data">
				<input type="hidden" id="groupId" name="groupId" value="${itemVO.groupId}">
				<font color="green">${successMessage}</font>
				<table style="padding-left:40px;">
					<tr>
						<td width="150px;">Reference No</td>
						<td><input type="text" name="itemReference" id="itemReference" maxLength="45"/></td>
						<td width="150px;">Priority</td>
						<td>
							<select name="priority" id="priority">
								<option value="Low">Low</option>
								<option value="Medium">Medium</option>
								<option value="High">High</option>						
							</select>
						</td>
					</tr>
					<tr>
						<td width="150px;" rowspan=2>Description <font color="red">*</font></td>
						<td rowspan=2><textarea cols="25" rows="4" name="itemDesc" id="itemDesc"></textarea></td>
						<td width="150px;">Severity</td>
						<td>
							<select name="severity" id="severity">
								<option value="Minor">Minor</option>
								<option value="Medium">Medium</option>
								<option value="Major">Major</option>	
								<option value="Critical">Critical</option>						
							</select>
						</td>
					</tr>
					<tr>
						<td>Reported by <font color="red">*</font></td>
						<td><input type="text" name="itemCreatedBy" id="itemCreatedBy" maxLength="45"/>
					</tr>
					<tr height="40">
						<td>Steps to Reproduce </td>
						<td><textarea cols="25" rows="4" name="itemSteps" id="itemSteps"></textarea></td>
						<td>Assigned To </td>
						<td><input type="text" name="assignedTo" id="assignedTo" maxLength="75"/></td>
					</tr>		
					<tr height="40">
						<td>Module</td>
						<td><input type="text" name="module" id="module" maxLength="45"/></td>
						<td>Sub Module</td>
						<td><input type="text" name="subModule" id="module" maxLength="45"/></td>
					</tr>		
					<tr height="40">
						<td>Type</td>
						<td>
							<select name="itemType" id="itemType">
								<option value="Defect">Defect</option>
								<option value="Change">Change</option>
								<option value="Enhancement">Enhancement</option>	
								<option value="Clarification">Clarification</option>					
							</select>
						</td>
						<td>Attachment</td>
						<td>
							<form:input path="attachmentVO.fileData" type="file"/>(Max File Size - 16mb)
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<br/>
							<input type="button" value="Save Item" onclick="saveItem()"/>
						</td>
					</tr>			
				</table>
			</form:form>
		</div>
	</body>
</html>