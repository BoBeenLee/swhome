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
import data.vo.article.PartArticle;

/**
 * Servlet implementation class boardContent
 */
@WebServlet("/BoardContent")
public class BoardContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardContent() {

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
		request.setCharacterEncoding("UTF-8");
		
		String req_idx = request.getParameter("req");
		String page_idx = null;

		page_idx = swRightSwitcher(request, req_idx);

		if (page_idx == null) {
			page_idx = request.getHeader("referer");
			System.out.println(page_idx);
		}
		
		RequestDispatcher rs = request.getRequestDispatcher(page_idx);
		rs.forward(request, response);
	}

	private String swRightSwitcher(HttpServletRequest request, String req) {
		String type = request.getParameter("type");
		String url = "/Sub04/free_board.jsp";

		switch (Integer.parseInt(req)) {
		case 1: // 자유게시판
			if (type == null || type.equals("search")) {
				procNorArticleList(request, Board.FREE.getValue());
				url = "/Sub04/free_board.jsp";
			} else {
				if(type.equals("write")){
					HttpSession session = request.getSession();
					HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");
					
					if(hs == null)
						return "/common/jsp/accessFail.jsp";
				}
				
				url = (new SelectController()).selectNorArticle(request);
				request.setAttribute("idx", "freeBoard");
			}
			break;
		case 2: // 건의사항
			if (type == null || type.equals("search")) {
				procPartArticleList(request, Board.SUGGESTION.getValue());
				url = "/Sub04/suggestion_board.jsp";
			} else {
				if(type.equals("write")){
					HttpSession session = request.getSession();
					HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");
					
					if(hs == null)
						return "/common/jsp/accessFail.jsp";
				}
				
				url = (new SelectController()).selectPartArticle(request);
				request.setAttribute("idx", "suggestionBoard");
			}
			break;
		case 3: // 명예전당
			if (type == null || type.equals("search")) {
				procNorArticleList(request, Board.HONOR.getValue());
				url = "/Sub04/honor_board.jsp";
			} else {
				if(type.equals("write")){
					HttpSession session = request.getSession();
					HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");
					
					if(hs == null)
						return "/common/jsp/accessFail.jsp";
					
					String codeName = hs.get("codeName");
					
					if(!( codeName.equals("관리자") || codeName.equals("교수") ))
						return "/common/jsp/accessFail.jsp";
				}
				
				url = (new SelectController()).selectNorArticle(request);
				request.setAttribute("idx", "honorBoard");
			}
			break;
		case 4: // Q/A
			if (type == null || type.equals("search")) {
				procPartArticleList(request, Board.QA.getValue());
				url = "/Sub04/QA_board.jsp";
			} else {
				if(type.equals("write")){
					HttpSession session = request.getSession();
					HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");
					
					if(hs == null)
						return "/common/jsp/accessFail.jsp";
				}
				
				url = (new SelectController()).selectPartArticle(request);
				request.setAttribute("idx", "QABoard");
			}
			break;
		case 5: // 자료실
			if (type == null || type.equals("search")) {
				procNorArticleList(request, Board.DATA.getValue());
				url = "/Sub04/data_board.jsp";
			} else {
				if(type.equals("write")){
					HttpSession session = request.getSession();
					HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");
					
					if(hs == null)
						return "/common/jsp/accessFail.jsp";
				}
				
				url = (new SelectController()).selectNorArticle(request);
				request.setAttribute("idx", "dataBoard");
			}
			break;
		}
		return url;
	}

	public void procNorArticleList(HttpServletRequest request, int boardId) {
		ArticleBO articleBo = new ArticleBO();
		List<NorArticle> articleList = null;

		String searchType = request.getParameter("searchType");

		if (searchType == null) {
			articleList = articleBo.getNorArticleList(boardId);
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

	public void procPartArticleList(HttpServletRequest request, int boardId) {
		ArticleBO articleBo = new ArticleBO();
		List<PartArticle> articleList = null;

		String searchType = request.getParameter("searchType");

		if (searchType == null) {
			articleList = articleBo.getPartArticleList(boardId);
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
			
			articleList = articleBo.getSearchPartArticle(parameter);
		}

		request.setAttribute("articleList", articleList);
		request.setAttribute("articleSize", articleList.size());
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
