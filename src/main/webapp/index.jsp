<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>성공회 대학교 소프트웨어공학과</title>
</head>
<%
	//HashMap<String, String> userInfo = (HashMap<String, String>)session.getAttribute("userInfo");
%>
<link rel="stylesheet" href="css/index.css" /> <!-- Index.css 부분 -->
<link rel="stylesheet" href="css/formalize.css" /> <!-- Form css 설정 --> 
<!-- jQuery를 쓰기위한 -->
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<!-- history 관련 js -->
<script type="text/javascript" src="js/jquery.ba-hashchange.min.js"></script>
<!-- 드롭엔 드랍 관련 js -->
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.hoverIntent.minified.js"></script>
<script type="text/javascript" src="js/jquery.naviDropDown.1.0.js"></script>
<!--  -->
<script type="text/javascript" src="js/jquery.formalize.js"></script>
<!-- <script type="text/javascript" src="js/json2.js"></script>  json2.js -->
<script type="text/javascript">
	jQuery(function() {
		jQuery(window).hashchange(function() { // 뒤로가기 새로고침 보완용
			var hash = (location.hash).split("?"); // 자르는 것을 구분
			var reqIdx = "";
			var sidebarUrl = "", contentRightUrl = "";
			var validBool = false; // 유효성 검사할 때 쓰는 체크 ( true : 오류, false : 정상)
			var leftIdx = "";
			var siteUrl;
				
			// hash[0] #sw와 같이 구분하기 위한 것
			// hash[1] 그 이외 get에 관련된 변수들을 지정하기 위한 것. 

			
			if (hash[1] != null){
				siteUrl = hash[1].split("&"); // url &를 구분하기위해서
				reqIdx = siteUrl[0].split("=")[1];
			//alert(hash[0] + " " + hash[1]);
			}
			switch (hash[0]) { // 페이지를 관할하는 컨트롤러 (로그인 창을 현재 페이지에 계속 유지하기 위해선 어쩔수 없이 이썽야한다.);
			case "#sw":
				sidebarUrl = "Sub01/sw_left.html";
				contentRightUrl = "SwContent?" + hash[1];
				leftIdx = 1;
				break;
			case "#admin":
				sidebarUrl = "Sub02/admin_left.html";
				contentRightUrl = "AdminContent?" + hash[1];
				leftIdx = 2;
				break;
			case "#info":
				sidebarUrl = "Sub03/info_left.html";
				contentRightUrl = "InfoContent?" + hash[1];
				leftIdx = 3;
				break;
			case "#board":
				sidebarUrl = "Sub04/board_left.html";
				contentRightUrl = "BoardContent?" + hash[1];
				leftIdx = 4;
				break;
			case "#asso":
				sidebarUrl = "Sub05/asso_left.html";
				contentRightUrl = "AssoContent?" + hash[1];
				leftIdx = 5;
				break;
			case "#user":
				sidebarUrl = "Sub06/login_left.html";
				contentRightUrl = "UserContent?" + hash[1];
				leftIdx = 6;
				break;
			case "#mypage":
				sidebarUrl = "Sub07/myPage_left.jsp";
				contentRightUrl = "MyPageContent?" + hash[1];
				leftIdx = 7;
				break;
			case "#siteinfo":
				sidebarUrl = "Sub08/site_left.html";
				contentRightUrl = "SiteInfoContent?" + hash[1];
				leftIdx = 8;
				break;
			case "":
			case "#main":
				jQuery("#content_up").css({
					"display" : "block",
					"width" : "100%",
					"height" : "315px",
					"margin" : "0px 0px 5px 0px",
					"float" : "left"
				});
				jQuery("#content_left").css({
					"height" : "260px"
				});
				jQuery("#content_right").css({
					"height" : "240px"
				});
				// 사이드바를 아예 감추기 위해서
				jQuery("#sidebar").css({
					"margin-bottom" : "0px"
				});
				jQuery.ajax("Main/main_up.html").done(function(data) {
					jQuery("#content_up").html(data);
				});
				sidebarUrl = "Main/main_left.html";
				contentRightUrl = "MainContent";
				break;
			default:
				alert("해당 페이지에 접근할 수가 없습니다.");
				validBool = true;
			}

			//alert(reqIdx + " " + leftIdx);
			
			if (!(hash[0] == "#main" || hash[0] == "")) {
				jQuery("#content_up").removeAttr("style").html("");
				jQuery("#content_left, #content_right").css({
					"height" : "99.5%"
				}).html();
				jQuery("#sidebar").css({ // 사이드바 밑에를 띄우기 위해 설정을 하였음.
					"margin-bottom" : "5px"
				});
			}
			if (validBool) {
				history.back(-1);
			} else {
				jQuery.ajax(sidebarUrl).done(function(data) {
					jQuery("#sidebar").html(data);
					jQuery("#sidebar a").each(function(index, element) { // a 링크와 관련된 것에 이미지를 부여하는 데, 서브 메뉴로 간주한다.
						jQuery(this).html("<img src='Sub0" + leftIdx + "/img/sub/menu" + leftIdx + "-" + (index + 1) + "out.PNG' />");
					});
					jQuery("#sidebar a:eq(" + (Number(reqIdx) - 1) + ")").html("<img src='Sub0" + leftIdx + "/img/sub/menu" + leftIdx + "-" + reqIdx + "on.PNG' />");	
				});
				jQuery.ajax(contentRightUrl).done(function(data) { // 오른쪽 내용물 출력
					jQuery("#content_right").html(data);
				});
				//alert(jQuery("#content_up").height() + jQuery("#content_right").height());
				//jQuery("#content").css({"height" : jQuery("#content_up").height() + jQuery("#content_right").height()});
			}
		}).hashchange();
	});

	jQuery(document).ready(function($) {
		defaultPage(); // 기초 메인 부르기
	});

	function defaultPage() {
		/* var defaultUrl = "http://localhost:8080/swHome/index.jsp"; */
		jQuery("#navigation_horiz").naviDropDown({}); // header 부분 dropdown	
		//alert("Hello");
		//if(location.hash == "#main" || location.hash == "") // 첨 홈페이지에 들어왔을 때랑 로고, home 링크를 클릭하였을 때.
		//alert(window.document.location.href);
		//alert(defaultUrl);
		//alert(location.hash);
		if(location.hash == "")
			jQuery("#mainPage").trigger("click"); // 한 번 클릭 실행. 트리거 함수를 호출
		<%
			// 어떠한 작업에 메시지를 출력하기 위한 총체적인 역할을 하는 넘.
			String resMsg = request.getParameter("resMsg");
			if(resMsg != null)
				out.println("alert('" + resMsg + "');");
		%>
	}
