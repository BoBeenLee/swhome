package data.vo.comment;

import java.sql.Date;
import java.sql.Timestamp;

public class Comment {
	private int commentId;
	private String articleId;
	private String commentWriterId;
	private String commentContent;
	private Timestamp commentDate;
	private int parentCommentId;
	private int distance;
	private int step;
	private String userName;
	
	public Comment() {
		super();
	}
	public Comment(String articleId, String commentWriterId,
			String commentContent, int parentCommentId, String userName) {
		super();
		this.articleId = articleId;
		this.commentWriterId = commentWriterId;
		this.commentContent = commentContent;
		this.parentCommentId = parentCommentId;
		this.userName = userName;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getCommentWriterId() {
		return commentWriterId;
	}
	public void setCommentWriterId(String commentWriterId) {
		this.commentWriterId = commentWriterId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Timestamp getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}
	public int getParentCommentId() {
		return parentCommentId;
	}
	public void setParentCommentId(int parentCommentId) {
		this.parentCommentId = parentCommentId;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
}
