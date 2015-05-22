<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="LoginBox/css/style.css" />
<%
	//System.out.println(session.getAttribute("userInfo"));
%>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$.ajax({
			type : "POST",
			url : "LoginServlet",
			data : {"log":"none"},
			success : function(data) {
			//	alert(data);
				$("#form_wrapper").html(data);
			}
		});
	});
</script>
<div id="form_wrapper"></div>
