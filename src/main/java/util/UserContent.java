package util;


import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.select.SelectController;

/**
 * Servlet implementation class UserContent
 */
@WebServlet("/UserContent")
public class UserContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserContent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String req_idx = request.getParameter("req");
		String page_idx = null;

		page_idx = UserRightSwitcher(request, req_idx);
	
		RequestDispatcher rs = request.getRequestDispatcher(page_idx);
		rs.forward(request, response);
	}

	private String UserRightSwitcher(HttpServletRequest request, String req) {
		HttpSession session = request.getSession();
		HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");
		String url = "/Sub06/join.jsp";
		
		// 너 이미 로그인 되어있는 데, 왜 자꾸 이런데 들어가냐
		if(hs != null)
			return "/common/jsp/accessFail.jsp";
		
		switch(Integer.parseInt(req)){
		case 1 :
			
			url = "/Sub06/join.jsp";
			break;
		case 2 :
			
			url = "/Sub06/search.jsp";
			break;
		}
		return url;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
