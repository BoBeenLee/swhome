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
					<th>보낸 사람</th>
					<th>${ msg.senderName }</th>
					<th>보낸 ID</th>
					<th>${ msg.senderId }</th>
				</tr>
				<tr>
					<th colspan="2">받은 날짜</th>
					<th colspan="2">${ msg.receiveDate }</th>
				</tr>
			</table>
			<div class="messageContent">
				<p>${ msg.receiveContent }</p>
			</div>
			<div id="messageMenu">
				<input type="button" value="답장"
					onclick="javascript_:window.document.location.href='#mypage?req=2&type=write&box=receive&receiver=${ msg.senderId }'">
				<input type="button" onclick="history.back()" value="닫기"> <input
					id="receivemsgid" type="hidden" value="${ msg.receiveMessageId }" />
			</div>
		</div>
	</div>
</body>
</html>