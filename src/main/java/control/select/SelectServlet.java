package control.select;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.bo.ArticleBO;
import data.bo.FileBO;
import data.vo.article.NorArticle;
import data.vo.file.FileInfo;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/SelectServlet")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectServlet() {
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
		String idx = request.getParameter("idx");
		String url = "index.jsp";

		
		
		if (idx.equals("manage")) {
			String type = request.getParameter("type");
			
			if (type.equals("user")) {

			} else if (type.equals("part")) {
				url = selectModifyNorArticle(request);
			} else if (type.equals("nor")) {

			} else if (type.equals("calendar")) {

			}
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private String selectModifyNorArticle(HttpServletRequest request){
		String articleId = request.getParameter("articleId");
		ArticleBO articleBo = new ArticleBO();

		// 조회하기
		NorArticle norArticle = articleBo.getSelectNorArticle(articleId);
		
		// 날짜 관련 조회 설정
		if (norArticle.getNoticeDate() != null) {
			procNoticeDate(request, norArticle.getNoticeDate());
		}
		
		// 파일 관련 조회 설정
		procFile(request, norArticle.getNorArticleId());
		// System.out.println(norArticle.getNorArticleContent());
		request.setAttribute("norArticle", norArticle);
		request.setAttribute("popup", "nor");
		
		return "/common/jsp/article/norArticleModify.jsp";
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	private void procNoticeDate(HttpServletRequest request, Date noticeDate){
		/* 날짜 출력하기 위한 */
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(noticeDate.getTime());

		// System.out.println(norArticle.getNorArticleContent());
		request.setAttribute("noticeYear", calendar.get(Calendar.YEAR));
		request.setAttribute("noticeMonth",
				calendar.get(Calendar.MONTH));
		request.setAttribute("noticeDay", calendar.get(Calendar.DATE));
	}
	
	private void procFile(HttpServletRequest request, String articleId){
		/* 파일 리스트를 보여주기 위한 */
		
		FileBO fileBo = new FileBO();
		
		List<FileInfo> fileList = fileBo.getFileList(articleId);
		
		if(fileList.size() > 0){
			request.setAttribute("fileList", fileList);
		}
	}
}
