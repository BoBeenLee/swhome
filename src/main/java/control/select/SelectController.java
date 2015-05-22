package control.select;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import data.bo.ArticleBO;
import data.bo.CommentBO;
import data.bo.FileBO;
import data.bo.MessageBO;
import data.vo.article.NorArticle;
import data.vo.article.PartArticle;
import data.vo.comment.Comment;
import data.vo.file.FileInfo;
import data.vo.message.ReceiveMessage;
import data.vo.message.SendMessage;

public class SelectController {
	
	// PartArticle
	public String selectPartArticle(HttpServletRequest request) {
		String articleType = request.getParameter("type");
		String url = null;

		if (articleType.equals("read")) {
			String articleId = (String) request.getParameter("articleId");
			String password = request.getParameter("password");
			ArticleBO articleBo = new ArticleBO();

			// 조회하기
			PartArticle partArticle = articleBo.getSelectPartArticle(articleId);
				
			//System.out.println(password + " ++ " + partArticle.getPartPassword());
			if(partArticle.getPartPassword() != null && !partArticle.getPartPassword().equals(password)){
				return null;
			}
			
			// 조회수 증가 시키기
			articleBo.getAddArticleHit(articleId, "part");
			
			// 날짜 관련 조회 설정
			if (partArticle.getNoticeDate() != null) {
				procNoticeDate(request, partArticle.getNoticeDate());
			}
			// 파일 관련 조회 설정
			procFile(request, partArticle.getPartArticleId());
			// 댓글 관련 조회 설정
			procComment(request, partArticle.getPartArticleId());
			
			// 글 조회 등록
			request.setAttribute("partArticle", partArticle);
			
			url = "/common/jsp/article/partArticleRead.jsp";
		} else if (articleType.equals("write")) {
			String articleId = request.getParameter("targetArticleId");

			if(articleId != null)
				request.setAttribute("targetArticleId", articleId); 
			
			url = "/common/jsp/article/partArticleWrite.jsp";
		} else if (articleType.equals("update")) {
			String articleId = (String) request.getParameter("articleId");
			ArticleBO articleBo = new ArticleBO();
			
			// 우선 로그인은 필수로 해야지
			HttpSession session = request.getSession();
			HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");
			
			
			if(hs == null)
				return "/common/jsp/accessFail.jsp";
			
			// 조회하기
			PartArticle partArticle = articleBo.getSelectPartArticle(articleId);
			
			// 니가 쓴 글은지 신원확인 좀 해야겠다.
			String writerId = hs.get("userId");
			
			if(!partArticle.getPartArticleWriterId().equals(writerId))
				return "/common/jsp/accessFail.jsp";
			
			// 날짜 관련 조회 설정
			if (partArticle.getNoticeDate() != null) {
				procNoticeDate(request, partArticle.getNoticeDate());
			}
			
			// 파일 관련 조회 설정
			procFile(request, partArticle.getPartArticleId());
			// System.out.println(norArticle.getNorArticleContent());
			request.setAttribute("partArticle", partArticle);

			url = "/common/jsp/article/partArticleModify.jsp";
		}

		return url;
	}
	
	// NorArticle 
	public String selectNorArticle(HttpServletRequest request) {
		String articleType = request.getParameter("type");
		String url = null;
		
		if (articleType.equals("read")) {
			String articleId = (String) request.getParameter("articleId");
			ArticleBO articleBo = new ArticleBO();

			// 조회하기
			NorArticle norArticle = articleBo.getSelectNorArticle(articleId);
				
			// 조회수 증가 시키기
			articleBo.getAddArticleHit(articleId, "nor");
			
			// 날짜 관련 조회 설정
			if (norArticle.getNoticeDate() != null) {
				procNoticeDate(request, norArticle.getNoticeDate());
			}
			// 파일 관련 조회 설정
			procFile(request, norArticle.getNorArticleId());
			// 댓글 관련 조회 설정
			procComment(request, norArticle.getNorArticleId());
			
			// 글 조회 등록
			request.setAttribute("norArticle", norArticle);
			
			url = "/common/jsp/article/norArticleRead.jsp";
		} else if (articleType.equals("write")) {
			// 각각의 게시판에 권한이 있기 때문에 여기에 권한처리를 해주지 못하였다.
			url = "/common/jsp/article/norArticleWrite.jsp";
		} else if (articleType.equals("update")) {
			String articleId = (String) request.getParameter("articleId");
			ArticleBO articleBo = new ArticleBO();

			// 우선 로그인은 필수로 해야지
			HttpSession session = request.getSession();
			HashMap<String, String> hs = (HashMap<String, String>) session.getAttribute("userInfo");
			
			if(hs == null)
				return "/common/jsp/accessFail.jsp";
			
			// 조회하기
			NorArticle norArticle = articleBo.getSelectNorArticle(articleId);
			
			// 니가 쓴 글은지 신원확인 좀 해야겠다.
			String writerId = hs.get("userId");
			
			if(!norArticle.getNorArticleWriterId().equals(writerId))
				return "/common/jsp/accessFail.jsp";
			
			// 날짜 관련 조회 설정
			if (norArticle.getNoticeDate() != null) {
				procNoticeDate(request, norArticle.getNoticeDate());
			}
			
			// 파일 관련 조회 설정
			procFile(request, norArticle.getNorArticleId());
			// System.out.println(norArticle.getNorArticleContent());
			request.setAttribute("norArticle", norArticle);

			url = "/common/jsp/article/norArticleModify.jsp";
		}

		return url;
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
	
	private void procComment(HttpServletRequest request, String articleId){
		
		CommentBO commentBo = new CommentBO();
		
		List<Comment> commentList = commentBo.getCommentList(articleId);
		
		request.setAttribute("commentList", commentList);
	}
	
	private void procFile(HttpServletRequest request, String articleId){
		/* 파일 리스트를 보여주기 위한 */
		
		FileBO fileBo = new FileBO();
		
		List<FileInfo> fileList = fileBo.getFileList(articleId);
		
		if(fileList.size() > 0){
			request.setAttribute("fileList", fileList);
		}
	}
	
	// Message
	public String selectMsgReceive(HttpServletRequest request) {
		String articleType = request.getParameter("type");
		MessageBO messageBo = new MessageBO();
		String url = null;

		// System.out.println(articleType);
		if (articleType.equals("read")) {
			int msgId = Integer.parseInt(request.getParameter("articleId"));
			HttpSession session = request.getSession();
			HashMap<String, String> hs = (HashMap<String, String>) session
					.getAttribute("userInfo");
			String userId = hs.get("userId");

			// System.out.println(msgId);
			ReceiveMessage receiveMessage = messageBo.getSelectReceiveMsg(
					msgId, userId);
			// System.out.println(receiveMessage.getMessageBoxId());
			request.setAttribute("msg", receiveMessage);

			url = "/common/jsp/message/receiveMsgRead.jsp";
		} else if (articleType.equals("write")) {
			request.setAttribute("receiverId", request.getParameter("receiver"));
			url = "/common/jsp/message/msgWrite.jsp";
		}
		return url;
	}

	public String selectMsgSend(HttpServletRequest request) {
		String articleType = request.getParameter("type");
		MessageBO messageBo = new MessageBO();
		String url = null;

		if (articleType.equals("read")) {
			int msgId = Integer.parseInt(request.getParameter("articleId"));
			SendMessage sendMessage = messageBo.getSelectSendMsg(msgId);

			request.setAttribute("msg", sendMessage);

			url = "/common/jsp/message/sendMsgRead.jsp";
		}
		return url;
	}
}
