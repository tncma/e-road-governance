<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Tamil Nadu eGrievance Portal</title>		
		<script>
		var realpath	=	"<%=request.getContextPath()%>";
		</script>
	  	<link media="screen" type="text/css" href="<%=request.getContextPath()%>/css/tracker.css" rel="stylesheet">
	  	<link media="screen" type="text/css" href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/structure.css" type="text/css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/form.css" type="text/css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/theme.css" type="text/css" />
	  	<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/roadgovern.js">
	  	</script>
	</head>
	<body>
		<div id="container">			
				<tiles:insertAttribute name="header"/>		
			
				<tiles:insertAttribute name="menu"/>
		
			<div id="content">
				<tiles:insertAttribute name="body"/>
			</div>
			<div class="clear"></div>
						
				<tiles:insertAttribute name="footer"/>
		
		</div>
	</body>
</html>
	