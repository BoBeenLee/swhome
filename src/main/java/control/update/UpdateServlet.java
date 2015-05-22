package control.update;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.bo.ArticleBO;
import data.bo.CommentBO;
import data.bo.FileBO;
import data.bo.UserBO;
import data.constant.Code;
import data.vo.file.FileInfo;
import data.vo.user.EmployeeUser;
import data.vo.user.ProfessorUser;
import data.vo.user.StudentUser;
import data.vo.user.User;



/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "D:/Users/cultist/Adobe Flash Builder 4.6/skhusw/WebContent/Upload/";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String idx = request.getParameter("idx");
		String url = null;

		// 업데이트 인덱스
		if (idx.equals("modifyPw")) {
			url = updatePassword(request);
		} else if (idx.equals("studentModify")) {
			url = updateStudent(request);
		} else if (idx.equals("professorModify")) {
			url = updateProfessor(request);
		} else if (idx.equals("employeeModify")) {
			url = updateEmployee(request);
		} else if (idx.equals("noticeBoard")) {
			url = "index.jsp?#admin?req=1";
			updateNorArticle(request);
		} else if (idx.equals("studentBoard")) {
			url = "index.jsp#admin?req=2";
			updateNorArticle(request);
		} else if (idx.equals("photoAlbum")) {
			url = "index.jsp#admin?req=3";
			updateNorArticle(request);
		} else if (idx.equals("jobInfo")) {
			url = "index.jsp#info?req=2";
			updateNorArticle(request);
		} else if (idx.equals("dataBoard")) {
			url = "index.jsp#board?req=5";
			updateNorArticle(request);
		} else if (idx.equals("freeBoard")) {
			url = "index.jsp#board?req=1";
			updateNorArticle(request);
		} else if (idx.equals("honorBoard")) {
			url = "index.jsp#board?req=3";
			updateNorArticle(request);
		} else if (idx.equals("QABoard")) { //
			url = "index.jsp#board?req=4";
			updatePartArticle(request);
		} else if (idx.equals("suggestionBoard")) { //
			url = "index.jsp#board?req=2";
			updatePartArticle(request);
		} else if (idx.equals("assoBoard")) {
			url = "index.jsp#asso?req=1";
			updateNorArticle(request);
		} else if (idx.equals("comment")) {
			String curUrl = request.getParameter("curUrl");

			HttpSession session = request.getSession();
			HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");

			if (hs == null) {
				url = "/common/jsp/accessFail.jsp";
			} else {
				String userId = hs.get("userId");
				String articleWriter = request.getParameter("articleWriter");

				if (!userId.equals(articleWriter)) {
					url = "/common/jsp/accessFail.jsp";
				} else {
					url = "index.jsp" + curUrl + "&articleId="
							+ request.getParameter("articleId") + "&type=read";

					
					// System.out.println(url);
					updateComment(request);
				}
			}
		} else if(idx.equals("manage")){
			String type = request.getParameter("type");
			
			if(type.equals("user")){
				updateStudent(request);
				url = "/skhusw/common/jsp/popup/close.jsp";
			} else if(type.equals("nor")){
				updateNorArticle(request);
				url = "/skhusw/common/jsp/popup/close.jsp";
			} else if(type.equals("part")){
				updatePartArticle(request);
				url = "/skhusw/common/jsp/popup/close.jsp";
			} else if(type.equals("calendar")){} else {
				url = "/skhusw/common/jsp/popup/close.jsp";
			}
		} else if(idx.equals("process")){
			updateProcess(request);
		}

		response.sendRedirect(url);
	}

	private int updateProcess(HttpServletRequest request){
		String articleId = request.getParameter("articleId");
		int state = Integer.parseInt(request.getParameter("state"));
		
		ArticleBO articleBo = new ArticleBO();
		
		articleBo.getUpdateProcess(articleId, state);
		
		return 0;
	}
	
	private int updateComment(HttpServletRequest request) {
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String commentContent = request.getParameter("content");

		CommentBO commentBo = new CommentBO();

		commentBo.getUpdateComment(commentId, commentContent);

		return 0;
	}

	private int updateNorArticle(HttpServletRequest request) {
		String articleTitle = request.getParameter("title");
		String articleContent = request.getParameter("articleContent");
		String noticeYn = (request.getParameter("notice") == null) ? "n" : "y";
		String norArticleId = request.getParameter("articleId");
		String articleWriterId = request.getParameter("articleWriterId");
		
		// 공지사항 기간 만들기
		int noticeYear = Integer.parseInt(request.getParameter("noticeYear"));
		int noticeMonth = Integer.parseInt(request.getParameter("noticeMonth")); // Month
																					// 0
																					// -
																					// 11
		int noticeDay = Integer.parseInt(request.getParameter("noticeDay"));

		/* 공지일정을 업테이드하기 위한 것 */
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		Date noticeDate = null;
		try {
			date = sd.parse("" + noticeYear + "-" + (noticeMonth) + "-"
					+ noticeDay);
		} catch (ParseException e) {
			System.out.println(e);
		}

		if (date != null)
			noticeDate = new Date(date.getTime());

		ArticleBO articleBo = new ArticleBO();

		// 글 업데이트
		articleBo.getUpdateNorArticle(norArticleId, articleTitle,
				articleContent, noticeYn, noticeDate);
		
		// 파일 첨부 생성
		insertFile(request, articleWriterId, norArticleId);
		
		return 0;
	}

	private int updatePartArticle(HttpServletRequest request) {
		String articleTitle = request.getParameter("title");
		String articleContent = request.getParameter("articleContent");
		String noticeYn = (request.getParameter("notice") == null) ? "n" : "y";
		String partArticleId = request.getParameter("articleId");
		String partPassword = request.getParameter("password");
		String articleWriterId = request.getParameter("articleWriterId");
		
		// 공지사항 기간 만들기
		int noticeYear = Integer.parseInt(request.getParameter("noticeYear"));
		int noticeMonth = Integer.parseInt(request.getParameter("noticeMonth")); // Month
																					// 0
																					// -
																					// 11
		int noticeDay = Integer.parseInt(request.getParameter("noticeDay"));

		/* 공지일정을 업테이드하기 위한 것 */
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		Date noticeDate = null;
		try {
			date = sd.parse("" + noticeYear + "-" + (noticeMonth) + "-"
					+ noticeDay);
		} catch (ParseException e) {
			System.out.println(e);
		}

		if (date != null)
			noticeDate = new Date(date.getTime());

		ArticleBO articleBo = new ArticleBO();

		// 글 업데이트
		articleBo.getUpdatePartArticle(partArticleId, articleTitle, articleContent, noticeYn, partPassword, noticeDate);

		// 파일 첨부 생성
		insertFile(request, articleWriterId, partArticleId);
		
		return 0;
	}
	
	// 파일 삽입
	private int insertFile(HttpServletRequest request, String articleWriterId, String articleId) {
		String[] fileNames = request.getParameterValues("fileName");
		FileBO fileBo = new FileBO();

		if (fileNames != null) {
			for (int i = 0; i < fileNames.length; i++) {
				String fileName = fileNames[i];
				
				// 동일 파일이 존재할 경우 처리
				File parentFolder = new File(UPLOAD_DIR + "/" + articleWriterId);
				File prevFile = null, file = null;
				String itemName = fileName, preItemName = null;
				String nameNum = "";
				int n = 0;
				
				do{
					prevFile = file; preItemName = itemName;
					itemName = nameNum + fileName;
					file = new File(parentFolder.getAbsolutePath() + "/" + itemName);
					nameNum = "[" + (++n) + "]";
				} while(file != null && file.exists());
				/*                 */
				
				// 파일 정보 클래스에 저장 후 삽입
				
				long fileSize = prevFile.length();
				String fileType = preItemName
						.substring(preItemName.lastIndexOf(".") + 1);
				String fileUrl = "Upload/" + articleWriterId + "/" + preItemName;

				FileInfo fileInfo = new FileInfo(preItemName, fileSize, fileType,
						fileUrl, articleId);
				// System.out.println(preItemName + " " + fileSize + " " + fileType
				// + " " + fileUrl + " " + articleId);
				fileBo.getInsertFile(fileInfo);
				// System.out.println(file.getAbsolutePath() + " " +
				// file.length());
			}
		}
		return 0;
	}
	
	
	// 회원 관련 처리
	private String updateEmployee(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		String userId = hs.get("userId");
		UserBO userBo = new UserBO();
		String userName, userTel, userEmail, question, answer, officeLocation, extenstionTel;
		String url = null;

		userName = request.getParameter("name");
		userEmail = request.getParameter("email");
		userTel = request.getParameter("phone");
		question = request.getParameter("question");
		answer = request.getParameter("answer");
		officeLocation = request.getParameter("location");
		extenstionTel = request.getParameter("extenstion");

		EmployeeUser eu = new EmployeeUser(new User(userId, userName, null,
				userTel, userEmail, question, answer, null), officeLocation,
				extenstionTel);
		int checkNum = userBo.getUpdateEmployeeUser(eu);

		if (checkNum == 0) {
			url = "index.jsp?resMsg=UserUpdate : Success";
		}
		return url;
	}

	private String updateProfessor(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		String userId = hs.get("userId");
		UserBO userBo = new UserBO();
		String userName, userTel, userEmail, question, answer, professorLocation, extenstionTel, professorAbility, professorCareer, professorPosition;
		String url = null;

		userName = request.getParameter("name");
		userEmail = request.getParameter("email");
		userTel = request.getParameter("phone");
		question = request.getParameter("question");
		answer = request.getParameter("answer");
		professorLocation = request.getParameter("location");
		extenstionTel = request.getParameter("extenstion");
		professorAbility = request.getParameter("ability");
		professorCareer = request.getParameter("career");
		professorPosition = request.getParameter("position");
		
		ProfessorUser pu = new ProfessorUser(new User(userId, userName, null,
				userTel, userEmail, question, answer, null), professorLocation,
				extenstionTel, professorAbility, professorCareer, professorPosition);
		int checkNum = userBo.getUpdateProfessorUser(pu);

		if (checkNum == 0) {
			url = "index.jsp?resMsg=UserUpdate : Success";
		}
		return url;
	}

	private String updateStudent(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		String userId = hs.get("userId");
		UserBO userBo = new UserBO();
		String userName, userTel, userEmail, question, answer, department;
		int userGrade;
		String url = null;

		userName = request.getParameter("name");
		userEmail = request.getParameter("email");
		userTel = request.getParameter("phone");
		question = request.getParameter("question");
		answer = request.getParameter("answer");
		userGrade = Integer.parseInt(request.getParameter("grade"));
		department = request.getParameter("dept");

		StudentUser su = new StudentUser(new User(userId, userName, null,
				userTel, userEmail, question, answer, null), userGrade,
				department, null);
		int checkNum = userBo.getUpdateStudentUser(su);

		if (checkNum == 0) {
			url = "index.jsp?resMsg=UserUpdate : Success";
		}
		return url;
	}

	private String updatePassword(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		String userId = hs.get("userId");
		String userPw = request.getParameter("curpwd");
		String url = null;

		UserBO userBo = new UserBO();
		int checkNum = userBo.getLoginCheck(userId, userPw);

		if (checkNum == 0) {
			String pwd1 = request.getParameter("pwd1");
			String pwd2 = request.getParameter("pwd2");

			if (pwd1.equals(pwd2)) {
				// 삭제 프로시저가 제대로 작동했을 경우
				int checkNum1 = userBo.getUpdatePassword(userId, pwd1);

				if (checkNum1 == 0)
					url = "index.jsp?resMsg=Modify Password : Success";
				else
					url = "index.jsp#mypage?req=3";
			} else
				url = "index.jsp#mypage?req=3";
		} else {
			url = "index.jsp#mypage?req=3";
		}
		return url;
	}
	
	
}
