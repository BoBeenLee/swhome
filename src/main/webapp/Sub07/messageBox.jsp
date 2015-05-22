<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List,data.vo.message.*;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="common/css/rightContent.css"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="common/css/bbs.css" />
<script type="text/javascript" src="common/js/board/jPages.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		// 리스트를 보여주기 위한 처리
		$("div .receive_list_num").jPages({
			containerID : "receive_records",
			previous : "←",
			next : "→",
			perPage : 10,
			delay : 20
		});
		$("div .send_list_num").jPages({
			containerID : "send_records",
			previous : "←",
			next : "→",
			perPage : 10,
			delay : 20
		});
	});

	function allChecked(container, name) {
		var bool = jQuery("#" + name + "allCheck").attr("checked");
		
		//alert(bool);
		if (bool) {
			jQuery("#" + container + " input:checkbox[name=" + name + "]")
					.each(function() {
						jQuery(this).attr("checked", true);
					});
		} else {
			jQuery("#" + container + " input:checkbox[name=" + name + "]")
					.each(function() {
						jQuery(this).attr("checked", false);
					});
		}
	}
	/*
	 // 쪽지 읽기 위한 처리
	 function msgRead(msgType, msgId){
	 var dataString = {"idx" : "messageBox", "msgType" : msgType, "msgId" : msgId};
	 wrapWindowByMask();

	 jQuery("#readArticle").show();

	 jQuery.ajax({
	 type : "POST",
	 url : "SelectServlet",
	 data : dataString,
	 success : function(data) {
	 //alert(data);
	 jQuery("#readArticle").html(data);
	 jQuery("#" + msgId + "chk").html("y"); // 확인란 변경하는 곳
	 }
	 });
	 }

	 // 쪽지 쓰기위한 처리
	 function msgWrite(receiverId){
	 jQuery('.window').hide();
	 //alert("Hello");
	 wrapWindowByMask();
	 //윈도우 같은 거 띄운다.       
	
	 jQuery('#writeArticle').show();

	 jQuery.ajax({
	 type : "GET",
	 url : "common/jsp/message/msgWrite.jsp",
	 success : function(data){
	 jQuery("#writeArticle").html(data);
	 jQuery('#receiverId').val(receiverId);
	 }
	 });	
	 }*/
</script>

<body>
	<div>
		<img src="Sub07/img/content-2/subtop7-2.PNG" width="754px" />
	</div>
	<div align="center" class="rightContent">
		<!-- 메시지 받는 저장소 -->
		<div id="receive_container" style="margin: 10px;" class="bbs">
			<h2>받은 쪽지함</h2>
			<form action="DeleteServlet" method="post">
				<table>
					<thead>
						<tr>
							<th width="10"><input id="receiveCheckBoxallCheck"
								type="checkbox"
								onclick="allChecked('receive_records', 'receiveCheckBox')" /></th>
							<th width="120">보낸 ID</th>
							<th width="120">보낸사람</th>
							<th width="290">내용</th>
							<th width="70">받은시간</th>
							<th width="30">확인</th>
						</tr>
					</thead>
					<tbody id="receive_records">
						<c:forEach items="${ receiveMessageList }" var="receiveMsg">
							<tr>
								<td><input type="checkbox" name="receiveCheckBox"
									value="${ receiveMsg.receiveMessageId }" ></td>
								<td>${ receiveMsg.senderId }</td>
								<td>${ receiveMsg.senderName }</td>
								<td><div
										style="text-overflow: ellipsis; white-space: nowrap; width: 200px; overflow: hidden;">
										<a
											href="#mypage?req=2&type=read&articleId=${ receiveMsg.receiveMessageId }&box=receive">${
											receiveMsg.receiveContent }</a>
									</div></td>
								<td>${ receiveMsg.receiveDate }.</td>
								<td id="${ receiveMsg.receiveMessageId }chk">${
									receiveMsg.messageYn }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="receive_list_num"></div>
				<input type="button" id="writeButton" value="쪽지쓰기"
					onclick="javascript_:window.document.location.href='#mypage?req=2&type=write&box=receive'" />
				<input type="submit" value="쪽지삭제" /> <input type="hidden"
					name="idx" value="messageBox" /> <input type="hidden" name="box"
					value="receive" />
			</form>
		</div>
		<hr><hr>
		<!-- 메시지 보낸 저장소 -->
		<div id="send_container" style="margin: 10px;" class="bbs">
			<h2>보낸 쪽지함</h2>
			<form action="DeleteServlet" method="post">
				<table>
					<thead>
						<tr>
							<th width="10"><input id="sendCheckBoxallCheck"
								type="checkbox"
								onclick="allChecked('send_records', 'sendCheckBox')" /></th>
							<th width="120">받는 ID</th>
							<th width="120">받는사람</th>
							<th width="320">내용</th>
							<th width="70">보낸시간</th>
						</tr>
					</thead>
					<tbody id="send_records">
						<c:forEach items="${ sendMessageList }" var="sendMsg">
							<tr>
								<td><input type="checkbox" name="sendCheckBox"
									value="${ sendMsg.sendMessageId }"></td>
								<td>${ sendMsg.receiverId }</td>
								<td>${ sendMsg.receiverName }</td>
								<td><div
										style="text-overflow: ellipsis; white-space: nowrap; width: 200px; overflow: hidden;">
										<a
											href="#mypage?req=2&type=read&articleId=${ sendMsg.sendMessageId }&box=send">${
											sendMsg.sendContent }</a>
									</div></td>
								<td>${ sendMsg.sendDate }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="send_list_num"></div>
				<input type="submit" value="쪽지삭제" /> <input type="hidden"
					name="idx" value="messageBox" /> <input type="hidden" name="box"
					value="send" />
			</form>
		</div>
	</div>
</body>
</html>