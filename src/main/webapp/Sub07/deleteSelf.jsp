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
<body>
	<div>
		<img src="Sub07/img/content-5/subtop7-5.PNG" width="754px" />
	</div>
	<div class="rightContent">
		<form action="DeleteServlet" method="post">
			<label for="pwd">비밀번호</label> <input type="password" name="pwd"
				id="pwd" class="text_field" maxlength="20" title="비밀번호를 입력하세요" /> <input
				type="hidden" name="idx" value="deleteSelf" /> <input type="submit"
				value="확인" />
		</form>
	</div>
</body>
</html>