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
			<form action="InsertServlet" method="post">
				<table>
					<tr>
						<th width="70px">받는 ID</th>
						<th><input type="text" id="receiverId" name="receiver"
							width="100px" height="10px" style="float: left;"
							value="${ receiverId }" /></th>
					</tr>
				</table>
				<div class="messageContent">
					<textarea name="content" rows="14" cols="6"></textarea>
				</div>
				<div id="messageMenu">
					<input type="submit" value="확인" /> <input type="button" value="취소"
						onclick="history.back()" /> <input type="hidden" name="idx"
						value="messageBox" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>