<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>
<link rel="stylesheet" href="common/css/rightContent.css"
	type="text/css" />
<link type="text/css" rel="stylesheet" href="Sub01/css/style1-3.css" />

<script type="text/javascript" src="Sub01/js/jquery.sudoSlider.min.js"></script>
<script type="text/javascript"
	src="common/js/tooltip/jquery.hovercard.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		var sudoSlider = $("#subject_content").sudoSlider({
			customLink : '.custom',
			prevNext : false,
			continuous: true
		});

		$(".summary").each(function(i, element) {
			var hoverHTML = $(this).children(":eq(0)").html();

			//alert(hoverHTML);
			$(this).hovercard({
				detailsHTML : hoverHTML,
				width : 650,
				openOnLeft : true,
				openOnTop : true
			});
		});
	});
	
	/*function tab_change(i) {
		jQuery("#navigation ul").children().each(
				function(i, element) {
					//alert(jQuery(this).children("a").html());
					jQuery(this).children("a").html(
							"<img src='Sub01/img/content-3/btn3-" + (i + 1) + "-off.png'"
									+ " />");
				});

		//alert(jQuery(".nav").children(":eq(" + i + ")").children("a").html());
		jQuery("#navigation ul").children(":eq(" + i + ")").children("a").html(
				"<img src='Sub01/img/content-3/btn3-" + (i + 1) + "-on.png'" + "  />");
	}*/
</script>

<body>
	<div>
		<img src="Sub01/img/content-3/subtop1-3.PNG" width="754px" />
	</div>
	<div class="rightContent">
		<div id="navigation">
			<ul>
				<li rel="1" class="custom current"><a href="#"><img src="Sub01/img/content-3/btn3-1-on.png" alt="1학년"/></a></li>
				<li rel="2" class="custom"><a href="#"><img src="Sub01/img/content-3/btn3-2-on.png" alt="2학년"/></a></li>
				<li rel="3" class="custom"><a href="#"><img src="Sub01/img/content-3/btn3-3-on.png" alt="3학년"/></a></li>
				<li rel="4" class="custom"><a href="#"><img src="Sub01/img/content-3/btn3-4-on.png" alt="4학년"/></a></li>
			<!-- 	<li rel="5" class="custom"><a href="#">졸업요건</a></li>  -->
			</ul>
		</div>
		<div id="subject_content" class="bbs">
			<ul>
				<c:forEach var="listtmp" items="${ gradeList }">
					<li>
						<table>
							<thead>
								<tr>
									<th width="40px">학기</th>
									<th width="80px">구분</th>
									<th>과목명</th>
									<th width="35px">학점</th>
									<th width="200px">선수필수과목</th>
									<th width="200px">선수권장과목</th>
									<th width="40px">개요</th>
								</tr>
							</thead>
							<c:forEach var="gradetmp" items="${ listtmp }">
								<tbody>
									<td>${ gradetmp.semester }</td>
									<td>${ gradetmp.classification }</td>
									<td>${ gradetmp.subjectName }</td>
									<td>${ gradetmp.credit }</td>
									<td>${ gradetmp.preRequiredSubject }</td>
									<td>${ gradetmp.preRecommendedSubject }</td>
									<td><c:if test="${ !(gradetmp.summary == '보기') }">
											<label class="summary">보기
												<div style="display: none;">
													<p>${ gradetmp.summary }</p>
												</div>
											</label>
										</c:if></td>
								</tbody>
							</c:forEach>
						</table>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
