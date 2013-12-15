<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript">
function selectCity(cityId){
	document.location.href="<%=request.getContextPath()%>/createComplaint.do?stateId=${stateId}&districtId=${districtId}&cityId="+ cityId;
}
</script>
<div
	style="text-align: center; width: 519px; margin-left: auto; margin-right: auto;">
	<img id="Image-Maps_4201312140440523"
		src="<%=request.getContextPath()%>/images/district_${districtId}.png"
		usemap="#Image-Maps_4201312140440523" border="0" width="519"
		height="534" alt="" />
	<map id="_Image-Maps_4201312140440523"
		name="Image-Maps_4201312140440523">
		<area shape="poly"
			coords="128,157,119,166,113,178,110,195,109,206,124,213,135,219,149,226,170,229,183,235,194,243,197,251,207,262,224,257,239,255,254,249,270,242,276,228,281,213,276,200,277,187,282,171,286,153,294,141,300,132,294,123,280,116,275,106,261,100,259,92,260,77,253,76,240,78,230,85,211,86,205,94,194,99,188,95,187,109,172,113,165,116,154,119,147,124,141,131,139,137,146,144,158,150,168,149,157,158,141,160,"
			alt="Coimbatore" title="Coimbatore" href="javascript:selectCity('1')" />

		<area shape="poly"
			coords="298,134,288,144,282,159,282,171,276,189,277,209,274,235,293,232,312,233,318,248,328,255,342,265,354,277,373,274,387,273,391,256,388,245,376,246,371,238,368,229,364,221,359,215,372,212,390,210,396,197,395,187,404,177,416,179,413,167,412,166,425,162,427,153,410,152,392,149,386,154,382,146,375,138,369,131,375,125,359,119,345,114,342,105,333,94,323,90,311,95,312,107,316,118,311,128,302,126,"
			href="javascript:selectCity('3')" alt="Palladam" title="Palladam" />
	</map>

</div>