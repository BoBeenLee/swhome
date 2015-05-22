package control.confirm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.bo.MessageBO;
import data.bo.UserBO;
import data.constant.Code;
import data.vo.user.EmployeeUser;
import data.vo.user.ProfessorUser;
import data.vo.user.StudentUser;

/**
 * Servlet implementation class ConfirmServlet
 */
@WebServlet("/ConfirmServlet")
public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmServlet() {
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
		/*
		 * MessageBO test = new MessageBO(); test.getTest();
		 */
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
		String url = null;

		if (idx == null) {
			url = "/Sub07/lotteryEvent.jsp";
		} else if (idx.equals("modifyUserInfo")) {
			url = confirmModifyUserInfo(request);
		} else if (idx.equals("manage")) {
			url = confirmModifyManage(request);
		}

		// System.out.println(url);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private String confirmModifyManage(HttpServletRequest request){
		UserBO userBo = new UserBO();
		
		int codeId = Integer.parseInt(request.getParameter("codeId"));
		String userId = request.getParameter("userId");
		
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("userId", userId);
		hashmap.put("codeId", String.valueOf(codeId));
		
		if (codeId == Code.GRADUATE.getValue()
				|| codeId == Code.STUDENT.getValue()
				|| codeId == Code.STUDENTUNION.getValue()) { // 졸업생, 학생, 학생회
			List<StudentUser> studentList = userBo.getStudentUser(hashmap);

			request.setAttribute("student", studentList.get(0));
			return "/Sub07/modify/studentModify.jsp";
		} else if (codeId == Code.PROFESSOR.getValue()) { // 교수
			List<ProfessorUser> professorList = userBo
					.getProfessorUser(hashmap);

			request.setAttribute("professor", professorList.get(0));
			return "/Sub07/modify/professorModify.jsp";
		} else if (codeId == Code.EMPLOYEE.getValue()) { // 직원	
			List<EmployeeUser> employeeList = userBo.getEmployeeUser(hashmap);

			request.setAttribute("employee", employeeList.get(0));
			return "/Sub07/modify/employeeModify.jsp";
		}
		
		return "/common/jsp/accessFail.jsp";
	}
	
	private String confirmModifyUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session
				.getAttribute("userInfo");
		String userId = hs.get("userId");
		String userPw = request.getParameter("pwd");

		if (userId == null || userPw == null) {
			return "/common/jsp/accessFail.jsp";
		}
		UserBO userBo = new UserBO();
		int checkNum = userBo.getLoginCheck(userId, userPw);

		if (checkNum != 0) {
			return request.getLocalAddr();
		}

		int codeId = Integer.parseInt(hs.get("codeId"));
		// System.out.println(codeId);

		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("userId", userId);
		hashmap.put("codeId", hs.get("codeId"));
		
		if (codeId == Code.GRADUATE.getValue() || codeId == Code.STUDENT.getValue()
				|| codeId == Code.STUDENTUNION.getValue()) { // 졸업생, 학생, 학생
			List<StudentUser> studentList = userBo.getStudentUser(hashmap);

			request.setAttribute("student", studentList.get(0));
			return "/Sub07/modify/studentModify.jsp";
		} else if (codeId == Code.PROFESSOR.getValue()) { // 교수
			List<ProfessorUser> professorList = userBo
					.getProfessorUser(hashmap);

			request.setAttribute("professor", professorList.get(0));
			return "/Sub07/modify/professorModify.jsp";
		} else if (codeId == Code.EMPLOYEE.getValue()) { // 직원
			List<EmployeeUser> employeeList = userBo.getEmployeeUser(hashmap);

			request.setAttribute("employee", employeeList.get(0));
			return "/Sub07/modify/employeeModify.jsp";
		}
		return "/common/jsp/accessFail.jsp";
	}
}
