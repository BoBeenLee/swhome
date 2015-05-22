<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="data.constant.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="processSuccess" value="<%= Code.PROCESSSUCCESS.getValue() %>" />
<c:set var="processFail" value="<%= Code.PROCESSFAIL.getValue() %>" />
<c:set var="processProcessing" value="<%= Code.PROCESSING.getValue() %>" />

<c:choose>
	<c:when test="${ idx == 'QABoard'}">
		<c:set var="idxUrl" value="#board?req=4" />
		<c:set var="topIdx" value="4" />
		<c:set var="subIdx" value="4" />
	</c:when>
	<c:when test="${ idx == 'suggestionBoard'}">
		<c:set var="idxUrl" value="#board?req=2" />
		<c:set var="topIdx" value="4" />
		<c:set var="subIdx" value="2" />
	</c:when>
</c:choose>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="common/css/article.css" />
<link rel="stylesheet" href="common/css/rightContent.css"
	type="text/css" />
<script type="text/javascript">
	function goCommentModify(no) {
		var commentno = "comment" + no;
		var formno = "modifyCommentForm" + no;
		var commenttmp = jQuery("#" + commentno);
		var formtmp = jQuery("#" + formno);

		//alert(commenttmp.css("display"));
		if (commenttmp.css("display") == "block") {
			commenttmp.css("display", "none");
			formtmp.css("display", "block");
		} else {
			commenttmp.css("display", "block");
			formtmp.css("display", "none");
		}
	}

	function addRecommend(articleId) {
		var dataString = {
			"idx" : "recommend",
			"articleId" : articleId
		};

		jQuery.ajax({
			type : "POST",
			url : "InsertServlet",
			data : dataString,
			success : function(data) {
				alert("add Recommend Success");
			}
		});
	}

	function processState(articleId, state){
		var dataString = {
			"idx" : "process",
			"articleId" : articleId,
			"state" : state
		};

		jQuery.ajax({
			type : "POST",
			url : "UpdateServlet",
			data : dataString,
			success : function(data){
				alert("Process Success");
			}
		});
	}
