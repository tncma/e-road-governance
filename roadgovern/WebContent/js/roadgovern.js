
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
};

function goHome(){
	document.location.href=realpath+"/index.do";
}


function filterResult(){
	document.forms[0].action=realpath+"/getDashboard.do";
	document.forms[0].submit();
}

function createItem(){
	document.location.href=realpath+"/createComplaint.do?stateId=1&districtId=0&cityId=0";
}

function addRemarks(itemId){
	document.location.href=realpath+"/addRemarks.do?itemId="+itemId;
}

function addRemarksPop(itemId){
	
	var url	=	realpath+"/addRemarks.do?itemId="+itemId;
	window.open(url,'popUpWindow','height=700,width=1000,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes');
}

function saveRemarks(){

	var itemUpdatedBy	=	document.getElementById("itemUpdatedBy").value;
	if(itemUpdatedBy.trim()==""){
		alert("Logged By is mandatory.");
	}
	else{
		var r=confirm("Are you sure you want to add Remarks/Change Status of this item? ");
		if (r==true){
			document.forms[0].action=realpath+"/saveRemarks.do";
			document.forms[0].submit();
		}
	}
}


function editItem(itemId){
	document.location.href=realpath+"/editItem.do?complaintId="+itemId;
}

function updateItem(){

	var itemDesc	=	document.getElementById("itemDesc").value;
	var itemUpdatedBy	=	document.getElementById("itemUpdatedBy").value;
	if(itemDesc.trim()==""){
		alert("Item Description is mandatory.");
	}
	else if(itemUpdatedBy.trim()==""){
		alert("Logged By is mandatory.");
	}
	else{
		var r=confirm("Are you sure you want to update this Item? ");
		if (r==true){
			document.forms[0].action=realpath+"/updateItem.do";
			document.forms[0].submit();
		}
	}
}

function refreshItemsList(){
	window.opener.document.forms[0].action=realpath+"/getDashboard.do";
	window.opener.document.forms[0].submit();
}