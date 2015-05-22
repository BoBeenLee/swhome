<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${ idx == 'noticeBoard'}">
		<c:set var="idxUrl" value="#admin?req=1" />
		<c:set var="topIdx" value="2" />
		<c:set var="subIdx" value="1" />
	</c:when>
	<c:when test="${ idx == 'studentBoard'}">
		<c:set var="idxUrl" value="#admin?req=2" />
		<c:set var="topIdx" value="2" />
		<c:set var="subIdx" value="2" />
	</c:when>
	<c:when test="${ idx == 'photoAlbum'}">
		<c:set var="idxUrl" value="#admin?req=3" />
		<c:set var="topIdx" value="2" />
		<c:set var="subIdx" value="3" />
	</c:when>
	<c:when test="${ idx == 'jobInfo'}">
		<c:set var="idxUrl" value="#info?req=2" />
		<c:set var="topIdx" value="3" />
		<c:set var="subIdx" value="2" />
	</c:when>
	<c:when test="${ idx == 'dataBoard'}">
		<c:set var="idxUrl" value="#board?req=5" />
		<c:set var="topIdx" value="4" />
		<c:set var="subIdx" value="5" />
	</c:when>
	<c:when test="${ idx == 'freeBoard'}">
		<c:set var="idxUrl" value="#board?req=1" />
		<c:set var="topIdx" value="4" />
		<c:set var="subIdx" value="1" />
	</c:when>
	<c:when test="${ idx == 'honorBoard'}">
		<c:set var="idxUrl" value="#board?req=3" />
		<c:set var="topIdx" value="4" />
		<c:set var="subIdx" value="3" />
	</c:when>
	<c:when test="${ idx == 'assoBoard'}">
		<c:set var="idxUrl" value="#asso?req=1" />
		<c:set var="topIdx" value="5" />
		<c:set var="subIdx" value="1" />
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
					<th colspan="3" style="text-align: left !important;">${
						norArticle.norArticleTitle }</th>
				</tr>
				<tr>
					<th width="80px">작성자</th>
					<th>${ norArticle.userName }(${ norArticle.norArticleWriterId
						})</th>
					<th width="50px">작성일</th>
					<th>${ norArticle.norArticleDate }</th>
				</tr>
			</table>
			<div id="norContent" class="articleContent">
				<p>
				<div>
					<b>파일 첨부 : <c:forEach var="filetmp" items="${ fileList }">
							<a
								href="common/jsp/upload/download.jsp?fileUrl=${ filetmp.fileUrl }">
								${ filetmp.fileName } </a>&nbsp 
				</c:forEach>
					</b>
				</div>
				<c:if test="${ idx == 'photoAlbum'}">
					<c:forEach var="filetmp" items="${ fileList }">
						<c:if
							test="${ filetmp.fileType == 'jpg' || filetmp.fileType == 'gif' || filetmp.fileType == 'png' || filetmp.fileType == 'bmp' || filetmp.fileType == 'JPG' || filetmp.fileType == 'GIF' || filetmp.fileType == 'PNG' || filetmp.fileType == 'BMP'}">
							<img src="${ filetmp.fileUrl }"
								alt="${ filetmp.fileName }" />
						</c:if>
					</c:forEach>
				</c:if>
				${ norArticle.norArticleContent }
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
									name="articleId" value="${ norArticle.norArticleId }">
									<input type="hidden" name="articleWriter" value="${ commenttmp.commentWriterId }" >
							</form>
						</h6>
						<p id="comment${ commenttmp.commentId }">${
							commenttmp.commentContent }</p>
						<div class="modify-comment">
							<form id="modifyCommentForm${ commenttmp.commentId }"
								action="UpdateServlet" method="post" style="display: none;">
								<p>
									<input type="hidden" name="articleId"
										value="${ norArticle.norArticleId }"> <input
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
						value="${ norArticle.norArticleId }"> <input type="hidden"
						name="idx" value="comment"> <input type="hidden"
						name="curUrl" value="${ idxUrl }">
				</form>
			</c:if>
			<div id="norMenu">
				<c:if test="${ norArticle.norArticleWriterId == userInfo.userId }">
					<form action="DeleteServlet" method="post">
						<input type="button" value="수정"
							onclick="javascript_:window.document.location.href='${ idxUrl }&type=update&articleId=${ norArticle.norArticleId }'">
						<input type="hidden" id="articleId" name="articleId"
							value="${ norArticle.norArticleId }"> <input
							type="hidden" name="idx" value="${ idx }"> <input
							type="submit" value="삭제">
					</form>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>