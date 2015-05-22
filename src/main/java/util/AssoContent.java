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
 * Servlet implementation class asssoContent
 */
@WebServlet("/AssoContent")
public class AssoContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssoContent() {
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
		String url = "/Sub05/asso_board.jsp";
		HttpSession session = request.getSession();
		
		HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");
		
		// 로그인 안했는 데 접근하면!
		if(hs == null)
			return "/common/jsp/accessFail.jsp";
		String codeName = hs.get("codeName");

		// 졸업생도 아닌데 접근하면 안되지..
		if((codeName.equals("학생") || codeName.equals("학생회") || codeName.equals("직원")))
			return "/common/jsp/accessFail.jsp";
		
		switch(Integer.parseInt(req)){
		case 1 : // 동문회 게시판
			if (type == null || type.equals("search")) {
				procNorArticleList(request, Board.ASSO.getValue());
				url = "/Sub05/asso_board.jsp";
			} else {
				url = (new SelectController()).selectNorArticle(request);
				request.setAttribute("idx", "assoBoard");
			}
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
			
			System.out.println(searchText);
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
