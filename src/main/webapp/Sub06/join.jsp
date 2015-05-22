<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="common/css/rightContent.css"
	type="text/css" />
<script type="text/javascript" src="Sub06/js/jquery.validate.min.js"></script>
<script type="text/javascript">
	jQuery(document)
			.ready(
					function($) {
<%/*
						String resMsg = request.getParameter("resMsg");
						
						if(resMsg != null)
							out.println("alert('" + resMsg + "')"); */%>
	// 학생, 교수, 직원 선택시
						$("form p :radio[name='joinGroup']")
								.click(function() {
									switch ($(this).val()) {
											case "s":
												$.ajax("Sub06/join/studentJoin.html").done(
																function(data) {
																	$("#check_user").html(data);
																});
												break;
											case "p":
												$.ajax("Sub06/join/professorJoin.html").done(
																function(data) {
																	$("#check_user").html(data);
																});
												break;
											case "e":
												$.ajax("Sub06/join/employeeJoin.html").done(
																function(data) {
																	$("#check_user").html(data);
																});
												break;
											}
										});
						$("#firstJoin").trigger("click");

						$("#signupForm")
								.validate(
										{
											rules : {
												id : {
													required : true,
													minlength : 6,
													maxlength : 20
												},
												name : {
													required : true,
													minlength : 2,
													maxlength : 20
												},
												pwd1 : {
													required : true,
													minlength : 6,
													maxlength : 20
												},
												pwd2 : {
													required : true,
													minlength : 6,
													maxlength : 20,
													equalTo : "#pwd1"
												},
												email : {
													required : true,
													email : true
												}
											},
											messages : {
												id : {
													required : " Please enter a id",
													minlength : " Your Id must consist of at least 6 Characters",
													maxlength : " Your Id must consist of at most 20 Characters"
												},
												name : {
													required : " Please enter a username",
													minlength : " Your Id must consist of at least 2 Characters",
													maxlength : " Your Id must consist of at most 20 Characters"
												},
												pwd1 : {
													required : " Please provide a password",
													minlength : " Your password must be at leat 6 characters long",
													maxlength : " Your Id must consist of at most 20 Characters"
												},
												pwd2 : {
													required : " Please provide a password",
													minlength : " Your password must be at leat 6 characters long",
													maxlength : " Your Id must consist of at most 20 Characters",
													equalTo : " Please enter the same password as above"
												},
												email : " Please enter a valid email address"
											}
										});
					});
</script>

<body>
	<div>
		<img src="Sub06/img/content-1/subtop6-1.PNG" width="754px" />
	</div>
	<div class="rightContent">
		<form action="InsertServlet" method="post" id="signupForm">
			<p>
				<input type="radio" id="firstJoin" name="joinGroup" value="s"
					checked /> <label for="joinGroup"> 학생 </label> &nbsp; &nbsp; <input
					type="radio" name="joinGroup" value="p" /> <label for="joinGroup">
					교수 </label> &nbsp; &nbsp; <input type="radio" name="joinGroup" value="e" />
				<label for="joinGroup"> 직원</label>
			</p>
			<div id="check_user"></div>
			<input type="submit" value="등록" /> &nbsp;&nbsp; <input type="reset"
				value="취소" /> <input type="hidden" name="idx" value="join">
		</form>
	</div>
</body>
</html>