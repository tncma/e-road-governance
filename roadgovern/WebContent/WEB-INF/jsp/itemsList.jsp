<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<style>
.noDisplay{
	display:none;
}
#remarksDiv{
    padding: 0px;
    text-align: left;
    vertical-align: top;
    font-size:11px;
}
</style>
<form id="filterForm" action="getGroup.do">
	<input type="hidden" id="goupIdHidden" name="groupId" value="${filterVO.groupId}"/>
	<input type="hidden" id="goupNameHidden" name="groupName" value="${filterVO.groupName}"/>
	<div id="filterCriteria" style="font-size: 14px; text-align:center; width:100%">
		<table style="font-size: 14px" align="center">
			<tr>
				<td>Module :</td>
				<td>
					<select id="moduleFilter" name="module" onchange="filterResult()"  style="font-size: 14px">
						<option value="">Select</option>
						<c:forEach items="${filterVO.moduleList}" var="moduleList">
							<c:choose>
								<c:when test="${filterVO.module==moduleList}">
									<option value="${moduleList}" SELECTED>${moduleList}</option>
								</c:when>
								<c:otherwise>
									<option value="${moduleList}">${moduleList}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<td>Sub Module :</td>
				<td>
					<select id="moduleFilter" name="subModule" onchange="filterResult()" style="font-size: 14px">
						<option value="">Select</option>
						<c:forEach items="${filterVO.subModuleList}" var="subModuleList">
							<c:choose>
								<c:when test="${filterVO.subModule==subModuleList}">
									<option value="${subModuleList}" SELECTED>${subModuleList}</option>
								</c:when>
								<c:otherwise>
									<option value="${subModuleList}">${subModuleList}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<td>Status :</td>
				<td>
					<select id="moduleFilter" name="statusId" onchange="filterResult()" style="font-size: 14px">
						<option value="0">Select</option>
						<c:forEach items="${filterVO.statusList}" var="statusList">
							<c:choose>
								<c:when test="${filterVO.statusId==statusList.statusId}">
									<option value="${statusList.statusId}" SELECTED>${statusList.statusName}</option>
								</c:when>
								<c:otherwise>
									<option value="${statusList.statusId}">${statusList.statusName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<td>Assigned To :</td>
				<td>
					<select id="moduleFilter" name="assignedTo" onchange="filterResult()" style="font-size: 14px">
						<option value="">Select</option>
						<c:forEach items="${filterVO.assignedToList}" var="assignedToList">
							<c:choose>
								<c:when test="${filterVO.assignedTo==assignedToList}">
									<option value="${assignedToList}" SELECTED>${assignedToList}</option>
								</c:when>
								<c:otherwise>
									<option value="${assignedToList}">${assignedToList}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<td>Type :</td>
				<td>
					<select id="moduleFilter" name="type" onchange="filterResult()" style="font-size: 14px">
						<option value="">Select</option>
						<c:forEach items="${filterVO.typeList}" var="typeList">
							<c:choose>
								<c:when test="${filterVO.type==typeList}">
									<option value="${typeList}" SELECTED>${typeList}</option>
								</c:when>
								<c:otherwise>
									<option value="${typeList}">${typeList}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<td>Severity :</td>
				<td>
					<select id="moduleFilter" name="severity" onchange="filterResult()" style="font-size: 14px">
						<option value="">Select</option>
						<c:forEach items="${filterVO.severityList}" var="severityList">
							<c:choose>
								<c:when test="${filterVO.severity==severityList}">
									<option value="${severityList}" SELECTED>${severityList}</option>
								</c:when>
								<c:otherwise>
									<option value="${severityList}">${severityList}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
	</div>
</form>
<h3 style="height:20px;width:250px;float:left">${filterVO.groupName}</h3>
<div style="text-align:right"><a href="#" onclick="javascript:goHome();"><img src="<%=request.getContextPath()%>/images/home.jpg"/></a></div>
<hr style="clear:both"/>
<div>
	<input type="button" value="Create New Issue" onclick="createItem('${filterVO.groupId}')"/>
</div>
<div>
	<c:choose>
		<c:when test="${not empty itemsList}">
			<%
				SimpleDateFormat sdf	=	new SimpleDateFormat("dd-MMM-yyyy");
			%>
			<display:table id="defectsTable" style="border: 1px solid #000;width: 100%;align:center;"
                    name="itemsList"  export="true"
                    requestURI='/getGroup.do' clearStatus="true">
	            <display:column title="No" sortable="true" value="${defectsTable_rowNum}"/>
	            <display:column property="itemReference" title="Reference No" sortable="true" media="excel pdf"/>
	            <display:column title="Reference No" media="html">
	            	<a href="#" onclick="javascript:addRemarksPop('${defectsTable.itemId}')">${defectsTable.itemReference}</a>
	            </display:column>
	            <display:column property="itemDesc" title="Description" sortable="true"  media="excel pdf"/>
	            <display:column title="Description" media="html">
	            	${defectsTable.itemDesc}
	            	<c:if test="${not empty defectsTable.itemAttachments}">
	            		<strong> Attachments :</strong>
						<c:forEach items="${defectsTable.itemAttachments}" var="itemAttachments">
							<a href="<%=request.getContextPath()%>/getAttachment.do?attachmentId=${itemAttachments.attachmentId}">${itemAttachments.attachmentName}</a><br/>
						</c:forEach>
	            	</c:if>
	            </display:column>
	            <display:column property="itemSteps" title="Steps" />
	            <display:column property="severity" title="Severity" sortable="true"  />
	            <display:column property="module" title="Module" sortable="true"  />
	            <display:column property="subModule" title="Sub Module" sortable="true"  />
	            <display:column property="assignedTo" title="Assigned To" sortable="true"  />
	            <display:column property="itemType" title="Type" sortable="true"  />
	            <display:column property="status" title="Status" sortable="true"  />
	            <display:column title="Remarks" media="html">
					<div id="remarksDiv">
						<c:forEach var="itemLogsList" items="${defectsTable.itemLogs}">
							<c:if test="${itemLogsList.displayFlag && (itemLogsList.remarks!=''||not empty itemLogsList.logAttachments)}">
								<span>
									&#60;<fmt:formatDate pattern="dd MMM" value="${itemLogsList.createTS}"/>-${itemLogsList.loggedBy}&#62; ${itemLogsList.remarks}
									<c:if test="${not empty itemLogsList.logAttachments}">
										<c:forEach items="${itemLogsList.logAttachments}" var="logAttachments">
											<a href="<%=request.getContextPath()%>/getAttachment.do?attachmentId=${logAttachments.attachmentId}">${logAttachments.attachmentName}</a>
										</c:forEach>
									</c:if>
								</span><br/>
							</c:if>
						</c:forEach>
					</div>
	            </display:column>
	            <display:column property="lastChangeTS" title="Last Changed" sortable="true" format="{0,date,dd-MMM-yyyy}"/>
            </display:table>
		</c:when>
		<c:otherwise>
			No Items available.
		</c:otherwise>
	</c:choose>
</div>	
<div>
	<input type="button" value="Create New Issue" onclick="createItem('${filterVO.groupId}')"/>
</div>