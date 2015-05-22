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
		<img src="Sub07/img/content-3/subtop7-3.PNG" width="754px" />
	</div>
	<div class="rightContent">
		<form action="UpdateServlet" method="post">
			<table>
				<tr>
					<th scope="row"><label for="curpwd">현재 비밀번호</label></th>
					<td><input type="password" name="curpwd" id="curpwd"
						maxlength="20" title="비밀번호를 입력하세요" /> <br /> 비밀번호는 6~20자로 되어야
						합니다</td>
				<tr>
					<th scope="row"><label for="pwd1">새 비밀번호</label></th>
					<td><input type="password" name="pwd1" id="pwd1"
						maxlength="20" title="비밀번호를 입력하세요" /> <br /> 비밀번호는 6~20자로 되어야
						합니다</td>
				</tr>
				<tr>
					<th scope="row"><label for="pwd2">새 비밀번호 확인</label></th>
					<td><input type="password" name="pwd2" id="pwd2"
						maxlength="20" title="비밀번호 확인을 입력하세요" /></td>
				</tr>
			</table>
			<input type="hidden" name="idx" value="modifyPw"> <input
				type="submit" value="확인" /> &nbsp;&nbsp; <input type="reset"
				value="취소" />
		</form>
	</div>
</body>
</html>