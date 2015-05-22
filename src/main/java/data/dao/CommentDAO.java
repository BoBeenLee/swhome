package data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import data.vo.check.Check;
import data.vo.comment.Comment;

public interface CommentDAO {
	// List 
	String COMMENT_LIST = "select * from commenttable " +
			"where articleid = #{articleId} order by commentdate asc";
	
	@Select(COMMENT_LIST)
	List<Comment> commentList(@Param("articleId") String articleId);
	
	// Insert 
	String INSERT_COMMENT = "{ CALL insert_comment( #{Comment.articleId}, #{Comment.commentWriterId}, #{Comment.commentContent}, #{Comment.userName}, " +
			"#{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	@Select(value = INSERT_COMMENT)
	@Options(statementType=StatementType.CALLABLE)
	Object insertComment(@Param("Comment") Comment comment, @Param("Check") Check checkNum);
	
	// Delete
	String DELETE_COMMENT = "{ CALL delete_comment( #{commentId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	
	@Select(value = DELETE_COMMENT)
	@Options(statementType=StatementType.CALLABLE)
	Object deleteComment(@Param("commentId") int commentId, @Param("Check") Check checkNum);
	
	// Update
	String UPDATE_COMMENT = "{ CALL update_comment( #{commendId}, #{commentContent}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";

	@Select(value = UPDATE_COMMENT)
	@Options(statementType=StatementType.CALLABLE)
	Object updateComment(@Param("commendId") int commentId, @Param("commentContent") String commentContent, @Param("Check") Check checkNum);
}

