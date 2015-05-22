<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${ boardId == 1 }">
		<c:set var="idxUrl" value="#admin?req=1" />
	</c:when>
	<c:when test="${ boardId == 2 }">
		<c:set var="idxUrl" value="#admin?req=2" />
	</c:when>
	<c:when test="${ boardId == 3 }">
		<c:set var="idxUrl" value="#admin?req=3" />
	</c:when>
	<c:when test="${ boardId == 4 }">
		<c:set var="idxUrl" value="#info?req=2" />
	</c:when>
	<c:when test="${ boardId == 8 }">
		<c:set var="idxUrl" value="#board?req=5" />
	</c:when>
	<c:when test="${ boardId == 5 }">
		<c:set var="idxUrl" value="#board?req=1" />
	</c:when>
	<c:when test="${ boardId == 9 }">
		<c:set var="idxUrl" value="#board?req=3" />
	</c:when>
	<c:when test="${ boardId == 10 }">
		<c:set var="idxUrl" value="#asso?req=1" />
	</c:when>
	<c:when test="${ boardId == 7 }">
		<c:set var="idxUrl" value="#board?req=4" />
	</c:when>
	<c:when test="${ boardId == 6 }">
		<c:set var="idxUrl" value="#board?req=2" />
	</c:when>
</c:choose>

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
	jQuery(document).ready(
			function($) {
				//  -------------------------------- Article
				$("#manageArticle_list").jPages({
					containerID : "manageArticle_records",
					previous : "←",
					next : "→",
					perPage : 10,
					delay : 20
				});
				$("#articlePageNum").change(function() {
					var newPageNum = parseInt($(this).val());

					$("#manageArticle_list").jPages("destroy").jPages({
						containerID : "manageArticle_records",
						previous : "←",
						next : "→",
						perPage : newPageNum,
						delay : 20
					});
				});

				//  -------------------------------- User
				$("#manageUser_list").jPages({
					containerID : "manageUser_records",
					previous : "←",
					next : "→",
					perPage : 10,
					delay : 20
				});
				$("#userPageNum").change(function() {
					var newPageNum = parseInt($(this).val());

					$("manageUser_list").jPages("destroy").jPages({
						containerID : "manageUser_records",
						previous : "←",
						next : "→",
						perPage : newPageNum,
						delay : 20
					});
				});

				//  -------------------------------- Calendar

				$("#manageCalendar_list").jPages({
					containerID : "manageCalendar_records",
					previous : "←",
					next : "→",
					perPage : 10,
					delay : 20
				});
				$("#calendarPageNum").change(function() {
					var newPageNum = parseInt($(this).val());

					$("manageCalendar_list").jPages("destroy").jPages({
						containerID : "manageCalendar_records",
						previous : "←",
						next : "→",
						perPage : newPageNum,
						delay : 20
					});
				});

				// 검색 할 시...
				$("#userNum, #articleNum").change(function() {
					var url = "#mypage?req=6&";
					var userGroup = $("#userNum").val();
					var boardId = $("#articleNum").val();
					var sDate = $("#sDate").val();
					var lDate = $("#lDate").val();
					var articleSearchType = $("#articleSearchType").val();
					var userSearchType = $("#userSearchType").val();
					var articleSearchText = encodeURIComponent($("#articleSearchText").val());
					var userSearchText = encodeURIComponent($("#userSearchText").val());
					
					url += "userGroup=" + userGroup + "&";
					url += "boardId=" + boardId + "&";
					url += "sDate=" + sDate + "&";
					url += "lDate=" + lDate + "&";
					url += "articleSearchType=" + articleSearchType + "&";
					url += "userSearchType=" + userSearchType + "&";
					url += "articleSearchText=" + articleSearchText + "&";
					url += "userSearchText=" + userSearchText;

					window.document.location.href = url;
				});
				$("#calendarSearch, #userSearch, #articleSearch").click(function(e) {
					var url = "#mypage?req=6&";
					var userGroup = $("#userNum").val();
					var boardId = $("#articleNum").val();
					var sDate = $("#sDate").val();
					var lDate = $("#lDate").val();
					var articleSearchType = $("#articleSearchType").val();
					var userSearchType = $("#userSearchType").val();
					var articleSearchText = encodeURIComponent($("#articleSearchText").val());
					var userSearchText = encodeURIComponent($("#userSearchText").val());
					
					url += "userGroup=" + userGroup + "&";
					url += "boardId=" + boardId + "&";
					url += "sDate=" + sDate + "&";
					url += "lDate=" + lDate + "&";
					url += "articleSearchType=" + articleSearchType + "&";
					url += "userSearchType=" + userSearchType + "&";
					url += "articleSearchText=" + articleSearchText + "&";
					url += "userSearchText=" + userSearchText;
					
					window.document.location.href = url;
				});
			});

	function popup(url){
		var setting = "width=780px, height=400px";
		window.open("<%=request.getContextPath()%>/" + url, "", setting);
	}
