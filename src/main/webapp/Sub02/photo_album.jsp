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
					containerID : "photo_records",
					previous : "←",
					next : "→",
					perPage : 12,
					keyBrowse : true
				});

				// 검색 시
				$("#photoSearch").click(
						function(e) {
							window.document.location.href = "#admin?req=3&"
									+ "searchType="
									+ jQuery("#searchType").val()
									+ "&searchText="
									+ encodeURIComponent(jQuery("#searchText").val());
						});

				// 검색  Enter 시
				$("#searchText").keypress(function(e){
					if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
						$("#photoSearch").trigger("click");
						return false;
					} else
						return true;				
				});
			});
</script>
<body>
	<div>
		<img src="Sub02/img/content-3/subtop2-3.PNG" width="754px" />
	</div>
	<div class="rightContent">
		<div id="photo_container" class="bbs">
			<ul id="photo_records"
				style="list-style: none; width: 100%; height: 500px;">
				<c:forEach items="${ articleList }" var="article" varStatus="num">
					<li style="float: left; padding: 10px;"><a
						href="#admin?req=3&articleId=${ article.norArticleId }&type=read">
							<div>
								<img src="${ article.fileUrl }" alt="X" width="146px"
									height="126px" />
							</div> <span><c:if test="${ article.noticeYn == 'y' }">Notice</c:if><c:if test="${ article.noticeYn == 'n' }">${articleSize - num.count
								+ 1}</c:if>. </span>${
							article.norArticleTitle } [${ article.commentCount }]
					</a></li>
				</c:forEach>
			</ul>
			<div class="bbs_list_num"></div>
		</div>
		<div align="center">
			<form id="searchList" style="display: inline-block;">
				<select id="searchType">
					<option value="title">title</option>
					<option value="content">content</option>
					<option value="writer">writer</option>
				</select> <input type="text" id="searchText" /> <input type="button"
					id="photoSearch" value="찾기" />
			</form>
			<c:if test="${ userInfo != null }"><input type="button"
				onclick="javascript_:window.document.location.href='#admin?req=3&type=write'"
				value="글쓰기" /></c:if>
		</div>
		<!--! end of #content -->
	</div>
</body>
</html>
