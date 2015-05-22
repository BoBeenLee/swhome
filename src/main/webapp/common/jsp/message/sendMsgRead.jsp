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
<link rel="stylesheet" type="text/css" href="common/css/message.css" />
<body>
	<div>
		<img src="Sub07/img/content-2/subtop7-2.PNG" width="754px" />
	</div>
	<div class="rightContent">
		<div class="message">
			<table>
				<tr>
					<th>받는 사람</th>
					<th>${ msg.receiverName }</th>
					<th>받는 ID</th>
					<th>${ msg.receiverId }</th>
				</tr>
				<tr>
					<th colspan="2">보낸 날짜</th>
					<th colspan="2">${ msg.sendDate }</th>
				</tr>
			</table>
			<div class="messageContent">
				<p>${ msg.sendContent }</p>
			</div>
			<div id="messageMenu">
				<input type="button" value="쪽지쓰기"
					onclick="callArticle('messageBoxReceive', '', 'write', '${ msg.receiverId }')">
				<input type="button" class="close" value="닫기">
			</div>
		</div>
	</div>
</body>
</html>