<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
		<div id="header">
			<div style="width:600px;float:left;cursor:pointer" onclick="goHome();">
				<h1>
					Tamil Nadu e-Grievance Portal
				</h1>
			</div>
			<div style="float:right;padding-right:0px;">
				<c:if test="${sessionScope.userVO != null}" > Welcome <c:out value="${sessionScope.userVO.firstName}" /><font size="1">[<a href="logout.do">Logout</a>]</font><br/></c:if>
					<span><%=new SimpleDateFormat("MMMMM d, yyyy").format(new Date())%></span>					
			</div>
			<br/>
		</div>