</script>
<body>
	<input type="hidden" id="mainPage"
		onclick="javascript_:window.document.location.href='#main'" />
	<div class="header">
		<div class="list_quick">
			<a href="index.jsp"><img src="img/top/home.PNG" /></a> <img
				src="img/top/dot.PNG" /> <a href="#siteinfo?req=1"><img
				src="img/top/sitemap.PNG" /></a> <img src="img/top/dot.PNG" /> <a href="http://www.skhu.ac.kr"><img src="img/top/skhu.PNG" /></a> <img src="img/top/dot.PNG" /> <a
				href="#siteinfo?req=2"><img src="img/top/help.PNG" /></a>
		</div>
		<div class="list_menu">
			<a id="logoMain" href="index.jsp" ><img
				id="logo" src="img/top/logo.PNG" /></a>
			<div id="navigation_horiz">
				<ul>
					<li><a href="#sw?req=1"><img src="img/top/topmenu1.JPG"
							id="sw" alt="학과정보" /></a>
						<div class="dropdown" id="dropdown_sw">
							<a href="#sw?req=1"><img src="img/top/menu1-1.PNG" alt="학과소개" /></a><a
								href="#sw?req=2"><img src="img/top/menu1-2.PNG" alt="교수소개" /></a><a
								href="#sw?req=3"><img src="img/top/menu1-3.PNG" alt="교과과정" /></a><a
								href="#sw?req=4"><img src="img/top/menu1-4.PNG" alt="행정안내" /></a>
						</div> <!-- .dropdown_menu --></li>
					<li><a href="#admin?req=1"><img src="img/top/topmenu2.JPG"
							id="admin" alt="학과행정" /></a>
						<div class="dropdown" id="dropdown_admin">
							<a href="#admin?req=1"><img src="img/top/menu2-1.PNG"
								alt="공지사항" /></a><a href="#admin?req=2"><img
								src="img/top/menu2-2.PNG" alt="학생회소식" /></a><a href="#admin?req=3"><img
								src="img/top/menu2-3.PNG" alt="사진첩" /></a>
						</div> <!-- .dropdown_menu --></li>
					<li><a href="#info?req=1"><img src="img/top/topmenu3.JPG"
							id="info" alt="정보광장" /></a>
						<div class="dropdown" id="dropdown_info">
							<a href="#info?req=1"><img src="img/top/menu3-1.PNG"
								alt="자격증정보" /></a><a href="#info?req=2"><img
								src="img/top/menu3-2.PNG" alt="취업정보" /></a><a href="#info?req=3"><img
								src="img/top/menu3-3.PNG" alt="인턴쉽" /></a>
						</div> <!-- .dropdown_menu --></li>
					<li><a href="#board?req=1"><img src="img/top/topmenu4.JPG"
							id="board" alt="참여광장" /></a>
						<div class="dropdown" id="dropdown_board">
							<a href="#board?req=1"><img src="img/top/menu4-1.PNG"
								alt="자유게시판" /></a><a href="#board?req=2"><img
								src="img/top/menu4-2.PNG" alt="건의사항" /></a><a href="#board?req=3"><img
								src="img/top/menu4-3.PNG" alt="명예전당" /></a><a href="#board?req=4"><img
								src="img/top/menu4-4.PNG" alt="Q/A" /></a><a href="#board?req=5"><img
								src="img/top/menu4-5.PNG" alt="자료실" /></a>
						</div> <!-- .dropdown_menu --></li>
					<li><a href="#asso?req=1"><img src="img/top/topmenu5.JPG"
							id="asso" alt="동문회" /></a>
						<div class="dropdown" id="dropdown_asso">
							<a href="#asso?req=1"><img src="img/top/menu5.PNG"
								alt="동문회 게시판" /></a>
						</div></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="content">
		<div id="content_up"></div>
		<div id="content_left">
			<div id="box_login"><%@ include
					file="/LoginBox/loginCheckBox.jsp"%></div>
			<div id="sidebar"></div>
			<div class="box_link">
				<a href="http://library.skhu.ac.kr/"><img src="img/main/q1.PNG" alt="중앙도서관" style="float: left;" /></a> 
				<a href="http://report.skhu.ac.kr/"><img src="img/main/q2.PNG" alt="과제제출시스템" style="float: left;" /></a>
				<a href="http://forest.skhu.ac.kr/"><img src="img/main/q3.PNG" alt="학사관리시스템" style="float: left;" /></a>
				<a href="http://club.cyworld.com/skhusoft"><img src="img/main/q4.PNG" alt="SOFT카페" style="float: left;" /></a>
			</div>
		</div>
		<div id="content_right"></div>
	</div>
	<div class="foot_info">
		<img src="img/footer.PNG" alt="footer" />
	</div>
</body>
</html>