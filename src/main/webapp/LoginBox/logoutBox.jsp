<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="LoginBox/css/logoutStyle.css" />
<title>Insert title here</title>
</head>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		<%  
			String faultNum = request.getParameter("faultNum");
		
			if(faultNum != null){
				if(faultNum.equals("1")){
					out.println("alert('아이디가 잘못되었어 ^^');");
					out.println("$('#login_id').focus();");
				} else if(faultNum.equals("2")){
					out.println("alert('비밀번호가 잘못되었어 ^^');");
					out.println("$('#login_ps').focus();");
				}
			}
		%>

		// 검색  Enter 시
		$("#login_id, #login_ps").keypress(function(e){
			if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
				loginSubmit();
				return false;
			} else
				return true;				
		});
	});

	function loginSubmit() {
		var $form_wrapper = jQuery("#form_wrapper");
		//$('input[type="submit"]').attr(disable, true);
		// jQuery('input[type="submit"]').attr("disabled", "disabled");
		
		var dataString = {
			"log" : "in",
			"userid" : jQuery("#login_id").val(),
			"password" : jQuery("#login_ps").val()
		};//JSON.stringify(new data_login($("#login_id").val(), $("#login_ps").val()));

		//alert(dataString);
		jQuery.ajax({
			type : "POST",
			url : "LoginServlet",
			data : dataString,
			success : function(data) {
				$form_wrapper.fadeOut(400, function() { // 애니메이션 효과
					$form_wrapper.html(data); // 값 넣는 부분
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
	}
/*	function data_login(userid, password) {
		this.log = "in";
		this.userid = userid;
		this.password = password;
	} */
</script>
<body>
	<img alt="로그인창" src="LoginBox/img/login.png">
	<form class="logoutBox">
		<!-- main -->
		<div>
			<div style="display: inline-block">
				<div style="width: 120px; height: 30px; margin-left: 20px;">
					<input id="login_id" type="text" title="아이디"/>
				</div>
				<div style="width: 120px; height: 30px; margin-left: 20px;">
					<input id="login_ps" type="password" title="비밀번호"/>
				</div>
			</div>
			<div
				style="margin-right: 15px; float: right; width: auto; height: auto; ">
				<a href="javascript:void(0)" onclick="loginSubmit()">
				<img alt="로그인버튼" src="LoginBox/img/loginbutton.png" /></a>
			</div>
		</div>
		<div style="float: left; margin-top: 2px; margin-left: 10px;">
			<a href="#user?req=1" style="margin-left: 10px"><img src="LoginBox/img/join.png" alt="회원가입"/></a><a
				href="#user?req=2" style="margin-left: 10px"><img src="LoginBox/img/such.png" alt="아이디-비밀번호찾기"/></a>
		</div>
	</form>
</body>
</html>