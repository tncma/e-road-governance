<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<form:form cssClass="wufoo" action="saveUser.do" modelAttribute="userVO"
	method="post">

	<div class="info">
		<h2>Add a User</h2>
	</div>


	<ul>
		<li class="section first">
			<h3>Complaining Person's Details</h3>
		</li>

		<li><label class="desc">Name</label> <span> <form:input
					cssClass="field text" size="8" value="" path="firstName" /> <label>First
					<span class="req">*</span>
			</label>
		</span> <span> <form:input cssClass="field text" size="14" value=""
					path="lastName" /> <label>Last</label>
		</span></li>

		<li><label class="desc">Phone <span class="req">*</span></label>
			<span> <form:input cssClass="field text large" maxlength="10"
					value="" path="contactNo" />
		</span></li>

		<li><label class="desc">Email <span class="req">*</span></label> <span> <form:input
					cssClass="field text large" maxlength="35" value="" path="email" />
		</span></li>
		
		<li class="section">
			<h3>POC Details</h3>
		</li>

		<li>
			<div>
				<span class="right"> <form:select path="issueTypeId"
						cssClass="field select addr" id="issueTypeId">
						<option value="">Select</option>
						<form:options items="${userVO.issueTypes}"
							itemLabel="issueTypeName" itemValue="issueTypeId" />
					</form:select> <label>POC <span class="req">*</span></label>
				</span>
			</div>
			<p class="instruct">Please choose the complaint type.</p>
		</li>
		<li>
			<div>
				<span class="right"> <form:select path="cityId"
						cssClass="field select addr" id="cityId">
						<option value="">Select</option>
						<form:options items="${userVO.cities}" itemLabel="cityName"
							itemValue="cityId" />
					</form:select> <label>City <span class="req">*</span></label>
				</span>
			</div>
		</li>

		<li class="buttons"><input id="saveForm" class="btTxt"
			type="submit" value="Submit" /></li>
	</ul>

</form:form>