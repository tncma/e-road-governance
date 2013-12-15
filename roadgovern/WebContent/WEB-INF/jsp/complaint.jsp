<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript">
function submitComplaint(cityId){
	document.location.href="<%=request.getContextPath()%>/selectCity.do?stateId=${stateId}&districtId=${districtId}&cityId="+ cityId;
}
</script>

<form:form cssClass="wufoo" action="submitComplaint.do" modelAttribute="complaintVO"  method="post" enctype="multipart/form-data">

	<div class="info">
		<h2>Log a complaint</h2>
	</div>

	<ul>

	<li class="section first">
		<h3>Complaint Location</h3>
		<div>Please specify district, city, locality of the complaint</div>
	</li>

	<li>
	<div style="display:none">
		<span class="right">
		<form:select path="stateId" cssClass="field select addr" id="states">
			<form:options items="${complaintVO.states}" itemLabel="stateName" itemValue="stateId"/>
		</form:select>
		<label>State <span class="req">*</span></label>
		</span>
	</div>
	<div style="float:left;">
		<span class="right">
		<form:select path="districtId" cssClass="field select addr" id="districts">
			<form:options items="${complaintVO.districts}" itemLabel="districtName" itemValue="districtId"/>
		</form:select>
		<label>District <span class="req">*</span></label>
		</span>
	</div>
	<div style="float:left;margin-left:50px;">
		<span class="right">
		<form:select path="cityId" cssClass="field select addr" id="cities">
			<form:options items="${complaintVO.cities}" itemLabel="cityName" itemValue="cityId"/>
		</form:select>
		<label>City <span class="req">*</span></label>
		</span>
	</div>
	<div style="float:left;margin-left:50px;">
		<span class="right">
		<form:select path="roadId" cssClass="field select addr" id="roads">
			<option value="">Select</option>
			<form:options items="${complaintVO.roads}" itemLabel="roadName" itemValue="roadId"/>
		</form:select>
		<label>Street/Locality <span class="req">*</span></label>
		</span>
	</div>
	<p class="instruct">If you are not able to find your street/locality name, please provide the nearest locality name here and specify the full address in details section</p>
	<br style="clear:both">
	</li>

	<li class="section">
		<h3>Complaint Details</h3>
	</li>

	<li>
		<div>
			<span class="right">
			<form:select path="issueTypeVO.issueTypeId" cssClass="field select addr" id="issueTypeId">
				<option value="">Select</option>
				<form:options items="${complaintVO.issueTypes}" itemLabel="issueTypeName" itemValue="issueTypeId"/>
			</form:select>
			<label>Complaint Type <span class="req">*</span></label>
			</span>
		</div>
		<p class="instruct">Please choose the complaint type.</p>
	</li>
	<li>
		<label class="desc">Details of Complaint  <span class="req">*</span></label>
		<div>
		<textarea rows="10" cols="50" class="field textarea medium" name="complaintDesc">Garbage has not been cleared in my area for past 3 days. The bins are full and the garbage is piled up on the road side. The place is turning out to be a breeding place for mosquitoes. Requesting the authoriities to quickly react.</textarea>
		</div>

		<p class="instruct">Please give full details of the complaint. Please be precise and clear.</p>
	</li>
	
	<li class="complex">
		<label class="desc">File Upload</label>
			<div>
			<span><form:input path="attachmentVO.fileData" type="file"/>(Max File Size - 16mb)</span>
			</div>
			<p class="instruct">If you have photos related to the complaint, it will help the authorities to quickly react.</p>
		</li>

	<li class="section">
		<h3>Complaining Person's Details</h3>
	</li>

	<li>
	<label class="desc">Name</label>

		<span>
		<form:input cssClass="field text" size="8" value="" path="complaintUser.firstName"/>
		<label>First <span class="req">*</span></label>
		</span>

		<span>
		<form:input cssClass="field text" size="14" value="" path="complaintUser.lastName"/>
		<label>Last</label>
		</span>
	</li>

	<li>
	<label class="desc">Phone <span class="req">*</span></label>
		<span>
		<form:input cssClass="field text large" maxlength="10" value="" path="complaintUser.contactNo"/>
		</span>
	</li>
	
	<li>
	<label class="desc">Email </label>
		<span>
		<form:input cssClass="field text large" maxlength="35" value="" path="complaintUser.email"/>
		</span>
	</li>
	
	<li class="complex">
	<label class="desc">Address</label>
	<div>
		<span class="full">
		<form:input value=""   cssClass="field text addr"  path="complaintUser.address1"/>
		<label style="float:right">Address Line 1 </label>
		</span>

		<span class="full">
		<form:input cssClass="field text addr" value=""  path="complaintUser.address2"/>
		<label style="float:right">Address Line 2</label>
		</span>

		<span class="left">
		<form:input cssClass="field text addr" value=""  path="complaintUser.city"/>
		<label>City</label>
		</span>

		<span class="right">
		<form:input cssClass="field text addr" value="Tamil Nadu"  path="complaintUser.state"/>
		<label>State / Province / Region</label>
		</span>

		<span class="left">
		<form:input cssClass="field text addr"  maxlength="15" value=""  path="complaintUser.postalCode"/>
		<label>Postal / Zip Code</label>
		</span>
	</div>
			<p class="instruct">Please provide your address</p>
	</li>
	
		<li class="buttons">
			<input id="saveForm" class="btTxt" type="submit" value="Submit" />
		</li>
	</ul>

</form:form>