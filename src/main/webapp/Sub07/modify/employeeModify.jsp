<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table align="center">
	<tr>
		<th scope="row"><label for="id">아이디</label></th>
		<td><input type="text" name="id" id="id" disabled="disabled" value="${ employee.userId }"/></td>
	</tr>
	<tr>
		<th scope="row"><label for="name">이름</label></th>
		<td><input type="text" name="name" id="name" maxlength="20" title="이름을 입력하세요" value="${ employee.userName }" />
			<br/>
			이름은 2~20자 이내여야 합니다 </td>
	</tr>
	<tr>
		<th scope="row"><label for="email">이메일 주소</label></th>
		<td><input type="text" name="email" id="email" title="이메일 주소를 입력하세요" value="${ employee.userEmail }" />
			<br/>
			메일주소는 메일인증 후 비밀번호 변경이나 찾기등에 사용됩니다. </td>
	</tr>
	<tr>
		<th scope="row"><label for="phone">전화번호</label></th>
		<td><input type="text" name="phone" id="phone" title="전화번호를 입력하세요" value="${ employee.userTel }" /></td>
	</tr>
	<tr>
		<th scope="row"><label for="question">질문</label></th>
		<td><select id="question" name="question">
          <option value="1">Some text goes here</option>
          <option value="2">Another choice could be here</option>
          <option value="3">Yet another item to be chosen</option>
          <option value="4">Some text goes here</option>
          <option value="5">Another choice could be here</option>
          <option value="6">Yet another item to be chosen</option>
          <option value="7">Some text goes here</option>
          <option value="8">Another choice could be here</option>
          <option value="9">Yet another item to be chosen</option>
   </select></td>
	</tr>
		<tr>
		<th scope="row"><label for="answer">대답</label></th>
		<td><input type="text" name="answer" id="answer" value="${ employee.answer }" /></td>
	</tr>
	<tr>
		<th scope="row"><label for="location">사무실 위치</label></th>
		<td><input type="text" name="location" id="location" title="사무실 위치를 입력하세요" value="${ employee.officeLocation }" /></td>
	</tr>
	<tr>
		<th scope="row"><label for="extenstion">외선 번호</label></th>
		<td><input type="text" name="extenstion" id="extenstion" title="외선 번호를 입력하세요" value="${ employee.extensionTel }" /></td>
	</tr>
</table>
	<input type="hidden" name="idx" value="employeeModify"/>
	<input type="submit" value="등록" />
	&nbsp;&nbsp;
	<input type="reset" value="취소"/>
</body>
</html>