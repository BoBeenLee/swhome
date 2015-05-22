<%@page import="java.sql.Timestamp"%>
<%@page import="data.vo.article.*" %>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>
<link rel="stylesheet" href="common/css/rightContent.css"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="common/css/bbs.css" />
<script type="text/javascript" src="common/js/board/jPages.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(
			function($) {
				$("div .bbs_list_num").jPages({
					containerID : "qa_records",
					previous : "←",
					next : "→",
					perPage : 15,
					delay : 20
				});
				$("#pageNum").change(function() {
					var newPageNum = parseInt($(this).val());

					$("div .bbs_list_num").jPages("destroy").jPages({
						containerID : "qa_records",
						previous : "←",
						next : "→",
						perPage : newPageNum,
						delay : 20
					});
				});

				// 검색 시
				$("#qaSearch").click(
						function(e) {
							window.document.location.href = "#board?req=4&"
									+ "searchType="
									+ jQuery("#searchType").val()
									+ "&searchText="
									+ encodeURIComponent(jQuery("#searchText").val());
						});

				// 검색  Enter 시
				$("#searchText").keypress(function(e){
					if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
						$("#qaSearch").trigger("click");
						return false;
					} else
						return true;				
				});
			});
	// 비밀번호 입력해야할 시..
	function partPwd(id) {
		if (jQuery("#pwdPopup").css("display") == "none") {
			jQuery("#pwdPopup").css({
				"display" : "block",
				"top" : "50%",
				"left" : "50%",
				"position" : "absolute",
				"width" : "200px",
				"height" : "100px"
			});
			jQuery("#pwdPopup form")
					.append(
							"<input class='articleId' type='hidden' name='articleId' value='" + id + "' />");
		} else {
			jQuery("#pwdPopup").css({
				"display" : "none"
			});
			jQuery(".articleId").remove();
		}
	}

	// 게시판 읽기 위한
	function articleRead() {
		var dataString = {
			"password" : jQuery("#password").val(),
			"articleId" : jQuery(".articleId").val(),
			"req" : 4,
			"type" : "read"
		};

		jQuery.ajax({
			type : "get",
			url : "BoardContent",
			data : dataString,
			success : function(data) {
				jQuery("#content_right").html(data);
			}
		});
	}
</script>
<body>
	<div>
		<img src="Sub04/img/content-4/subtop4-4.PNG" width="754px" />
	</div>
	<div id="qa_content" class="rightContent">
		<div id="qa_container" class="bbs">
			<table>
				<thead>
					<tr>
						<th width="60px">번호</th>
						<th width="640px">제목</th>
						<th width="80px">작성자</th>
						<th width="84px">작성일자</th>
						<th width="60px">조회수</th>
						<th width="70px">처리상태</th>
					</tr>
				</thead>
				<tbody id="qa_records">
					<c:forEach items="${ articleList }" var="article" varStatus="num">
						<tr>
							<td id="${ article.partArticleId }"><c:if test="${ article.noticeYn == 'y' }">Notice</c:if><c:if test="${ article.noticeYn == 'n' }">${articleSize - num.count
								+ 1}</c:if></td>
							<td style="text-align: left !important;"><a
								<c:if test="${ article.partPassword == null }">href="#board?req=4&articleId=${ article.partArticleId }&type=read"</c:if>
								<c:if test="${ article.partPassword != null }"> href="javascript:void(0)" onclick="partPwd('${ article.partArticleId }')"</c:if>><c:forEach
										var="step" begin="2" end="${ article.step }">&nbsp&nbsp&nbsp&nbsp</c:forEach>
									<c:if
										test="${ article.parentArticleId != article.partArticleId }">ㄴ</c:if>${
									article.partArticleTitle } [${ article.commentCount }]</a></td>
							<td>${ article.userName }</td>
							<td><%= new Date(((PartArticle) pageContext.getAttribute("article")).getPartArticleDate().getTime()) %></td>
							<td>${ article.hit }</td>
							<td><c:if
									test="${ article.parentArticleId == article.partArticleId }">${ article.codeName }</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="bbs_list_num"></div>
		<div align="center">
			<form id="searchList" style="display: inline-block;">
				<select id="searchType">
					<option value="title">title</option>
					<option value="content">content</option>
					<option value="writer">writer</option>
				</select> <input type="text" id="searchText" /> <input type="button"
					id="qaSearch" value="찾기" />
			</form>
			<form style="float: right;">
				<label>Page: </label> <select id="pageNum">
					<option>10</option>
					<option selected="selected">15</option>
					<option>20</option>
				</select>
			</form>
			<c:if test="${ userInfo != null }"><input type="button"
				onclick="javascript_:window.document.location.href='#board?req=4&type=write'"
				value="글쓰기" /></c:if>
		</div>
		<!--! end of #content -->
		<div id="pwdPopup" style="display: none;">
			<form>
				비밀번호를 입력하세요. <input id="password" type="password" name="password" />
				<input type="button" value="확인" onclick="articleRead()" /><input
					type="button" value="닫기" onclick="partPwd()" />
			</form>
		</div>
	</div>
	<!--! end of #container -->
</body>
</html>
