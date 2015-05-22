package control.delete;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.bo.ArticleBO;
import data.bo.CalendarBO;
import data.bo.CommentBO;
import data.bo.FileBO;
import data.bo.MessageBO;
import data.bo.UserBO;
import data.vo.file.FileInfo;

/**
 * Servlet implementation class DeletServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CUR_DIR = "D:/Users/cultist/Adobe Flash Builder 4.6/skhusw/WebContent/";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String idx = request.getParameter("idx");
		String url = "";

		// System.out.println(request.getParameter("fileId"));
		if (idx.equals("deleteSelf")) {
			url = deleteSelf(request);
		} else if (idx.equals("noticeBoard")) { // NorArticle을 삭제하기 위한
			url = "index.jsp?#admin?req=1";
			deleteNorArticle(request);
		} else if (idx.equals("studentBoard")) {
			url = "index.jsp#admin?req=2";
			deleteNorArticle(request);
		} else if (idx.equals("photoAlbum")) {
			url = "index.jsp#admin?req=3";
			deleteNorArticle(request);
		} else if (idx.equals("jobInfo")) {
			url = "index.jsp#info?req=2";
			deleteNorArticle(request);
		} else if (idx.equals("dataBoard")) {
			url = "index.jsp#board?req=5";
			deleteNorArticle(request);
		} else if (idx.equals("freeBoard")) {
			url = "index.jsp#board?req=1";
			deleteNorArticle(request);
		} else if (idx.equals("honorBoard")) {
			url = "index.jsp#board?req=3";
			deleteNorArticle(request);
		} else if (idx.equals("QABoard")) { //
			url = "index.jsp#board?req=4";
			deletePartArticle(request);
		} else if (idx.equals("suggestionBoard")) { //
			url = "index.jsp#board?req=2";
			deletePartArticle(request);
		} else if (idx.equals("assoBoard")) {
			url = "index.jsp#asso?req=1";
			deleteNorArticle(request);
		} else if (idx.equals("messageBox")) {
			url = "index.jsp#mypage?req=2";
			deleteMessageBox(request);
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
					deleteComment(request);
				}
			}
		} else if (idx.equals("manage")) {
			String type = request.getParameter("type");
			String boardId = request.getParameter("boardId");
			String userGroup = request.getParameter("userGroup");
			String sDate = request.getParameter("sDate");
			String lDate = request.getParameter("lDate");

			if (type.equals("user")) {
				deleteUser(request);
				url = "/skhusw/common/jsp/popup/close.jsp";
			} else if (type.equals("part")) {
				deletePartArticle(request);
				url = "/skhusw/common/jsp/popup/close.jsp";
			} else if (type.equals("nor")) {
				deleteNorArticle(request);
				url = "/skhusw/common/jsp/popup/close.jsp";
			} else if (type.equals("calendar")) {
				deleteCalendar(request);
				url = "index.jsp#mypage?req=6" + "&userGroup=" + userGroup
						+ "&boardId=" + boardId + "&sDate=" + sDate + "&lDate="
						+ lDate;
			} else if(type.equals("union")){
				System.out.println("Hello");
				revokeStudentUnion(request);
				url = "/skhusw/common/jsp/popup/close.jsp";
			}
		} else if (idx.equals("file")) {
			deleteFile(request);
		}
		response.sendRedirect(url);
	}

	private int revokeStudentUnion(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		int checkNum;
		UserBO userBo = new UserBO();
		
		checkNum = userBo.getRevokeStudentUnion(userId);
		
		return checkNum;
	}

	private int deleteCalendar(HttpServletRequest request) {
		CalendarBO calendarBo = new CalendarBO();
		int calendarId = Integer.parseInt(request.getParameter("calendarId"));

		int checkNum = calendarBo.getDeleteCalendar(calendarId);

		return checkNum;
	}

	private int deleteComment(HttpServletRequest request) {
		int commentId = Integer.parseInt(request.getParameter("commentId"));

		CommentBO commentBo = new CommentBO();
		int checkNum = commentBo.getDeleteComment(commentId);

		return checkNum;
	}

	private int deleteNorArticle(HttpServletRequest request) {
		String articleId = (String) request.getParameter("articleId");
		ArticleBO articleBo = new ArticleBO();

		int checkNum = articleBo.getDeleteNorArticle(articleId);

		// 파일 삭제
		deleteArticleFile(articleId);

		return checkNum;
	}

	private int deletePartArticle(HttpServletRequest request) {
		String articleId = (String) request.getParameter("articleId");
		ArticleBO articleBo = new ArticleBO();

		int checkNum = articleBo.getDeletePartArticle(articleId);

		// 파일 삭제
		deleteArticleFile(articleId);

		return checkNum;
	}

	private int deleteArticleFile(String articleId) {
		FileBO fileBo = new FileBO();

		List<FileInfo> fileList = fileBo.getFileList(articleId);

		for (FileInfo f : fileList) {
			File deleteFile = new File(CUR_DIR + f.getFileUrl());

			if (!deleteFile.delete()) {
				System.out.println("File Delete: Fail");
			} else {
				fileBo.getDeleteFile(f.getFileId());
			}
		}

		return 0;
	}

	private int deleteFile(HttpServletRequest request) {
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		String fileUrl = request.getParameter("fileUrl");
		FileBO fileBo = new FileBO();

		File deleteFile = new File(CUR_DIR + fileUrl);

		if (!deleteFile.delete()) {
			System.out.println("File Delete: Fail");
		}

		int checkNum = fileBo.getDeleteFile(fileId);

		return checkNum;
	}

	private int deleteMessageBox(HttpServletRequest request) {
		String boxName = request.getParameter("box");
		MessageBO messageBo = new MessageBO();

		if (boxName.equals("receive")) {
			String[] checked = request.getParameterValues("receiveCheckBox");

			if (checked != null) {
				for (String receiveMsgId : checked) {
					// System.out.println(receiveMsgId + "++");
					try {
						messageBo.getDeleteReceiveMsg(Integer
								.parseInt(receiveMsgId));
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		} else if (boxName.equals("send")) {
			String[] checked = request.getParameterValues("sendCheckBox");

			if (checked != null) {
				for (String sendMsgId : checked) {
					try {
						messageBo.getDeleteSendMsg(Integer.parseInt(sendMsgId));
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		}
		return 0;
	}

	private int deleteUser(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");

		UserBO userBo = new UserBO();

		int checkNum = userBo.getDeleteUser(userId, userPw);

		return checkNum;
	}

	private String deleteSelf(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		String userPw = request.getParameter("pwd");
		String userId = hs.get("userId");
		String url = null;
		UserBO userBo = new UserBO();

		// 로그인 확인
		int checkNum = userBo.getLoginCheck(userId, userPw);

		if (checkNum == 0) {
			// 회원 삭제
			int checkNum1 = userBo.getDeleteUser(userId, userPw);

			if (checkNum1 == 0) {
				url = "index.jsp?resMsg=UserDelete : Success";
				// 세션 등록 제거
				session.removeAttribute("userInfo");
			} else {
				url = "index.jsp?resMsg=UserDelete : Fail";
			}
		} else {
			url = "index.jsp?resMsg=UserDelete : Fail";
		}
		return url;
	}
}
