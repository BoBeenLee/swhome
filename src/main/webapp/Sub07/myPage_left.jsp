<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<img src="Sub07/img/sub/title1.PNG" />
		<!-- 좌측메뉴시작 -->
		<a href="#mypage?req=1"
			<c:if test="${ userInfo.codeName != '학생' && userInfo.codeName != '학생회' }"> style="display: none;" </c:if>>이벤트</a><a href="#mypage?req=2">쪽지함</a><a href="#mypage?req=3">비밀번호변경</a><a
			href="#mypage?req=4"
			<c:if test="${ userInfo.codeName == '관리자' }"> style="display: none;" </c:if>>회원정보수정</a><a href="#mypage?req=5"
			<c:if test="${ userInfo.codeName == '관리자' }"> style="display: none;" </c:if>>회원탈퇴</a><a href="#mypage?req=6"
			<c:if test="${ userInfo.codeName != '관리자' }"> style="display: none;" </c:if>>관리자</a>
		<!-- 좌측메뉴끝-->
		<img src="Sub07/img/sub/down.PNG" />
	</div>
</body>
</html>