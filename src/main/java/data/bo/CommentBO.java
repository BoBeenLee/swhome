package data.bo;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import data.dao.CommentDAO;

import data.vo.check.Check;
import data.vo.comment.Comment;

public class CommentBO {
	// List
	public List<Comment> getCommentList(String articleId) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		CommentDAO dao = session.getMapper(CommentDAO.class);
		List<Comment> CommentVO = null;
		
		try {
			CommentVO = dao.commentList(articleId);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return CommentVO;
	}

	// Insert
	public int getInsertComment(Comment comment){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		CommentDAO dao = session.getMapper(CommentDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.insertComment(comment, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
	
	// Delete
	public int getDeleteComment(int commentId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		CommentDAO dao = session.getMapper(CommentDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.deleteComment(commentId, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
	
	// Update
	public int getUpdateComment(int commentId, String commentContent){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		CommentDAO dao = session.getMapper(CommentDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.updateComment(commentId, commentContent, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
}
