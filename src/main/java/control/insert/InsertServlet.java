package control.insert;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;

import data.bo.ArticleBO;
import data.bo.CalendarBO;
import data.bo.CommentBO;
import data.bo.FileBO;
import data.bo.LotteryBO;
import data.bo.MessageBO;
import data.bo.UserBO;
import data.constant.Board;
import data.constant.Code;
import data.vo.SwCalendar;
import data.vo.article.NorArticle;
import data.vo.article.PartArticle;
import data.vo.comment.Comment;
import data.vo.file.FileInfo;
import data.vo.user.EmployeeUser;
import data.vo.user.ProfessorUser;
import data.vo.user.StudentUser;
import data.vo.user.User;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "D:/Users/cultist/Adobe Flash Builder 4.6/skhusw/WebContent/Upload";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 * +
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String idx = request.getParameter("idx");
		String url = null;
		int checkNum = 0;
		System.out.println(url + "  +++   " + idx);

		if (idx.equals("join")) {
			url = insertJoin(request);
		} else if (idx.equals("noticeBoard")) {
			url = "index.jsp#admin?req=1";
			checkNum = insertNorArticle(request);
		} else if (idx.equals("studentBoard")) {
			url = "index.jsp#admin?req=2";
			checkNum = insertNorArticle(request);
		} else if (idx.equals("photoAlbum")) {
			url = "index.jsp#admin?req=3";
			checkNum = insertNorArticle(request);
		} else if (idx.equals("jobInfo")) {
			url = "index.jsp#info?req=2";
			checkNum = insertNorArticle(request);
		} else if (idx.equals("dataBoard")) {
			url = "index.jsp#board?req=5";
			checkNum = insertNorArticle(request);
		} else if (idx.equals("freeBoard")) {
			url = "index.jsp#board?req=1";
			checkNum = insertNorArticle(request);
		} else if (idx.equals("honorBoard")) {
			url = "index.jsp#board?req=3";
			checkNum = insertNorArticle(request);
		} else if (idx.equals("QABoard")) { //
			url = "index.jsp#board?req=4";
			checkNum = insertPartArticle(request);
		} else if (idx.equals("suggestionBoard")) { //
			url = "index.jsp#board?req=2";
			checkNum = insertPartArticle(request);
		} else if (idx.equals("assoBoard")) {
			url = "index.jsp#asso?req=1";
			checkNum = insertNorArticle(request);
		} else if (idx.equals("messageBox")) {
			url = "index.jsp#mypage?req=2";
			checkNum = insertMessage(request);
		} else if (idx.equals("comment")) {
			String curUrl = request.getParameter("curUrl");

			HttpSession session = request.getSession();
			HashMap<String, String> hs = (HashMap<String, String>) session
					.getAttribute("userInfo");

			if (hs == null) {
				url = "/common/jsp/accessFail.jsp";
			} else {
				url = "index.jsp" + curUrl + "&articleId="
						+ request.getParameter("articleId") + "&type=read";

				// System.out.println(url);
				insertComment(request);
			}
		} else if (idx.equals("lottery")) {
			url = "index.jsp#mypage?req=1";
			checkNum = insertLottery(request);
			if (checkNum == 1)
				url += "&resMsg=포인트가 부족합니다.";
		} else if (idx.equals("recommend")) {
			insertRecommend(request);
		} else if (idx.equals("manage")) {
			String type = request.getParameter("type");
			String boardId = request.getParameter("boardId");
			String userGroup = request.getParameter("userGroup");

			if (type.equals("user")) {
			} else if (type.equals("part")) {
			} else if (type.equals("nor")) {
			} else if (type.equals("calendar")) {
				insertCalendar(request);
				url = "index.jsp#mypage?req=6" + "&userGroup=" + userGroup
						+ "&boardId=" + boardId;
			} else if (type.equals("union")) {
				grantStudentUnion(request);
				url = "/skhusw/common/jsp/popup/close.jsp";
			} else {
				url = "/skhusw/common/jsp/popup/close.jsp";
			}
		}

		response.sendRedirect(url); // 전체 화면을 보낸다.
	}

	private int grantStudentUnion(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String startDate = request.getParameter("sDate");
		String lastDate = request.getParameter("lDate");

		System.out.println(userId + " " + startDate + " " + lastDate);
		
		
		SimpleDateFormat format_yymmdd = new SimpleDateFormat("yyyy-MM-dd"); // 달력
		Date sDate = null, lDate = null;
		try {
			sDate = new Date(format_yymmdd.parse(startDate).getTime());
			lDate = new Date(format_yymmdd.parse(lastDate).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 잘못된 입력값일 때이다.
		if (sDate == null || lDate == null)
			return 1;

		UserBO userBo = new UserBO();
		int checkNum = userBo.getGrantStudentUnion(userId, sDate, lDate);

		return checkNum;
	}

	// 캘린더 목록 삽입
	private int insertCalendar(HttpServletRequest request) {
		String title = request.getParameter("title");
		String startDate = request.getParameter("sDate");
		String lastDate = request.getParameter("lDate");

		SimpleDateFormat format_yymmdd = new SimpleDateFormat("yyyy-MM-dd"); // 달력
																				// 형식
																				// 포맷
		Calendar calendar = Calendar.getInstance();

		try {
			calendar.setTime(format_yymmdd.parse(startDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date sDate = new Date(calendar.getTimeInMillis());

		try {
			calendar.setTime(format_yymmdd.parse(lastDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date lDate = new Date(calendar.getTimeInMillis());

		SwCalendar swCalendar = new SwCalendar(sDate, lDate, title);
		CalendarBO calenderBo = new CalendarBO();

		int checkNum = calenderBo.getInsertCalendar(swCalendar);

		return checkNum;
	}

	private int insertPartArticle(HttpServletRequest request) {
		// Normal 게시물 처리
		String idx = request.getParameter("idx"); // 어느 게시판의 게시물을 쓰고 있는 지에 대한 것.
		String articleTitle = request.getParameter("title");
		String articleContent = request.getParameter("articleContent");
		String noticeYn = (request.getParameter("notice") == null) ? "n" : "y";
		String partPassword = request.getParameter("password");
		String targetArticleId = (request.getParameter("targetArticleId") == null) ? "new"
				: request.getParameter("targetArticleId");

		// System.out.println(targetArticleId + " ++++++++++ ");
		// 공지사항 기간 만들기
		int noticeYear = Integer.parseInt(request.getParameter("noticeYear"));
		int noticeMonth = Integer.parseInt(request.getParameter("noticeMonth")); // Month
																					// 0
																					// -
																					// 11
		int noticeDay = Integer.parseInt(request.getParameter("noticeDay"));

		/* 공지일정을 삽입하기 위한 것 */
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		Date noticeDate = null;
		try {
			date = sd.parse("" + noticeYear + "-" + (noticeMonth) + "-"
					+ noticeDay);
		} catch (ParseException e) {
			System.out.println(e);
		}

		if (date != null) {
			noticeDate = new Date(date.getTime());
		}

		String articleWriterId = null;
		int boardId = 0;

		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		articleWriterId = hs.get("userId");

		if (idx.equals("suggestionBoard")) { // 어느 쪽 Nor 게시판인지 알아내기
			boardId = Board.SUGGESTION.getValue();
		} else if (idx.equals("QABoard")) {
			boardId = Board.QA.getValue();
		}

		// 거리 재기
		int distance = 1, step = 1, processId;

		if (distance == 1 && step == 1) {
			processId = Code.PROCESSING.getValue();
		} else {
			processId = Code.PROCESSFAIL.getValue();
		}

		PartArticle partArticle = new PartArticle(boardId, articleTitle,
				articleContent, articleWriterId, null, noticeYn, 0, 0, 0,
				partPassword, distance, step, null, null, noticeDate,
				processId, null);

		ArticleBO articleBo = new ArticleBO();

		articleBo.getInsertPartArticle(partArticle, targetArticleId);
		System.out.println(partArticle.getPartArticleId());
		// 파일 삽입 처리 부분
		insertFile(request, articleWriterId, partArticle.getPartArticleId());

		return 0;
	}

	private int insertLottery(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		String userId = hs.get("userId");
		LotteryBO lotteryBo = new LotteryBO();

		int checkNum = lotteryBo.getInsertNorArticle(userId);

		System.out.println(checkNum);
		return checkNum;
	}

	private int insertComment(HttpServletRequest request) {
		String articleId = request.getParameter("articleId");
		String commentContent = request.getParameter("content");
		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		String commentWriterId = hs.get("userId");
		String userName = hs.get("userName");

		Comment comment = new Comment(articleId, commentWriterId,
				commentContent, 0, userName);

		CommentBO commentBo = new CommentBO();

		int checkNum = commentBo.getInsertComment(comment);

		return 0;
	}

	private int insertNorArticle(HttpServletRequest request) {
		// Normal 게시물 처리
		String idx = request.getParameter("idx"); // 어느 게시판의 게시물을 쓰고 있는 지에 대한 것.
		String articleTitle = request.getParameter("title");
		String articleContent = request.getParameter("articleContent");
		String noticeYn = (request.getParameter("notice") == null) ? "n" : "y";

		// 공지사항 기간 만들기
		int noticeYear = Integer.parseInt(request.getParameter("noticeYear"));
		int noticeMonth = Integer.parseInt(request.getParameter("noticeMonth")); // Month
																					// 0
																					// -
																					// 11
		int noticeDay = Integer.parseInt(request.getParameter("noticeDay"));

		/* 공지일정을 삽입하기 위한 것 */
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		Date noticeDate = null;
		try {
			date = sd.parse("" + noticeYear + "-" + (noticeMonth) + "-"
					+ noticeDay);
		} catch (ParseException e) {
			System.out.println(e);
		}

		if (date != null) {
			noticeDate = new Date(date.getTime());
		}

		String articleWriterId = null;
		int boardId = 0;

		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		articleWriterId = hs.get("userId");

		if (idx.equals("noticeBoard")) { // 어느 쪽 Nor 게시판인지 알아내기
			boardId = Board.NOTICE.getValue();
		} else if (idx.equals("photoAlbum")) {
			boardId = Board.PHOTO.getValue();
		} else if (idx.equals("studentBoard")) {
			boardId = Board.STUDENT.getValue();
		} else if (idx.equals("jobInfo")) {
			boardId = Board.JOB.getValue();
		} else if (idx.equals("dataBoard")) {
			boardId = Board.DATA.getValue();
		} else if (idx.equals("freeBoard")) {
			boardId = Board.FREE.getValue();
		} else if (idx.equals("honorBoard")) {
			boardId = Board.HONOR.getValue();
		} else if (idx.equals("assoBoard")) {
			boardId = Board.ASSO.getValue();
		}

		NorArticle norArticle = new NorArticle(boardId, articleTitle,
				articleContent, articleWriterId, noticeYn, noticeDate);
		ArticleBO articleBo = new ArticleBO();

		articleBo.getInsertNorArticle(norArticle);
		System.out.println(norArticle.getNorArticleId());
		// 파일 삽입 처리 부분
		insertFile(request, articleWriterId, norArticle.getNorArticleId());

		return 0;
	}

	private int insertFile(HttpServletRequest request, String articleWriterId,
			String articleId) {
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

				do {
					prevFile = file;
					preItemName = itemName;
					itemName = nameNum + fileName;
					file = new File(parentFolder.getAbsolutePath() + "/"
							+ itemName);
					nameNum = "[" + (++n) + "]";
				} while (file != null && file.exists());
				/*                 */

				// 파일 정보 클래스에 저장 후 삽입

				long fileSize = prevFile.length();
				String fileType = preItemName.substring(preItemName
						.lastIndexOf(".") + 1);
				String fileUrl = "Upload/" + articleWriterId + "/"
						+ preItemName;

				FileInfo fileInfo = new FileInfo(preItemName, fileSize,
						fileType, fileUrl, articleId);
				// System.out.println(preItemName + " " + fileSize + " " +
				// fileType
				// + " " + fileUrl + " " + articleId);
				fileBo.getInsertFile(fileInfo);
				// System.out.println(file.getAbsolutePath() + " " +
				// file.length());
			}
		}
		return 0;
	}

	private int insertMessage(HttpServletRequest request) {
		String receiverId = request.getParameter("receiver");
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		String userId = hs.get("userId");
		MessageBO messageBo = new MessageBO();

		int checkNum = messageBo.getSendMsg(receiverId, userId, content);

		// System.out.println(checkNum);
		return 0;
	}

	private int insertRecommend(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		String userId = hs.get("userId");
		String articleId = request.getParameter("articleId");

		ArticleBO articleBo = new ArticleBO();

		int checkNum = articleBo.getAddRecommend(articleId, userId);

		return checkNum;
	}

	// Process User
	private String insertJoin(HttpServletRequest request) {
		String userIdx = request.getParameter("joinGroup");
		String userId = request.getParameter("id");
		String userName = request.getParameter("name");
		String userPw1 = request.getParameter("pwd1");
		String userPw2 = request.getParameter("pwd2");
		String userTel = request.getParameter("phone");
		String userEmail = request.getParameter("email");
		String userQuestion = request.getParameter("question");
		String userAnswer = request.getParameter("answer");
		String userGroupPw = request.getParameter("grouppw");
		String url = null;

		System.out.println(userIdx + " " + userId + " " + userName + " "
				+ userPw1 + " " + userTel + " " + userEmail + " "
				+ userQuestion + " " + userAnswer + " " + userGroupPw);
		if (userId == null || userName == null || userPw1 == null
				|| userPw2 == null || userTel == null || userEmail == null
				|| userQuestion == null || userAnswer == null
				|| userGroupPw == null || userId.equals("")
				|| userName.equals("") || userPw1.equals("")
				|| userPw2.equals("") || userTel.equals("")
				|| userEmail.equals("") || userQuestion.equals("")
				|| userAnswer.equals("") || userGroupPw.equals("")
				|| !userPw1.equals(userPw2)) {
			return "index.jsp#user?req=1";
		}
		User user = new User(userId, userName, userPw1, userTel, userEmail,
				userQuestion, userAnswer, userGroupPw);

		if (userIdx.equals("s")) {
			int userGrade;
			String department = request.getParameter("dept");

			try {
				userGrade = Integer.parseInt(request.getParameter("grade"));
			} catch (Exception e) {
				return "index.jsp#user?req=1";
			}
			// 졸업 년도 계산하기
			Calendar curCal = Calendar.getInstance();
			curCal.set(Calendar.YEAR, curCal.get(Calendar.YEAR)
					+ (5 - userGrade));
			String curDate = (new SimpleDateFormat("yyyy-MM-dd")).format(curCal
					.getTime());
			Date finishYear = Date.valueOf(curDate);
			//
			StudentUser su = new StudentUser(user, userGrade, department,
					finishYear);
			url = procStudent(su);
		} else if (userIdx.equals("p")) {
			String professorLocation = request.getParameter("location");
			String extenstionTel = request.getParameter("extenstion");
			String professorAbility = request.getParameter("ability");
			String professorCareer = request.getParameter("career");
			String professorPosition = request.getParameter("position");

			ProfessorUser pu = new ProfessorUser(user, professorLocation,
					extenstionTel, professorAbility, professorCareer,
					professorPosition);
			url = procProfessor(pu);
		} else if (userIdx.equals("e")) {
			String employeeLocation = request.getParameter("location");
			String extenstionTel = request.getParameter("extenstion");

			EmployeeUser eu = new EmployeeUser(user, employeeLocation,
					extenstionTel);
			url = procEmployee(eu);
		}
		return url;
	}

	private String procStudent(StudentUser su) {
		UserBO userBo = new UserBO();
		int checkNum = userBo.getInsertStudentUser(su);
		// System.out.println(checkNum);
		String url = (checkNum == 0) ? "index.jsp?resMsg=Join User : Success"
				: "index.jsp#user?req=1";

		return url;
	}

	private String procProfessor(ProfessorUser pu) {
		UserBO userBo = new UserBO();
		int checkNum = userBo.getInsertProfessorUser(pu);
		// System.out.println(checkNum);
		String url = (checkNum == 0) ? "index.jsp?resMsg=Join User : Success"
				: "index.jsp#user?req=1";

		return url;
	}

	private String procEmployee(EmployeeUser eu) {
		UserBO userBo = new UserBO();
		int checkNum = userBo.getInsertEmployeeUser(eu);
		// System.out.println(checkNum);
		String url = (checkNum == 0) ? "index.jsp?resMsg=Join User : Success"
				: "index.jsp#user?req=1";

		return url;
	}
}
