<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="data.vo.lottery.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	Calendar calendar = Calendar.getInstance();
%>

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
<script type="text/javascript"
	src="common/js/tooltip/jquery.hovercard.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$("#win_container .bbs_list_num").jPages({
			containerID : "win_records",
			previous : "←",
			next : "→",
			perPage : 3,
			delay : 20
		});
		$("#point_container .bbs_list_num").jPages({
			containerID : "point_records",
			previous : "←",
			next : "→",
			perPage : 10,
			delay : 20
		});
		$("#lottery_container .bbs_list_num").jPages({
			containerID : "lottery_records",
			previous : "←",
			next : "→",
			perPage : 10,
			delay : 20
		});

		// Point 안내
		var hoverHTML = $("#pointHelpContent").html();
		$("#pointHelp").hovercard({
			detailsHTML : hoverHTML
		});


		// 클릭시
		$("#pointYear, #pointMonth, #lotteryYear, #lotteryMonth").bind("change", function(e){
			var url = "#mypage?req=1&";
			url += "pointYear=" + $("#pointYear").val() + "&";
			url += "pointMonth=" + ($("#pointMonth").val() - 1) + "&";
			url += "lotteryYear=" + $("#lotteryYear").val() + "&";
			url += "lotteryMonth=" + ($("#lotteryMonth").val() - 1);

			window.document.location.href = url;
		});
	});
</script>

