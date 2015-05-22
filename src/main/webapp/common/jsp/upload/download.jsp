<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.BufferedOutputStream"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.BufferedInputStream"%>
<%@ page import="java.io.File"%>
<%@ page import="java.net.*"%>

<%
	// 파일 위치 알아내기
	String FILE_DIR = request.getSession().getServletContext().getRealPath("/");

	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	String url = request.getParameter("fileUrl");
	
	response.setContentType("text/html;charset=UTF-8");
	String fileUrl = FILE_DIR + url;
	File file = new File(fileUrl);

	/* 출력 테스트
	System.out.println(fileUrl);
	System.out.println(file.getAbsoluteFile());
	System.out.println(file.length());
	 */
	 
	// 이렇게 바꾸어줘야지 한글이 안깨진다. 이유는 정확히 모르지만.. UTF8이 아니어서 그런거 같다.
	String fileName = URLEncoder.encode(file.getName(), "UTF8");
	
	byte b[] = new byte[(int) file.length()];
	if (file.length() > 0 && file.isFile()) {
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition",
				"attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"8859_1") + ";");
		// 인코딩 설정값도 변경
		response.setHeader("Content-Transper-Encoding", "binary");
		// 사이즈도 알려줘야 한다
		response.setContentLength((int)file.length());

		BufferedInputStream input = new BufferedInputStream(
				new FileInputStream(file));
		BufferedOutputStream output = new BufferedOutputStream(
				response.getOutputStream());

		int read = 0;
		try {
			while ((read = input.read(b)) != -1) {
				output.write(b, 0, read);
			}
			output.close();
			input.close();
			out.clear();

			out = pageContext.pushBody();
		} catch (Exception e) {
			System.out.println("에러메세지: " + e.getMessage());
		} finally {
			if (output != null) {
				output.close();
			}
			if (input != null) {
				input.close();
			}
		}
	} else {
%>
<script>
	alert("파일이 존재 하지 않습니다.");

	self.close();
</script>
<%
	}
%>