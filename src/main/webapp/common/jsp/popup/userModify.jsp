<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="data.vo.user.StudentUnion,data.bo.UserBO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="codeId" value='<%=request.getParameter("codeId")%>' />

<%
	String codeId = request.getParameter("codeId");

	if (codeId.equals("4")) { // 4 -> 학생회 코드를 의미한다.
		String userId = request.getParameter("userId");
		UserBO userBo = new UserBO();

		StudentUnion su = userBo.getSelectStudentUnion(userId);
		request.setAttribute("studentUnion", su);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">

.status-message {
  position:absolute; right:10px; top:10px; padding:10px; font-size:14px;
  background-color:#ffffff; border:1px solid #aaaaaa;
}

    </style>
<title>Insert title here</title>
<link rel="stylesheet" href="/skhusw/common/css/rightContent.css"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="http://localhost:8080/skhusw/common/css/smoothness/jquery-ui-1.8.18.custom.css">

<!-- jQuery를 쓰기위한 -->
<script type="text/javascript" src="http://localhost:8080/skhusw/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://localhost:8080/skhusw/common/js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" src="skhusw/common/js/jquery.ui.datepicker-ko.js"></script>
<script type="text/javascript">

jQuery(document).ready(
		function($) {
		var dataString = {
			"codeId" : "<%=request.getParameter("codeId")%>",
			"userId" : "<%=request.getParameter("userId")%>",
			"idx" : "manage"
		};
		jQuery.ajax({
			type : "POST",
			url : "/skhusw/ConfirmServlet",
			data : dataString,
			success : function(data) {
				jQuery("#div_change").html(data);
			}
		});
		  var ops = {
	        changeMonth: true,
	        changeYear: true,
	        showButtonPanel: true,
	        showOn: "button",
	        dateFormat: "yy-mm-dd"
	    };
	    $("#startDate").datepicker(ops);
	    $("#lastDate").datepicker(ops);
	});
	  
</script>
</head>
<body>
	<div>
		<form action="/skhusw/UpdateServlet" method="post">
			<div id="div_change">
				
			</div>
		</form>
		<c:if test="${ codeId != 4 }">
			<form action="/skhusw/InsertServlet" method="post">
				<label>시작일</label><input type="text" name="sDate"
					style="width: 82px" id="startDate" /><label>종료일</label><input type="text"
					name="lDate" id="lastDate" style="width: 82px"> <input type="submit"
					value="생성" /><input type="hidden" name="userId"
					value="<%=request.getParameter("userId")%>" /> <input
					type="hidden" name="idx" value="manage"> <input
					type="hidden" name="type" value="union">
			</form>
		</c:if>
		<c:if test="${ codeId == 4 }">
			학생회 기간 : ${ studentUnion.unionStartDate } - ${ studentUnion.unionLastDate }
			<form action="/skhusw/DeleteServlet" method="post">
				<input type="submit" value="권한취소"><input type="hidden"
					name="idx" value="manage"> <input type="hidden" name="type"
					value="union"><input type="hidden" name="userId"
					value="<%=request.getParameter("userId")%>" />
			</form>
		</c:if>
	</div>
   
	
</body>
</html>
