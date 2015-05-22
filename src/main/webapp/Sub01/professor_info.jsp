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
<link type="text/css" rel="stylesheet" href="Sub01/css/style1-2.css" />
<script type="text/javascript" src="Sub01/js/jquery.sudoSlider.min.js"></script>

<script type="text/javascript">
	jQuery(document).ready(function($) {
		var sudoSlider = $("#professor_content").sudoSlider({
			customLink : '.custom',
			prevNext : false,
			continuous: true
		});
	});
</script>
<body>
	<div>
		<img src="Sub01/img/content-2/subtop1-2.PNG" width="754px" />
	</div>
	<div class="rightContent">
		<div class="bbs">
			<div id="navigation">
				<ul>
					<c:forEach var="professor" items="${ professorList }" varStatus="num">
						<c:if test="${ num.count == 1 }">
							<li rel="${ num.count }" class="custom current"><a href="#">${ professor.userName }</a></li>
						</c:if>
						<c:if test="${ num.count != 1 }">
							<li rel="${ num.count }" class="custom"><a href="#">${ professor.userName }</a></li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
			<div id="professor_content">
				<ul>
					<c:forEach var="professor" items="${ professorList }">
						<li>
							<table>
								<tr>
									<td rowspan="6">이미지 파일</td>
								</tr>
								<tr>
									<td width="50px">성명</td>
									<td width="80px">${ professor.userName }</td>
									<td width="50px">직위</td>
									<td width="90px">${ professor.professorPosition }</td>
								</tr>
								<tr>
									<td width="60px">연구실</td>
									<td width="70px">${ professor.extensionTel }</td>
									<td width="100px">전화번호</td>
									<td width="200px">${ professor.userTel }</td>
								</tr>
								<tr>
									<td colspan="2">이메일</td>
									<td colspan="2">${ professor.userEmail }</td>
								</tr>
								<tr>
									<td colspan="2">학력사항</td>
									<td colspan="2" width="500px">${ professor.professorAbility }</td>
								</tr>
								<tr>
									<td colspan="2">경력사항</td>
									<td colspan="2">${ professor.professorCareer }</td>
								</tr>
							</table>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
