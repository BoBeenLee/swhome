<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="data.bo.ArticleBO, data.bo.FileBO, data.vo.article.NorArticle, data.vo.file.FileInfo" %>

<%!
private int selectModifyNorArticle(HttpServletRequest request){
	String articleId = request.getParameter("articleId");
	ArticleBO articleBo = new ArticleBO();

	// 조회하기
	NorArticle norArticle = articleBo.getSelectNorArticle(articleId);
	
	// 날짜 관련 조회 설정
	if (norArticle.getNoticeDate() != null) {
		procNoticeDate(request, norArticle.getNoticeDate());
	}
	
	// 파일 관련 조회 설정
	procFile(request, norArticle.getNorArticleId());
	// System.out.println(norArticle.getNorArticleContent());
	request.setAttribute("norArticle", norArticle);
	
	return 0;
}

private void procNoticeDate(HttpServletRequest request, Date noticeDate){
	/* 날짜 출력하기 위한 */
	Calendar calendar = Calendar.getInstance();
	calendar.setTimeInMillis(noticeDate.getTime());

	// System.out.println(norArticle.getNorArticleContent());
	request.setAttribute("noticeYear", calendar.get(Calendar.YEAR));
	request.setAttribute("noticeMonth",
			calendar.get(Calendar.MONTH));
	request.setAttribute("noticeDay", calendar.get(Calendar.DATE));
}

private void procFile(HttpServletRequest request, String articleId){
	/* 파일 리스트를 보여주기 위한 */
	
	FileBO fileBo = new FileBO();
	
	List<FileInfo> fileList = fileBo.getFileList(articleId);
	
	if(fileList.size() > 0){
		request.setAttribute("fileList", fileList);
	}
}

%>
<%
	HashMap<String, String> hs = (HashMap<String, String>)session.getAttribute("userInfo");

	if(!(hs.get("codeName").equals("관리자"))){
		response.sendRedirect("index.jsp/common/jsp/accessFail.jsp");
	}
	// Setting
	selectModifyNorArticle(request);
 	request.setAttribute("idx", request.getParameter("idx"));
 	request.setAttribute("type", request.getParameter("type"));
 	
	Calendar calendar = Calendar.getInstance();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="/skhusw/common/css/article.css" />
<link rel="stylesheet" href="/skhusw/common/css/rightContent.css"
	type="text/css" />
	
<!-- jQuery를 쓰기위한 -->
<script type="text/javascript" src="/skhusw/js/jquery-1.7.1.min.js"></script>

<!-- Load TinyMCE -->
<script type="text/javascript"
	src="/skhusw/common/tinymce/jscripts/tiny_mce/jquery.tinymce.js"></script>

<!-- Load plupload and all it's runtimes and finally the jQuery queue widget -->
<script type="text/javascript" src="/skhusw/common/js/board/plupload.full.js"></script>

<script type="text/javascript">
	jQuery()
			.ready(
					function($) {
						$('textarea.tinymce')
								.tinymce(
										{
											// Location of TinyMCE script
											script_url : '/skhusw/common/tinymce/jscripts/tiny_mce/tiny_mce.js',

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
	function deleteFile(fileId){
		var dataString = {"idx" : "file", "fileId" : fileId};

		jQuery.ajax({
			type : "POST",
			url : "/skhusw/DeleteServlet",
			data : dataString,
			success : function(data) {
				//alert("delete file Success");
				history.go(0);
			}
		});	
	}
</script>
<!-- /TinyMCE -->

<body>
	<div class="rightContent article" style="margin-left: 3px;">
		<form id="norForm" action="/skhusw/UpdateServlet" method="post">
			<table>
				<tr>
					<th colspan="1">제목</th>
					<th colspan="3"><input name="title" type="text"
						style="width: 100%;" value="${ norArticle.norArticleTitle }">
					</th>
				</tr>
				<tr>
					<th>공지여부</th>
					<th><input type="checkbox" name="notice" <c:if test="${ norArticle.noticeYn == 'y' }">checked='checked'</c:if>>
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
								<c:if test="${month - 1 != noticeMonth }">
									<option value="${month}">${month}</option>
								</c:if>
								<c:if test="${month - 1 == noticeMonth}">
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
				</tr>
			</table>
			<!-- Gets replaced with TinyMCE, remember HTML in a textarea should be encoded -->
			<div class="articleContent">
				<textarea id="articleContent" name="articleContent" rows="30"
					class="tinymce">
				${ norArticle.norArticleContent }
			</textarea>
			</div>
			<div id="articleMenu">
				<!--  파일업로드 부분 -->
				<div id="fileContainer">
					<div id="filelist">No runtime found.</div>
					<br /><span id="fileCount">0</span> <a id="pickfiles" href="javascript:void(0)">[Select files]</a>
				</div>
				<div>
					<ul>
						<c:forEach var="filetmp" items="${ fileList }">
							<li style="list-style: none;">
								<span>${ filetmp.fileName }</span>
								<a href="javascript:void(0)" onclick="deleteFile('${ filetmp.fileId }')">X</a>
							</li>
						</c:forEach>
					</ul>
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
												url : '/skhusw/common/jsp/upload/upload.jsp',
												flash_swf_url : '/skhusw/common/js/board/plupload.flash.swf',
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
											$('#norSubmit').click(function(e) {		
												var fileCount = Number($("#fileCount").html());

												if(fileCount == 0)
													$("#norForm").submit();
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
												$("#norForm").submit();
											});

											uploader.bind('QueueChanged', function(up) {
												$("#fileCount").html(uploader.files.length);
											});
										}
									});
				</script>
				<!--  파일 업로드 하는 부분 -->
				<input id="norSubmit" type="button" value="확인"> 
				<input type="hidden" name="idx" value="${ idx }" />
				<input type="hidden" name="articleId" value="${ norArticle.norArticleId }"> 
				<input type="hidden" name="type" value="${ type }"/>
			</div>
		</form>
	</div>
</body>
</html>