</script>
<body>
	<div>
		<img
			src="Sub0${ topIdx }/img/content-${ subIdx }/subtop${ topIdx }-${ subIdx }.PNG"
			width="754px" />
	</div>
	<div class="rightContent">
		<div class="article">
			<table>
				<tr>
					<th width="50px" colspan="1">제목</th>
					<th colspan="10" style="text-align: left !important;">${
						partArticle.partArticleTitle }</th>
				</tr>
				<tr>
					<th width="80px">작성자</th>
					<th>${ partArticle.userName }(${
						partArticle.partArticleWriterId })</th>
					<th width="50px">작성일</th>
					<th>${ partArticle.partArticleDate }</th>
					<c:if test="${ idx == 'suggestionBoard' && userInfo != null }">
						<th><input type="button" value="추천하기"
							onclick="addRecommend('${ partArticle.partArticleId }')" /></th>
					</c:if>
					<c:if test="${ idx == 'QABoard' && partArticle.partArticleId == partArticle.parentArticleId && ( userInfo.userId != partArticle.partArticleWriterId ) && !( userInfo.codeName == '교수' || userInfo.codeName == '관리자' ) }">
						<th><c:if test="${ partArticle.processId == processSuccess }">처리완료</c:if><c:if test="${ partArticle.processId == processProcessing }">처리중</c:if><c:if test="${ partArticle.processId == processFail }">처리불가</c:if></th>
					</c:if>
					<c:if
						test="${ idx == 'QABoard' && partArticle.partArticleId == partArticle.parentArticleId && userInfo != null && ( userInfo.userId == partArticle.partArticleWriterId ) && !( userInfo.codeName == '교수' || userInfo.codeName == '관리자' ) }">
						<th><input type="button" value="처리완료" onclick="processState('${ partArticle.partArticleId }', '${ processSuccess }')" /></th>
					</c:if>
					<c:if test="${ idx == 'QABoard' && partArticle.partArticleId == partArticle.parentArticleId && userInfo != null && ( userInfo.codeName == '교수' || userInfo.codeName == '관리자' ) }">
						<th><select id="processId">
							<option value="${ processSuccess }" <c:if test="${ partArticle.processId == processSuccess }">selected</c:if> >처리완료</option>
							<option value="${ processProcessing }" <c:if test="${ partArticle.processId == processProcessing }">selected</c:if> >처리중</option>
							<option value="${ processFail }" <c:if test="${ partArticle.processId == processFail }">selected</c:if> >처리불가</option>
						</select>
						<input type="button" value="선택" onclick="processState('${ partArticle.partArticleId }', jQuery('#processId').val())"> 
						</th>
					</c:if>
				</tr>
			</table>
			<div id="partContent" class="articleContent">
				<p>
					<b>파일 첨부 : <c:forEach var="filetmp" items="${ fileList }">
							<a
								href="common/jsp/upload/download.jsp?fileUrl=${ filetmp.fileUrl }">
								${ filetmp.fileName } </a>&nbsp 
				</c:forEach>
					</b> ${ partArticle.partArticleContent }
					<c:if test="${ norArticle.noticeYn == 'y' }">
						<br>
						<br>공지사항: ${ norArticle.noticeDate }</c:if>
				</p>
			</div>
			<!--  덧글 반복 시작 -->
			<div class="comment">
				<c:forEach var="commenttmp" items="${ commentList }">
					<div>
						<h4>${ commenttmp.commentWriterId }</h4>
						<h5>${ commenttmp.commentDate }</h5>
						<h6>
							<a href="javascript:goCommentModify('${ commenttmp.commentId }')">수정</a>
							|
							<form id="deleteCommentForm${ commenttmp.commentId }"
								action="DeleteServlet" method="post"
								style="display: inline-block;">
								<a
									href="javascript:document.forms.deleteCommentForm${ commenttmp.commentId }.submit()">삭제</a>
								<input type="hidden" name="commentId"
									value="${ commenttmp.commentId }"> <input type="hidden"
									name="idx" value="comment"> <input type="hidden"
									name="curUrl" value="${ idxUrl }"> <input type="hidden"
									name="articleId" value="${ partArticle.partArticleId }">
							</form>
						</h6>
						<p id="comment${ commenttmp.commentId }">${
							commenttmp.commentContent }</p>
						<div class="modify-comment">
							<form id="modifyCommentForm${ commenttmp.commentId }"
								action="UpdateServlet" method="post" style="display: none;">
								<p>
									<input type="hidden" name="articleId"
										value="${ partArticle.partArticleId }"> <input
										type="hidden" name="commentId"
										value="${ commenttmp.commentId }"> <input
										type="hidden" name="idx" value="comment"> <input
										type="hidden" name="curUrl" value="${ idxUrl }">
								</p>
								<div class="fr">
									<a
										href="javascript:document.forms.modifyCommentForm${ commenttmp.commentId }.submit()">수정하기</a>
									| <a
										href="javascript:goCommentModify('${ commenttmp.commentId }')">취소</a>
								</div>
								<div>
									<textarea class="modify-comment-ta" name="content" rows="2"
										cols="50">${ commenttmp.commentContent }</textarea>
								</div>
							</form>
						</div>
					</div>
				</c:forEach>
			</div>
			<!--  덧글 반복 끝 -->
			<c:if test="${ userInfo != null }">
				<form id="addCommentForm" action="InsertServlet" method="post">
					<div id="addComment">
						<textarea name="content" rows="2" cols="50"></textarea>
					</div>
					<div style="text-align: right;">
						<input type="submit" value="덧글남기기" />
					</div>
					<input type="hidden" name="articleId"
						value="${ partArticle.partArticleId }"> <input
						type="hidden" name="idx" value="comment"> <input
						type="hidden" name="curUrl" value="${ idx }">
				</form>
			</c:if>
			<div id="partMenu">
				<c:if test="${ partArticle.partArticleWriterId == userInfo.userId }">
					<form action="DeleteServlet" method="post">
						<input type="button" value="답변"
							onclick="javascript_:window.document.location.href='${ idxUrl }&type=write&targetArticleId=${ partArticle.partArticleId }'">
						<input type="button" value="수정"
							onclick="javascript_:window.document.location.href='${ idxUrl }&type=update&articleId=${ partArticle.partArticleId }'">
						<input type="hidden" id="articleId" name="articleId"
							value="${ partArticle.partArticleId }"> <input
							type="hidden" name="idx" value="${ idx }"> <input
							type="submit" value="삭제">
					</form>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>