</script>
<body>
	<div>
		<img alt="관리자 페이지" src="Sub07/img/content-6/subtop7-6.PNG" width="754px">
	</div>
	<div class="rightContent">
		<div id="manageArticle_container" class="bbs">
			<table>
				<thead>
					<tr>
						<th width="60px">번호</th>
						<th width="600px">제목</th>
						<th width="80px">작성자</th>
						<th width="84px">작성일자</th>
						<th width="60">수정</th>
						<th width="60">삭제</th>
					</tr>
				</thead>
				<tbody id="manageArticle_records">
					<c:if test="${ boardType == 'nor' }">
						<c:forEach items="${ articleList }" var="article" varStatus="num">
							<tr>
								<td id="${ article.norArticleId }">${articleSize -
									num.count + 1}</td>
								<td style="text-align: left !important;"><a
									href="${ idxUrl }&articleId=${ article.norArticleId }&type=read">${
										article.norArticleTitle } [${ article.commentCount }]</a></td>
								<td>${ article.userName }</td>
								<td>${ article.norArticleDate }</td>
								<td><form name="norForm">
										<input type="button" value="수정"
											onclick="popup('common/jsp/popup/norArticleModify.jsp?idx=manage&type=${ boardType }&articleId=${ article.norArticleId }')" />
									</form></td>
								<td><form action="DeleteServlet" method="post">
										<input type="submit" value="삭제" /><input type="hidden"
											name="articleId" value="${ article.norArticleId }" /><input
											type="hidden" name="idx" value="manage" /><input
											type="hidden" name="type" value="${ boardType }" /><input
											type="hidden" name="userGroup" value="${ userGroup }" /><input
											type="hidden" name="boardId" value="${ boardId }" /><input
											type="hidden" name="sDate" value="${ sDate }"> <input
											type="hidden" name="lDate" value="${ lDate }">
									</form></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${ boardType == 'part' }">
						<c:forEach items="${ articleList }" var="article" varStatus="num">
							<tr>
								<td id="${ article.partArticleId }">${articleSize -
									num.count + 1}</td>
								<td style="text-align: left !important;"><a
									href="${ idxUrl }&articleId=${ article.partArticleId }&type=read&password=${ article.partPassword }">${
										article.partArticleTitle } [${ article.commentCount }]</a></td>
								<td>${ article.userName }</td>
								<td>${ article.partArticleDate }</td>
								<td><input type="button" value="수정" onclick="popup('common/jsp/popup/partArticleModify.jsp?idx=manage&type=${ boardType }&articleId=${ article.partArticleId }')" /></td>
								<td><form action="DeleteServlet" method="post">
										<input type="submit" value="삭제" /><input type="hidden"
											name="articleId" value="${ article.partArticleId }" /><input
											type="hidden" name="idx" value="manage" /><input
											type="hidden" name="type" value="${ boardType }" /><input
											type="hidden" name="userGroup" value="${ userGroup }" /><input
											type="hidden" name="boardId" value="${ boardId }" /><input
											type="hidden" name="sDate" value="${ sDate }"> <input
											type="hidden" name="lDate" value="${ lDate }">
									</form></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		<div id="manageArticle_list" class="bbs_list_num"></div>
		<div align="center">
			<form id="searchList" style="display: inline-block;">
				<select id="articleSearchType">
					<option value="title">title</option>
					<option value="content">content</option>
					<option value="writer">writer</option>
				</select> <input type="text" id="articleSearchText" value="${ articleSearchText }"/> <input type="button"
					id="articleSearch" value="찾기" />
			</form>
			<form style="float: right;">
				<label>Page: </label> <select id="articlePageNum">
					<option selected="selected">10</option>
					<option>15</option>
					<option>20</option>
				</select>
			</form>
			<form style="float: right; margin-right: 5px;">
				<label>게시판 종류: </label> <select id="articleNum">
					<option value="1" <c:if test="${ boardId == 1 }">selected</c:if>>공지사항</option>
					<option value="2" <c:if test="${ boardId == 2 }">selected</c:if>>학생회소식</option>
					<option value="3" <c:if test="${ boardId == 3 }">selected</c:if>>사진첩</option>
					<option value="4" <c:if test="${ boardId == 4 }">selected</c:if>>취업정보</option>
					<option value="5" <c:if test="${ boardId == 5 }">selected</c:if>>자유게시판</option>
					<option value="6" <c:if test="${ boardId == 6 }">selected</c:if>>건의합니다</option>
					<option value="9" <c:if test="${ boardId == 9 }">selected</c:if>>명예전당</option>
					<option value="7" <c:if test="${ boardId == 7 }">selected</c:if>>Q/A</option>
					<option value="8" <c:if test="${ boardId == 8 }">selected</c:if>>자료실</option>
					<option value="10" <c:if test="${ boardId == 10 }">selected</c:if>>동문회게시판</option>
				</select>
			</form>
		</div>
		<!--! end of #content -->
		<hr style="margin: 20px;" />
		<div id="manageUser_container" class="bbs">
			<table>
				<thead>
					<tr>
						<th width="60px">번호</th>
						<th width="80px">유저아이디</th>
						<th width="80px">비밀번호</th>
						<th width="84px">유저이름</th>
						<th width="80px">유저이메일</th>
						<th width="80px">유저전화번호</th>
						<c:if test="${ userGroup == 's' }"><th width="80px">학생회여부</th></c:if>
						<th width="60">수정</th>
						<th width="60">삭제</th>
					</tr>
				</thead>
				<tbody id="manageUser_records">
					<c:forEach items="${ userList }" var="user" varStatus="num">
						<tr>
							<td id="${ user.userId }">${userSize - num.count + 1}</td>
							<td>${ user.userId }</td>
							<td>${ user.userPw }</td>
							<td>${ user.userName }</td>
							<td>${ user.userEmail }</td>
							<td>${ user.userTel }</td>
							<c:if test="${ userGroup == 's' }"><td><c:if test="${ user.codeId == 4 }">O</c:if><c:if test="${ user.codeId != 4 }">X</c:if></td></c:if>
							<td><input type="button" value="수정" onclick="popup('common/jsp/popup/userModify.jsp?idx=manage&userId=${ user.userId }&codeId=${ user.codeId }')" /></td>
							<td><form action="DeleteServlet" method="post">
									<input type="submit" value="삭제" /><input type="hidden"
										name="userId" value="${ user.userId }" /><input type="hidden"
										name="userPw" value="${ user.userPw }" /><input type="hidden"
										name="idx" value="manage" /><input type="hidden" name="type"
										value="user" /><input type="hidden" name="userGroup"
										value="${ userGroup }" /><input type="hidden" name="boardId"
										value="${ boardId }" /><input type="hidden" name="sDate"
										value="${ sDate }"> <input type="hidden" name="lDate"
										value="${ lDate }">
								</form></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="manageUser_list" class="bbs_list_num"></div>
		<div align="center">
			<form id="searchList" style="display: inline-block;">
				<select id="userSearchType">
					<option value="userId" <c:if test="${ userSearchType == 'userId' }">selected="selected"</c:if> >userid</option>
					<option value="userName" <c:if test="${ userSearchType == 'userName' }">selected="selected"</c:if> >username</option>
				</select> <input type="text" id="userSearchText" value="${ userSearchText }"/> <input type="button"
					id="userSearch" value="찾기" />
			</form>
			<form style="float: right;">
				<label>Page: </label> <select id="userPageNum">
					<option selected="selected">10</option>
					<option>15</option>
					<option>20</option>
				</select>
			</form>
			<form style="float: right; margin-right: 5px;">
				<label>유저 종류: </label> <select id="userNum">
					<option value="s"
						<c:if test="${ userGroup == 's' }">selected</c:if>>학생</option>
					<option value="p"
						<c:if test="${ userGroup == 'p' }">selected</c:if>>교수</option>
					<option value="e"
						<c:if test="${ userGroup == 'e' }">selected</c:if>>직원</option>
				</select>
			</form>
		</div>
		<!--! end of #content -->
		<hr style="margin: 20px;" />
		<div id="manageCalendar_container" class="bbs">
			<table>
				<thead>
					<tr>
						<th width="60px">번호</th>
						<th width="80px">제목</th>
						<th width="80px">시작일자</th>
						<th width="80px">마침일자</th>
						<th width="60">삭제</th>
					</tr>
				</thead>
				<tbody id="manageCalendar_records">
					<c:forEach items="${ calendarList }" var="calendar" varStatus="num">
						<tr>
							<td id="${ calendar.calendarId }">${calendarSize - num.count
								+ 1}</td>
							<td>${ calendar.title }</td>
							<td>${ calendar.startDate }</td>
							<td>${ calendar.lastDate }</td>
							<td><form action="DeleteServlet" method="post">
									<input type="submit" value="삭제" /><input type="hidden"
										name="calendarId" value="${ calendar.calendarId }" /><input
										type="hidden" name="idx" value="manage" /><input
										type="hidden" name="type" value="calendar" /><input
										type="hidden" name="userGroup" value="${ userGroup }" /><input
										type="hidden" name="boardId" value="${ boardId }" /><input
										type="hidden" name="sDate" value="${ sDate }"> <input
										type="hidden" name="lDate" value="${ lDate }">
								</form></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="manageCalendar_list" class="bbs_list_num"></div>
		<div align="center">
			<form action="InsertServlet" method="post">
				<label>제목: </label><input type="text" name="title"
					style="width: 100px;"> <label>시작날짜: </label><input
					type="text" name="sDate" style="width: 82px;"> <label>마침날짜:
				</label><input type="text" name="lDate" style="width: 82px;"> <input
					type="submit" value="추가"><input type="hidden" name="idx"
					value="manage" /><input type="hidden" name="type" value="calendar" /><input
					type="hidden" name="userGroup" value="${ userGroup }" /><input
					type="hidden" name="boardId" value="${ boardId }" /><input
					type="hidden" name="sDate" value="${ sDate }"> <input
					type="hidden" name="lDate" value="${ lDate }">
			</form>
			<form style="float: right;">
				<span><label>검색 날짜 </label><input id="sDate" type="text"
					name="sDate" style="width: 82px" value="${ sDate }"> -
					<input id="lDate" type="text" name="lDate"
					style="width: 82px" value="${ lDate }"></span> <input
					id="calendarSearch" type="button" value="검색"> <label>Page:
				</label> <select id="calendarPageNum">
					<option selected="selected">10</option>
					<option>15</option>
					<option>20</option>
				</select>
			</form>
		</div>
		<!--! end of #content -->
	</div>
</body>
</html>