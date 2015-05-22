<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<%
	Calendar calendar = Calendar.getInstance();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="common/css/article.css" />
<link rel="stylesheet" href="common/css/rightContent.css"
	type="text/css" />
<!-- Load TinyMCE -->
<script type="text/javascript"
	src="common/tinymce/jscripts/tiny_mce/jquery.tinymce.js"></script>

<!-- Load plupload and all it's runtimes and finally the jQuery queue widget -->
<script type="text/javascript" src="common/js/board/plupload.full.js"></script>

<script type="text/javascript">
	jQuery()
			.ready(
					function($) {
						$('textarea.tinymce')
								.tinymce(
										{
											// Location of TinyMCE script
											script_url : 'common/tinymce/jscripts/tiny_mce/tiny_mce.js',

											// General options
											theme : "advanced",
											plugins : "autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist",

											// Theme options
											theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
											theme_advanced_buttons2 : "|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,link,unlink,image,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
											theme_advanced_buttons3 : "tablecontrols,|,sub,sup,|,charmap,emotions,media,advhr,|,fullscreen",
											theme_advanced_toolbar_location : "top",
											theme_advanced_toolbar_align : "left",
											theme_advanced_statusbar_location : "bottom",
											theme_advanced_resizing : true,

											// Example content CSS (should be your site CSS)
											content_css : "css/content.css",

											// Drop lists for link/image/media/template dialogs
											template_external_list_url : "lists/template_list.js",
											external_link_list_url : "lists/link_list.js",
											external_image_list_url : "lists/image_list.js",
											media_external_list_url : "lists/media_list.js",

											// Replace values for the template plugin
											template_replace_values : {
												username : "Some User",
												staffid : "991234"
											}
										});

					});
</script>
<!-- /TinyMCE -->

<body>
	<div>
		<img src="Sub0${ topIdx }/img/content-${ subIdx }/subtop${ topIdx }-${ subIdx }.PNG" width="754px" />
	</div>
	<div class="rightContent">
		<div class="article">
		<form id="partForm" action="InsertServlet" method="post">
			<table>
				<tr>
					<th colspan="1">제목</th>
					<th colspan="5"><input name="title" type="text"
						style="width: 100%;">
					</th>
				</tr>
				<tr>
					<th>공지여부</th>
					<th><input type="checkbox" name="notice" value="yes">
					</th>
					<th>공지기간</th>
					<th style="text-align: left !important;"><select
						name="noticeYear">
							<c:forEach var="year" begin="<%= calendar.get(Calendar.YEAR) %>" end="<%= calendar.get(Calendar.YEAR) + 200 %>" step="1">
								<c:if test="${year != noticeYear}">
									<option value="${year}">${year}</option>
								</c:if>
								<c:if test="${year == noticeYear}">
									<option value="${year}" selected="selected">${year}</option>
								</c:if>
							</c:forEach>
					</select> <select name="noticeMonth">
							<c:forEach var="month" begin="1" end="12" step="1">
								<c:if test="${month != noticeMonth}">
									<option value="${month}">${month}</option>
								</c:if>
								<c:if test="${month == noticeMonth}">
									<option value="${month}" selected="selected">${month}</option>
								</c:if>
							</c:forEach>
					</select>- <select name="noticeDay">
							<c:forEach var="day" begin="1" end="31" step="1">
								<c:if test="${day != noticeDay}">
									<option value="${day}">${day}</option>
								</c:if>
								<c:if test="${day == noticeDay}">
									<option value="${day}" selected="selected">${day}</option>
								</c:if>
							</c:forEach>
					</select></th>
					<th>비밀번호: </th>
					<th><input type="password" name="password"></th>
				</tr>
			</table>
			<!-- Gets replaced with TinyMCE, remember HTML in a textarea should be encoded -->
			<div class="articleContent">
				<textarea id="articleContent" name="articleContent" rows="30"
					class="tinymce">
			</textarea>
			</div>
			<div id="articleMenu">
				<!--  파일업로드 부분 -->
				<div id="fileContainer">
					<div id="filelist">No runtime found.</div>
					<br /><span id="fileCount">0</span> <a id="pickfiles" href="javascript:void(0)">[Select files]</a>
				</div>

				<!--  파일 업로드 하는 부분 -->
				<script type="text/javascript"> 
					jQuery()
							.ready(
									function($) {
										
										uploaderInit();

										function uploaderInit(){

											// 업로드 할 파일 선택시
											var uploader = new plupload.Uploader({
												runtimes : 'flash',
												browse_button : 'pickfiles',
												container : 'fileContainer',
												max_file_size : '10mb',
												url : 'common/jsp/upload/upload.jsp',
												flash_swf_url : 'common/js/board/plupload.flash.swf',
												filters : [
													{title : "Image files", extensions : "jpg,gif,png"},
													{title : "Zip files", extensions : "zip"}
												],
												resize : {width : 320, height : 240, quality : 90}
											});

											uploader.bind('Init', function(up, params) {
												$('#filelist').html("<div>Current runtime: " + params.runtime + "</div>");
											});

											// 파일을 업로드할 경우
											$('#partSubmit').click(function(e) {		
												var fileCount = Number($("#fileCount").html());

												if(fileCount == 0)
													$("#partForm").submit();
												else
													uploader.start();
													
												e.preventDefault();
											});
											
											uploader.init();

											uploader.bind('FilesAdded', function(up, files) {
												$.each(files, function(i, file) {
													$('#filelist').append(
														'<div id="' + file.id + '" >' +
														file.name + ' (' + plupload.formatSize(file.size) + ') <a href="javascript:void(0)">X</a> <b></b>' +
													'<input type="hidden" name="fileName" value="' + file.name + '">' + '</div>');

													// 업로드 할 파일을 취소할 경우
													$('#' + file.id + ' a').click(function(e){
														e.preventDefault();
													
														uploader.removeFile(file);
														$('#' + file.id).remove();
														uploader.refresh();
													});
												});

												up.refresh(); // Reposition Flash/Silverlight
											});

											uploader.bind('UploadProgress', function(up, file) {
												$('#' + file.id + " b").html(file.percent + "%");
											});

											uploader.bind('Error', function(up, err) {
												$('#filelist').append("<div>Error: " + err.code +
													", Message: " + err.message +
													(err.file ? ", File: " + err.file.name : "") +
													"</div>"
												);
												up.refresh(); // Reposition Flash/Silverlight
											});

											uploader.bind('FileUploaded', function(up, file) {
												$('#' + file.id + " b").html("100%");
											});

											uploader.bind('UploadComplete', function(up, file) {
												$("#partForm").submit();
											});

											uploader.bind('QueueChanged', function(up) {
												$("#fileCount").html(uploader.files.length);
											});
										}
									});
				</script>
				<!--  파일 업로드 하는 부분 -->
				<input id="partSubmit" type="button" value="확인"> 
				<input type="hidden" name="idx" value="${ idx }" />
				<!-- 답글일 경우.. -->
				<c:if test="${ targetArticleId != null }"><input type="hidden" name="targetArticleId" value="${ targetArticleId }"></c:if>
			</div>
		</form>
		</div>
	</div>
</body>
</html>