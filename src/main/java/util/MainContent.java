package util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.bo.ArticleBO;
import data.constant.Board;

/**
 * Servlet implementation class mainContent
 */
@WebServlet("/MainContent")
public class MainContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainContent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("jSuccess", request.getParameter("jSuccess"));
		
		ArticleBO articleBo = new ArticleBO();
		
		request.setAttribute("noticeList", articleBo.getSearchNorArticleRowNum(Board.NOTICE.getValue(), 6));
		request.setAttribute("studentList", articleBo.getSearchNorArticleRowNum(Board.STUDENT.getValue(), 6));
		request.setAttribute("jobList", articleBo.getSearchNorArticleRowNum(Board.JOB.getValue(), 6));
		request.setAttribute("freeList", articleBo.getSearchNorArticleRowNum(Board.FREE.getValue(), 6));
		
		//response.sendRedirect("www.naver.com");
		RequestDispatcher rs = request.getRequestDispatcher("/Main/main_right.jsp");
	//	System.out.println("Hello");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
