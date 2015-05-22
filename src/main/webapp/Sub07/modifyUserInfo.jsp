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
<script type="text/javascript">
	function pwdSubmit() {
		var dataString = {
			"pwd" : jQuery("#pwd").val(),
			"idx" : "modifyUserInfo"
		};
		jQuery.ajax({
			type : "POST",
			url : "ConfirmServlet",
			data : dataString,
			success : function(data) {
				jQuery("#div_change").html(data);
			}
		});
	}

	jQuery(document).ready(function($) {
		
		// 검색  Enter 시
		$("#pwd").keypress(function(e){
			if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
				pwdSubmit();
				return false;
			} else
				return true;				
		});
	});
</script>
<body>
	<div>
		<img src="Sub07/img/content-4/subtop7-4.PNG" width="754px" />
	</div>
	<div class="rightContent">
		<form action="UpdateServlet" method="post">
			<div id="div_change">
				<label for="pwd">비밀번호</label> <input type="password" name="pwd"
					id="pwd" class="text_field" maxlength="20" title="비밀번호를 입력하세요" />
				<input type="button" onclick="pwdSubmit()" value="확인" />
			</div>
		</form>
	</div>
</body>
</html>