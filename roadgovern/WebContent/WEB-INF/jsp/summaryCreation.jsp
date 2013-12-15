<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/structure.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/form.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/theme.css" type="text/css" />


	<div class="info">
		<h2>${successMessage}</h2>
	</div>
	<c:if test="${sessionScope.userVO != null}" >
		<a href="getDashboard.do" class="" target="" name=""><img width="40" vspace="" hspace="" height="40" border="0" align="right" src="images/BackButton.jpg" alt="Back to Dashboard"></a>
	</c:if>
	<c:if test="${sessionScope.userVO == null}" >
		<a href="index.do" class="" target="" name=""><img width="40" vspace="" hspace="" height="40" border="0" align="right" src="images/BackButton.jpg" alt="Home Page"></a>
	</c:if>