<body>
	<div>
		<img src="Sub07/img/content-1/subtop7-1.PNG" width="754px" />
	</div>
	<div class="rightContent">
		<table cellspacing="5px">
			<tr>
				<td>
					<div>
						<!-- 포인트 내역 알려주기 -->
						<table width="200px" height="100px" border="1">
							<thead>
								<tr>
									<th colspan="2">나의 포인트 내역</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>총 포인트</td>
									<td>${ totalPoint }</td>
								</tr>
								<tr>
									<td><form action="InsertServlet" method="post">
											<input type="submit" value="응모하기"> <input
												type="hidden" name="idx" value="lottery">
										</form></td>
									<td><label id="pointHelp">포인트 안내</label></td>
								</tr>
							</tbody>
						</table>
					</div>
				</td>
				<td>
					<div id="win_container" class="bbs">
						<!-- 당첨 내역 알려주기 -->
						<table>
							<thead>
								<tr>
									<th width="60px">번호</th>
									<th width="200px">당첨내역</th>
									<th width="230px">당첨상품</th>
									<th width="120px">당첨날짜</th>
								</tr>
							</thead>
							<tbody id="win_records">
								<c:forEach items="${ lotteryWinList }" var="win"
									varStatus="num">
										<tr>
											<td id="${ win.lotteryId }">${lotteryWinSize -
												num.count + 1}</td>
											<td style="text-align: left !important;">${
												win.lotteryContent }</td>
											<td>${ win.lotteryRank }</td>
											<td>${ win.lotteryDate }</td>
										</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="bbs_list_num"></div>
					</div>
				</td>
			</tr>
		</table>
		<div id="point_container" class="bbs">
			<select id="pointYear" name="pointYear">
				<c:forEach var="year" begin="<%= calendar.get(Calendar.YEAR) %>"
					end="<%= calendar.get(Calendar.YEAR) %>" step="1">
					<c:if test="${year != pointYear}">
						<option value="${year}">${year}</option>
					</c:if>
					<c:if test="${year == pointYear}">
						<option value="${year}" selected="selected">${year}</option>
					</c:if>
				</c:forEach>
			</select> <select id="pointMonth" name="pointMonth">
				<c:forEach var="month" begin="1" end="12" step="1">
					<c:if test="${month != pointMonth}">
						<option value="${month}">${month}</option>
					</c:if>
					<c:if test="${month == pointMonth}">
						<option value="${month}" selected="selected">${month}</option>
					</c:if>
				</c:forEach>
			</select>
			<table>
				<thead>
					<tr>
						<th width="60px">번호</th>
						<th width="400px">포인트 상세 내역</th>
						<th width="100px">포인트</th>
						<th width="120px">포인트적립날짜</th>
					</tr>
				</thead>
				<tbody id="point_records">
					<c:forEach items="${ lotteryPointList }" var="point"
						varStatus="num">
						<tr>
							<td id="${ point.pointContentId }">${lotteryPointSize -
								num.count + 1}</td>
							<td style="text-align: center !important;">${
								point.pointContent }</td>
							<td>${ point.addPoint }</td>
							<td>${ point.pointDate }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="bbs_list_num"></div>
		</div>
		<div id="lottery_container" class="bbs">
			<select id="lotteryYear" name="lotteryYear">
				<c:forEach var="year" begin="<%= calendar.get(Calendar.YEAR) %>"
					end="<%= calendar.get(Calendar.YEAR) %>" step="1">
					<c:if test="${year != lotteryYear}">
						<option value="${year}">${year}</option>
					</c:if>
					<c:if test="${year == lotteryYear}">
						<option value="${year}" selected="selected">${year}</option>
					</c:if>
				</c:forEach>
			</select> <select id="lotteryMonth" name="lotteryMonth">
				<c:forEach var="month" begin="1" end="12" step="1">
					<c:if test="${month != lotteryMonth}">
						<option value="${month}">${month}</option>
					</c:if>
					<c:if test="${month == lotteryMonth}">
						<option value="${month}" selected="selected">${month}</option>
					</c:if>
				</c:forEach>
			</select>
			<table>
				<thead>
					<tr>
						<th width="60px">번호</th>
						<th width="400px">응모날짜</th>
						<th width="120px">당첨여부</th>
					</tr>
				</thead>
				<tbody id="lottery_records">
					<c:forEach items="${ lotteryList }" var="lottery" varStatus="num">
						<tr>
							<td id="${ lottery.enterId }">${lotterySize - num.count + 1}</td>
							<td style="text-align: center !important;">${ lottery.enterDate }</td>
							<td><c:if test="${ lottery.lotteryId != 0 }">당첨</c:if><c:if test="${ lottery.lotteryId == 0 }">미확인</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="bbs_list_num"></div>
		</div>
	</div>

	<!-- 포인트 안내 -->
	<div id="pointHelpContent" style="display: none; padding: 2px;">
		<h1>포인트란?</h1>
		<br /> &nbsp;홈페이지를 이용하시는 학생들께 보다 폭 넓은 서비스를 제공하기 위해 만든 포인트제도 입니다. <br />
		<br />
		<h1>포인트 적립방법</h1>
		<table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<th
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-left: solid 1px #afafaf; border-right: solid 1px #afafaf; text-align: center; width: 20%; background-color: #efefef;">구분</th>
				<th
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-right: solid 1px #afafaf; text-align: center; width: 20%; background-color: #efefef;">포인트
					적립</th>
				<th
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-right: solid 1px #afafaf; text-align: center; width: 20%; background-color: #efefef;">비고</th>
			</tr>
			<tr>
				<td
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; text-align: left; padding-left: 10px; border-left: solid 1px #afafaf; border-right: solid 1px #afafaf;">로그인</td>
				<td
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-right: solid 1px #afafaf; text-align: left; padding-left: 10px;">2Point</td>
				<td
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-right: solid 1px #afafaf; text-align: left; padding-left: 10px;">하루
					한번 제한</td>
			</tr>
			<tr>
				<td
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-right: solid 1px #afafaf; text-align: left; width: 30%; padding-left: 10px; border-left: solid 1px #afafaf;">글
					작성</td>
				<td
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-right: solid 1px #afafaf; text-align: left; width: 30%; padding-left: 10px;">5Point</td>
				<td
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-right: solid 1px #afafaf; text-align: left; width: 30%; padding-left: 10px; border-bottom: solid 1px #afafaf;"
					rowspan="2">글(댓글) 삭제할 경우 지급된 Point 차감</td>
			</tr>
			<tr>
				<td
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-right: solid 1px #afafaf; text-align: left; width: 30%; padding-left: 10px; border-left: solid 1px #afafaf; border-bottom: solid 1px #afafaf;">댓글달기</td>
				<td
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-right: solid 1px #afafaf; text-align: left; width: 30%; padding-left: 10px; border-bottom: solid 1px #afafaf;">3Point</td>
			</tr>
		</table>
		<br /> <br />
		<h1>포인트 사용방법</h1>
		<br />
		<table border="0" cellpadding=0 cellspacing=0 style="width: 100%">
			<tr>
				<th
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-left: solid 1px #afafaf; text-align: center; background-color: #efefef;">구분</th>
				<th
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-left: solid 1px #afafaf; text-align: center; background-color: #efefef;">포인트
					사용</th>
				<th
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-left: solid 1px #afafaf; border-right: solid 1px #afafaf; text-align: center; background-color: #efefef;">비고</th>
			</tr>
			<tr>
				<td
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-bottom: solid 1px #afafaf; border-left: solid 1px #afafaf; text-align: left; padding-left: 10px;">로터리
					응모</td>
				<td
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-bottom: solid 1px #afafaf; text-align: left; padding-left: 10px; border-left: solid 1px #afafaf;">20Point</td>
				<td
					style="height: 18px; _height: 18px; border-top: solid 1px #afafaf; border-bottom: solid 1px #afafaf; border-right: solid 1px #afafaf; text-align: left; padding-left: 10px; border-left: solid 1px #afafaf;">&nbsp;</td>
			</tr>
		</table>
		<br /> <br />
	</div>
</body>
</html>