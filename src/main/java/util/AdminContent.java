package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;
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
import data.vo.article.PhotoArticle;

/**
 * Servlet implementation class adminContent
 */
@WebServlet("/AdminContent")
public class AdminContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminContent() {
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
		request.setCharacterEncoding("utf-8");
		
		String req_idx = request.getParameter("req");
		String page_idx = null;

		page_idx = swRightSwitcher(request, req_idx);

		RequestDispatcher rs = request.getRequestDispatcher(page_idx);
		rs.forward(request, response);
	}

	private String swRightSwitcher(HttpServletRequest request, String req) {
		String type = request.getParameter("type");
		String url = "/Sub02/notice_board.jsp";
		
		switch (Integer.parseInt(req)) {
		case 1: // 공지사항
			if (type == null || type.equals("search")) {
				procNorArticleList(request, Board.NOTICE.getValue());
				
				String pageNum = ((request.getParameter("page") == null)? "2" : request.getParameter("page"));
				
				url = "/Sub02/notice_board.jsp?page=" + pageNum;
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
				request.setAttribute("idx", "noticeBoard");
			}
			break;
		case 2: // 학생회소식
			if (type == null || type.equals("search")) {
				procNorArticleList(request, Board.STUDENT.getValue());
				url = "/Sub02/student_board.jsp";
			} else {
				if(type.equals("write")){
					HttpSession session = request.getSession();
					HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");
					
					if(hs == null)
						return "/common/jsp/accessFail.jsp";
					
					String codeName = hs.get("codeName");
					
					if(!( codeName.equals("관리자") || codeName.equals("교수") || codeName.equals("학생회") ))
						return "/common/jsp/accessFail.jsp";
				}

				url = (new SelectController()).selectNorArticle(request);
				request.setAttribute("idx", "studentBoard");
			}
			break;
		case 3: // 사진첩
			if (type == null || type.equals("search")) {
				procPhotoArticleList(request, Board.PHOTO.getValue());		
				url = "/Sub02/photo_album.jsp";
			} else {
				if(type.equals("write")){
					HttpSession session = request.getSession();
					HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");
					
					if(hs == null)
						return "/common/jsp/accessFail.jsp";
				}
				
				url = (new SelectController()).selectNorArticle(request);
				request.setAttribute("idx", "photoAlbum");
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
			
		//	System.out.println(searchText);
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
	
	public void procPhotoArticleList(HttpServletRequest request, int boardId){
		ArticleBO articleBo = new ArticleBO();
		List<PhotoArticle> articleList = null;
		
		String searchType = request.getParameter("searchType");
		
		if(searchType == null){
			articleList = articleBo
				.getPhotoArticleList(boardId);
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
			
			articleList = articleBo.getSearchPhotoArticle(parameter);
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
