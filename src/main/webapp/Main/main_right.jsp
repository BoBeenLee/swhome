<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>성공회 대학교 소프트웨어공학과</title>
</head>
<link type="text/css" href="Main/css/jquery-ui-1.8.20.custom.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="Main/js/jquery-ui-1.8.20.custom.min.js"></script>
<script type="text/javascript">
	jQuery(document)
			.ready(
					function($) {
<%if (request.getAttribute("jSuccess") != null)
				out.println("alert('회원가입되었습니다.'');");%>
	//alert("Hello");

						// Tabs
						$("#tabs").tabs({
							event : "mouseover"
						});

						// DatePicker
						$("#datepicker")
								.datepicker(
										{
											inline : true,
											prevText : "<img src='Main/img/main/left.PNG' style='padding: 5px' />",
											nextText : "<img src='Main/img/main/right.PNG' style='padding: 5px' />",
											onSelect : function(dateText, inst) {
												var date = $("#datepicker")
														.datepicker("getDate");
												var tmp_date = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
												//alert(tmp_date);

												
												$.ajax({
													type : "POST",
													url : "CalendarServlet",
													data : {"date": tmp_date},
													success : function(data) {
														jQuery("#list_calendar").html(data);
													}
												});
											}
										});
						// Default 
						var date = $("#datepicker").datepicker("getDate");
						var tmp_date = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

						$.ajax({
							type : "POST",
							url : "CalendarServlet",
							data : {"date": tmp_date},
							success : function(data) {
								jQuery("#list_calendar").html(data);
							}
						});
					});

	function tab_change(i) {
		jQuery(".nav").children().each(
				function(i, element) {
					//alert(jQuery(this).children("a").html());
					jQuery(this).children("a").html(
							"<img src='Main/img/mainmenu" + (i + 1) + "-2.PNG'"
									+ " />");
				});

		//alert(jQuery(".nav").children(":eq(" + i + ")").children("a").html());
		jQuery(".nav").children(":eq(" + i + ")").children("a").html(
				"<img src='Main/img/mainmenu" + (i + 1) + "-1.PNG'" + "  />");
	}
</script>
<body>
	<!--  Calendar Box -->
	<div
		style="display: inline-block; width: 343px; height: 217px; float: left; background-image: url(Main/img/bg.PNG); background-repeat: repeat-y;">
		<img src="Main/img/mon1.PNG"
			style="display: block; margin-top: 5px; margin-left: 10px;" />
		<div class="box_calendar">
			<div id="datepicker"></div>
		</div>
		<div id="list_calendar"
			style="display: inline-block; width: 148px; height: 160px; background-image: url(Main/img/mon2.png); margin-top: 15px; overflow-y: auto; text-indent: 3px;"></div>
	</div>
	<!--  Mini Board Box -->
	<div class="page-wrap"
		style="background-image: url(Main/img/noticebg.PNG);">
		<div id="tabs" class="list-result">
			<ul class="nav">
				<li><a href="#tabs-1" class="current"
					onmouseover="tab_change(0)"><img src="Main/img/mainmenu1-1.PNG"
						alt="공지사항" /></a></li>
				<li><a href="#tabs-2" onmouseover="tab_change(1)"><img
						src="Main/img/mainmenu2-2.PNG" alt="학생회소식" /></a></li>
				<li><a href="#tabs-3" onmouseover="tab_change(2)"><img
						src="Main/img/mainmenu3-2.PNG" alt="취업정보" /></a></li>
				<li><a href="#tabs-4" onmouseover="tab_change(3)"><img
						src="Main/img/mainmenu4-2.PNG" alt="자유게시판" /></a></li>
			</ul>
			<div class="list-wrap">
				<div id="tabs-1">
					<ul id="list-one">
						<c:forEach var="noticetmp" items="${ noticeList }">
							<li><a
								href="#admin?req=1&articleId=${ noticetmp.norArticleId }&type=read">${
									noticetmp.norArticleTitle }</a></li>
						</c:forEach>
					</ul>
				</div>
				<div id="tabs-2">
					<ul id="list-two">
						<c:forEach var="studenttmp" items="${ studentList }">
							<li><a
								href="#admin?req=2&articleId=${ studenttmp.norArticleId }&type=read">${
									studenttmp.norArticleTitle }</a></li>
						</c:forEach>
					</ul>
				</div>
				<div id="tabs-3">
					<ul id="list-three">
						<c:forEach var="jobtmp" items="${ jobList }">
							<li><a
								href="#info?req=2&articleId=${ jobtmp.norArticleId }&type=read">${
									jobtmp.norArticleTitle }</a></li>
						</c:forEach>
					</ul>
				</div>
				<div id="tabs-4">
					<ul id="list-four">
						<c:forEach var="freetmp" items="${ freeList }">
							<li><a
								href="#board?req=1&articleId=${ freetmp.norArticleId }&type=read">${
									freetmp.norArticleTitle }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>