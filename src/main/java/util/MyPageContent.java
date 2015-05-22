package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.select.SelectController;

import data.bo.ArticleBO;
import data.bo.CalendarBO;
import data.bo.LotteryBO;
import data.bo.MessageBO;
import data.bo.UserBO;
import data.constant.Board;
import data.constant.Code;
import data.vo.SwCalendar;
import data.vo.lottery.Lottery;
import data.vo.lottery.LotteryPoint;

/**
 * Servlet implementation class MyPageContent
 */
@WebServlet("/MyPageContent")
public class MyPageContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyPageContent() {
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
		String req_idx = request.getParameter("req");
		String page_idx = null;

		HttpSession hs = request.getSession();

		page_idx = myPageRightSwitcher(request, req_idx);

		RequestDispatcher rs = request.getRequestDispatcher(page_idx);
		rs.forward(request, response);
	}

	private String myPageRightSwitcher(HttpServletRequest request, String req) {
		// 회원 관련 정보 얻기 위한
		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		
		// 유저가 로그인 안했는 데 자꾸 접근하려 든다면!
		if(hs == null)
			return "/common/jsp/accessFail.jsp";
		
		String userId = hs.get("userId");
		String codeName = hs.get("codeName");

		String type = request.getParameter("type");
		String url = "/Sub07/lotteryEvent.jsp";
		String msg = request.getParameter("resMsg");

		// System.out.println(codeName);

		switch (Integer.parseInt(req)) {
		case 1: // 이벤트
			// 이벤트 할 수 있는 넘도 아닌 데 자꾸 이벤트에 들어가려 한다면!
			if(codeName.equals("교수") || codeName.equals("관리자") || codeName.equals("직원") || codeName.equals("졸업생"))
				return "/common/jsp/accessFail.jsp";
			
			// lotteryPoint Update
			LotteryBO lotteryBo = new LotteryBO();
			int codeId = Integer.parseInt(hs.get("codeId"));

			// 로그인 점수 올리고, 유저 총 포인트 갱신 한다.
			int checkNum = lotteryBo.getPointUserLottery(userId, codeId);

			// System.out.println(checkNum + " " + codeId + " " + userId);
			// 총 포인트를 추출한다.
			int totalPoint = lotteryBo.getTotalPoint(userId);
			request.setAttribute("totalPoint", totalPoint);

			//
			String psYear = request.getParameter("pointYear");
			String psMonth = request.getParameter("pointMonth");
			String lsYear = request.getParameter("lotteryYear");
			String lsMonth = request.getParameter("lotteryMonth");

			Calendar psDate = Calendar.getInstance();
			Calendar plDate = Calendar.getInstance();
			Calendar lsDate = Calendar.getInstance();
			Calendar llDate = Calendar.getInstance();

			if (psYear != null && psMonth != null && lsYear != null
					&& lsMonth != null) {
				int pYear = Integer.parseInt(psYear);
				int pMonth = Integer.parseInt(psMonth);
				int lYear = Integer.parseInt(lsYear);
				int lMonth = Integer.parseInt(lsMonth);

				psDate.set(Calendar.YEAR, pYear);
				plDate.set(Calendar.YEAR, pYear);
				psDate.set(Calendar.MONTH, pMonth);
				plDate.set(Calendar.MONTH, pMonth);

				lsDate.set(Calendar.YEAR, lYear);
				llDate.set(Calendar.YEAR, lYear);
				lsDate.set(Calendar.MONTH, lMonth);
				llDate.set(Calendar.MONTH, lMonth);
			}

			psDate.set(Calendar.DATE, 1);
			lsDate.set(Calendar.DATE, 1);
			plDate.set(Calendar.DATE, plDate.getActualMaximum(Calendar.DATE));
			llDate.set(Calendar.DATE, llDate.getActualMaximum(Calendar.DATE));

			request.setAttribute("pointMonth", psDate.get(Calendar.MONTH) + 1);
			request.setAttribute("pointYear", psDate.get(Calendar.YEAR));
			request.setAttribute("lotteryMonth", lsDate.get(Calendar.MONTH) + 1);
			request.setAttribute("lotteryYear", lsDate.get(Calendar.YEAR));

			// List 출력하기 위한 준비
			List<LotteryPoint> lotteryPointList = lotteryBo
					.getLotteryPointList(userId,
							new Date(psDate.getTimeInMillis()),
							new Date(plDate.getTimeInMillis()));
			List<Lottery> lotteryList = lotteryBo.getLotteryList(userId,
					new Date(lsDate.getTimeInMillis()),
					new Date(llDate.getTimeInMillis()));
			List<Lottery> lotteryWinList = lotteryBo.getLotteryWinList(userId);

			if (lotteryPointList != null) {
				request.setAttribute("lotteryPointList", lotteryPointList);
				request.setAttribute("lotteryPointSize",
						lotteryPointList.size());
			}
			if (lotteryList != null) {
				request.setAttribute("lotteryList", lotteryList);
				request.setAttribute("lotterySize", lotteryList.size());
			}
			if (lotteryWinList != null) {
				request.setAttribute("lotteryWinList", lotteryWinList);
				request.setAttribute("lotteryWinSize", lotteryWinList.size());
			}

			url = "/Sub07/lotteryEvent.jsp";
			break;
		case 2: // 쪽지함
			if (type == null) {
				MessageBO messageBo = new MessageBO();

				request.setAttribute("sendMessageList",
						messageBo.getSendList(userId));
				request.setAttribute("receiveMessageList",
						messageBo.getReceiveList(userId));
				url = "/Sub07/messageBox.jsp";
			} else {
				String box = request.getParameter("box");

				if (box.equals("send")) {
					url = (new SelectController()).selectMsgSend(request);
				} else if (box.equals("receive")) {
					url = (new SelectController()).selectMsgReceive(request);
				}
				request.setAttribute("idx", "messageBox");
			}
			break;
		case 3: // 패스워드 수정

			url = "/Sub07/modifyPw.jsp";
			break;
		case 4: // 유저 정보 수정

			url = "/Sub07/modifyUserInfo.jsp";
			break;
		case 5: // 회원 삭제

			url = "/Sub07/deleteSelf.jsp";
			break;
		case 6:
			// 관리자가 아닌 넘은 퇴출해야함. ㅇㅇ
			if(!codeName.equals("관리자"))
				return "/common/jsp/accessFail.jsp";
			
			String articleSearchType = request
					.getParameter("articleSearchType");
			String userSearchType = request.getParameter("userSearchType");
			
			String articleSearchText = null;
			String userSearchText = null;
			String boardSid = request.getParameter("boardId");

			int boardId = 0;
			
			try {
				articleSearchText = URLDecoder.decode((request
						.getParameter("articleSearchText") == null)? "" : request
								.getParameter("articleSearchText"), "UTF-8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};			

			try {
				userSearchText = URLDecoder.decode(request.getParameter("userSearchText"), "UTF-8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				userSearchText = null;
			}
			
			if (boardSid != null) {
				try {
					boardId = Integer.parseInt(boardSid);
				} catch (Exception e) {
					System.out.println(e);
				}
			}

			String userGroup = request.getParameter("userGroup");

			// Default
			if (userGroup == null)
				userGroup = "s";
			if (boardId == 0)
				boardId = Board.NOTICE.getValue();

			/* 게시판 종류에 따라 게시글 관리 */
			List articleList = null;
			ArticleBO articleBo = new ArticleBO();

			/* Board 결정 */
			if (Board.QA.getValue() == boardId
					|| Board.SUGGESTION.getValue() == boardId) {
				if (articleSearchText == null || articleSearchText.equals("")) {
					articleList = articleBo.getPartArticleList(boardId);
				} else {
					HashMap<String, String> parameter = new HashMap<String, String>();
					
					parameter.put("title", "");
					parameter.put("content", "");
					parameter.put("writer", "");
					parameter.put("boardId", String.valueOf(boardId));
					
					if(articleSearchType.equals("title"))
						parameter.put("title", articleSearchText);
					if(articleSearchType.equals("content"))
						parameter.put("content", articleSearchText);
					if(articleSearchType.equals("writer"))
						parameter.put("writer", articleSearchText);
					
					articleList = articleBo.getSearchPartArticle(parameter);
				}
				request.setAttribute("boardType", "part");
			} else {
				if (articleSearchText == null || articleSearchText.equals("")) {
					articleList = articleBo.getNorArticleList(boardId);
				} else {
					HashMap<String, String> parameter = new HashMap<String, String>();
					
					parameter.put("title", "");
					parameter.put("content", "");
					parameter.put("writer", "");
					parameter.put("boardId", String.valueOf(boardId));
					
					if(articleSearchType.equals("title"))
						parameter.put("title", articleSearchText);
					if(articleSearchType.equals("content"))
						parameter.put("content", articleSearchText);
					if(articleSearchType.equals("writer"))
						parameter.put("writer", articleSearchText);
						
					articleList = articleBo.getSearchNorArticle(parameter);
				}
				request.setAttribute("boardType", "nor");
			}

			request.setAttribute("articleSearchType", articleSearchType);
			request.setAttribute("articleSearchText", articleSearchText);
			request.setAttribute("boardId", boardId);
			request.setAttribute("articleList", articleList);
			request.setAttribute("articleSize", articleList.size());

			/* 회원 종류에 따라 회원 관리 */
			List userList = null;
			UserBO userBo = new UserBO();
			HashMap<String, String> parameter = new HashMap<String, String>();
			
			if(userSearchText != null){
				if(userSearchType.equals("userId")){
					parameter.put("userId", userSearchText);
					parameter.put("userName", "");
				} else if(userSearchType.equals("userName")){
					parameter.put("userId", "");
					parameter.put("userName", userSearchText);
				}
			}
			if (userGroup.equals("s")) {
				parameter.put("codeId", String.valueOf(Code.STUDENT.getValue()));
				if (userSearchText == null || userSearchText.equals(""))
					userList = userBo.getStudentUserList();
				else
					userList = userBo.getStudentUser(parameter);
			} else if (userGroup.equals("p")) {
				parameter.put("codeId", String.valueOf(Code.PROFESSOR.getValue()));
				if (userSearchText == null || userSearchText.equals(""))
					userList = userBo.getProfessorUserList();
				else
					userList = userBo.getProfessorUser(parameter);
			} else if (userGroup.equals("e")) {
				parameter.put("codeId", String.valueOf(Code.EMPLOYEE.getValue()));
				if (userSearchText == null || userSearchText.equals(""))
					userList = userBo.getEmployeeUserList();
				else
					userList = userBo.getEmployeeUser(parameter);
			}
			
			request.setAttribute("userSearchType", userSearchType);
			request.setAttribute("userSearchText", userSearchText);
			request.setAttribute("userGroup", userGroup);
			request.setAttribute("userList", userList);
			request.setAttribute("userSize", userList.size());

			/* 달력 날짜에 따라 달력 관리 */
			List<SwCalendar> calendarList = null;
			CalendarBO calendarBo = new CalendarBO();
			String sDate = request.getParameter("sDate");
			String lDate = request.getParameter("lDate");

			if (sDate == null || lDate == null) {
				sDate = "1990-01-01";
				lDate = "3000-01-01";
			}

			parameter.put("sDate", sDate);
			parameter.put("lDate", lDate);

			calendarList = calendarBo.getSwCalendarList(parameter);

			request.setAttribute("sDate", sDate);
			request.setAttribute("lDate", lDate);
			request.setAttribute("calendarList", calendarList);
			request.setAttribute("calendarSize", calendarList.size());

			url = "/Sub07/administration.jsp";
		}
		if (msg != null)
			request.setAttribute("resMsg", msg);

		return url;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
