<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.HashMap;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="LoginBox/css/loginStyle.css" />
<title>Insert title here</title>
</head>
<% 
	HashMap<String, String> userInfo = (HashMap<String, String>)session.getAttribute("userInfo");
%>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		var $form_wrapper = $('#form_wrapper');
		
		$form_wrapper.find("#logout").click(function(e) {
			$.ajax({
				type : "POST",
				url : "LoginServlet",
				data : {"log":"out"},
				success : function(data) {
					$form_wrapper.fadeOut(400, function() {
						$form_wrapper.html(data);
						$form_wrapper.css("display:none");
						$form_wrapper.stop().animate({
							width : $form_wrapper.data('width') + 'px',
							height : $form_wrapper.data('height') + 'px'
						}, 500, function() {
							$form_wrapper.fadeIn(400);
						});
					});
				}
			});
			e.preventDefault();
		});
	});

</script>
<body>
	<img alt="로그인창" src="LoginBox/img/logout.png">
	<form class="loginBox">
		<div class="title">${ userInfo.userName }  ${ userInfo.codeName }</div>
		<div class="square_box" style="width: 65px !important;">
			<div id="realMessageNum" ><a href="#mypage?req=2"><img alt="쪽지" src="LoginBox/img/message.png"></a></div>
		</div>
		<div class="square_box">
			<a <c:if test="${ !(userInfo.codeName == '학생' || userInfo.codeName == '학생회') }">href="#mypage?req=2"</c:if><c:if test="${ userInfo.codeName == '학생' || userInfo.codeName == '학생회' }">href="#mypage?req=1"</c:if>><img alt="마이페이지" src="LoginBox/img/mypage.PNG"></a>
		</div>
		<div class="square_box">
			<a href="javascript:void(0)" id="logout"><img alt="로그아웃" src="LoginBox/img/logoutbutton.png"></a>
		</div>
	</form>
</body>
</html>