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
					containerID : "job_records",
					previous : "←",
					next : "→",
					perPage : 15,
					delay : 20
				});
				$("#pageNum").change(function() {
					var newPageNum = parseInt($(this).val());

					$("div .bbs_list_num").jPages("destroy").jPages({
						containerID : "job_records",
						previous : "←",
						next : "→",
						perPage : newPageNum,
						delay : 20
					});
				});

				// 검색 시
				$("#jobSearch").click(
						function(e) {
							window.document.location.href = "#info?req=2&"
									+ "searchType="
									+ jQuery("#searchType").val()
									+ "&searchText="
									+ encodeURIComponent(jQuery("#searchText").val());
						});

				// 검색  Enter 시
				$("#searchText").keypress(function(e){
					if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
						$("#jobSearch").trigger("click");
						return false;
					} else
						return true;				
				});
			});
</script>
<body>
	<div>
		<img src="Sub03/img/content-2/subtop3-2.PNG" width="754px" />
	</div>
	<div class="rightContent">
		<div id="job_container" class="bbs">
			<table>
				<thead>
					<tr>
						<th width="60px">번호</th>
						<th width="700px">제목</th>
						<th width="80px">작성자</th>
						<th width="84px">작성일자</th>
						<th width="60">조회수</th>
					</tr>
				</thead>
				<tbody id="job_records">
					<c:forEach items="${ articleList }" var="article" varStatus="num">
						<tr>
							<td id="${ article.norArticleId }"><c:if test="${ article.noticeYn == 'y' }">Notice</c:if><c:if test="${ article.noticeYn == 'n' }">${articleSize - num.count
								+ 1}</c:if></td>
							<td style="text-align: left !important;"><a
								href="#info?req=2&articleId=${ article.norArticleId }&type=read">${
									article.norArticleTitle } [${ article.commentCount }]</a></td>
							<td>${ article.userName }</td>
							<td><%= new Date(((NorArticle) pageContext.getAttribute("article")).getNorArticleDate().getTime()) %></td>
							<td>${ article.hit }</td>
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
					id="jobSearch" value="찾기" />
			</form>
			<form style="float: right;">
				<label>Page: </label> <select id="pageNum">
					<option>10</option>
					<option selected="selected">15</option>
					<option>20</option>
				</select>
			</form>
			<c:if test="${ userInfo != null && ( userInfo.codeName == '관리자' || userInfo.codeName == '교수' || userInfo.codeName == '직원' ) }"><input type="button"
				onclick="javascript_:window.document.location.href='#info?req=2&type=write'"
				value="글쓰기" /></c:if>
		</div>
		<!--! end of #content -->
	</div>
</body>
</html>
