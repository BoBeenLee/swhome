package control.login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.bo.CodeBO;
import data.bo.UserBO;
import data.vo.CodeInfo;
import data.vo.user.User;


/**
 * Servlet implementation class checkHandler
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String log = request.getParameter("log");
		String url = null;
		
		if(log == null){
			url = defaultHandler(request);
		} else if(log.equals("in")){
			url = loginHandler(request);
		} else if(log.equals("out")){
			url = logoutHandler(request);
		} else {
			url = defaultHandler(request);
		}
		response.sendRedirect(url);
	}
	
	private String loginHandler(HttpServletRequest request){
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");
		
		// Validate Form Check
		if(userId == null || userId.equals(""))
			return "LoginBox/logoutBox.jsp?faultNum=1";
		if(password == null || password.equals(""))
			return "LoginBox/logoutBox.jsp?faultNum=2";
		// DB Development
	//	System.out.println(userId + " : " + password);
		UserBO userBO = new UserBO();
		int checkNum = userBO.getLoginCheck(userId, password);
		
		if(checkNum == 1)
			return "LoginBox/logoutBox.jsp?faultNum=1";
		else if(checkNum == 2)
			return "LoginBox/logoutBox.jsp?faultNum=2";
		User selectedUser = userBO.getUser(userId);

		// 권한 설정.
		CodeBO codeBO = new CodeBO();
		CodeInfo codeInfo = codeBO.getCode(selectedUser.getCodeId());
		
		//System.out.println(selectedUser.getUserId() + " = " + selectedUser.getUserPw());
		// Session Update
		HashMap<String, String> userInfo = new HashMap<String, String>(); // userid, username, userpw. codeid, codename
		HttpSession hs = request.getSession();
		// session setting
		userInfo.put("userId", selectedUser.getUserId());
		userInfo.put("userName", selectedUser.getUserName());
		userInfo.put("userPw", selectedUser.getUserPw());
		userInfo.put("codeId", (new Integer(selectedUser.getCodeId())).toString());	
		userInfo.put("codeName", codeInfo.getCodeName());
		
		hs.setAttribute("userInfo", userInfo);
		
		return "LoginBox/loginBox.jsp";
	}
	
	private String logoutHandler(HttpServletRequest request){
		HttpSession hs = request.getSession();
		
		hs.removeAttribute("userInfo");
		
		return "LoginBox/logoutBox.jsp";
	}
	
	private String defaultHandler(HttpServletRequest request){
		HttpSession hs = request.getSession();
		HashMap<String, String> userInfo = (HashMap<String, String>) hs.getAttribute("userInfo");
		
		if(userInfo == null)
			return "LoginBox/logoutBox.jsp";
		return "LoginBox/loginBox.jsp";
	}
}
