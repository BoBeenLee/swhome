package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import data.constant.Board;
import data.vo.article.NorArticle;

/**
 * Servlet implementation class infoContent
 */
@WebServlet("/InfoContent")
public class InfoContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoContent() {
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
		
		page_idx = swRightSwitcher(request, req_idx);

		RequestDispatcher rs = request.getRequestDispatcher(page_idx);
		rs.forward(request, response);
	}
	
	private String swRightSwitcher(HttpServletRequest request, String req){
		String type = request.getParameter("type");
		String url = "/Sub03/cert_info.html";
		
		switch(Integer.parseInt(req)){
		case 1 :
			//request.setAttribute("name", "major"); process
			
			url = "/Sub03/cert_info.html";
			break;
		case 2 : // 취업정보
			if (type == null || type.equals("search")) {
				procNorArticleList(request, Board.JOB.getValue());
				url = "/Sub03/job_info.jsp";
			} else {
				if(type.equals("write")){
					HttpSession session = request.getSession();
					HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");
					
					if(hs == null)
						return "/common/jsp/accessFail.jsp";
					
					String codeName = hs.get("codeName");
					
					if(!( codeName.equals("관리자") || codeName.equals("교수") || codeName.equals("직원") ))
						return "/common/jsp/accessFail.jsp";
				}
				
				url = (new SelectController()).selectNorArticle(request);
				request.setAttribute("idx", "jobInfo");
			}
			break;
		case 3 :
			//request.setAttribute("name", "subject");
			
			url = "/Sub03/internship_info.html";
			break;
		}
		return url;
	}

	
	public void procNorArticleList(HttpServletRequest request, int boardId){
		ArticleBO articleBo = new ArticleBO();
		List<NorArticle> articleList = null;
		
		String searchType = request.getParameter("searchType");
		
		if(searchType == null){
			articleList = articleBo
				.getNorArticleList(boardId);
		} else {
			HashMap<String, String> parameter = new HashMap<String, String>();
			
			String searchText = null;
			try {
				searchText = URLDecoder.decode(request.getParameter("searchText"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
			parameter.put("title", "");
			parameter.put("content", "");
			parameter.put("writer", "");
			parameter.put("boardId", String.valueOf(boardId));
			
			if(searchType.equals("title"))
				parameter.put("title", searchText);
			if(searchType.equals("content"))
				parameter.put("content", searchText);
			if(searchType.equals("writer"))
				parameter.put("writer", searchText);
			
			articleList = articleBo.getSearchNorArticle(parameter);
		} 
		
		request.setAttribute("articleList", articleList);
		request.setAttribute("articleSize", articleList.size());
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
