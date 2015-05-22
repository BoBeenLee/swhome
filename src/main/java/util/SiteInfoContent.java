package util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SiteInfoContent
 */
@WebServlet("/SiteInfoContent")
public class SiteInfoContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiteInfoContent() {
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
		
		page_idx = siteInfoRightSwitcher(request, req_idx);

		RequestDispatcher rs = request.getRequestDispatcher(page_idx);
		rs.forward(request, response);
	}

	private String siteInfoRightSwitcher(HttpServletRequest request,
			String req) {
		// TODO Auto-generated method stub
		switch(Integer.parseInt(req)){
		case 1 :
			//request.setAttribute("name", "major"); process
			return "/Sub08/siteMap.html";
		case 2 :
			//request.setAttribute("name", "professor");
			return "/Sub08/help.html";
		}
		return "/Sub08/siteMap.html";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
