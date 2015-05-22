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
		<img src="Sub06/img/content-2/subtop6-2.PNG" width="754px" />
	</div>
	<div class="rightContent">
		<form action="MailServlet" method="post">
			<label for="id"> 아이디를 입력하세요 </label> <input type="text" id="id"
				name="id" /> <input type="hidden" name="idx" value="pwd" /> <input
				type="submit" value="비밀번호 찾기" />
		</form>
	</div>
</body>
</html>