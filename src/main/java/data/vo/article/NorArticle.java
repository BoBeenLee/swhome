package data.vo.article;

import java.sql.Date;
import java.sql.Timestamp;

public class NorArticle {
	private String norArticleId;
	private int boardId;
	private String norArticleTitle;
	private String norArticleContent;
	private String norArticleWriterId;
	private Timestamp norArticleDate;
	private String noticeYn;
	private int hit;
	private int commentCount;
	private String userName;
	private Date noticeDate;
	
	public Date getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}
	public NorArticle(){
		this.norArticleId = "";
	}
	public NorArticle(int boardId, String norArticleTitle,
			String norArticleContent, String norArticleWriterId, String noticeYn, Date noticeDate) {
		super();
		this.norArticleId = "";
		this.boardId = boardId;
		this.norArticleTitle = norArticleTitle;
		this.norArticleContent = norArticleContent;
		this.norArticleWriterId = norArticleWriterId;
		this.noticeYn = noticeYn;
		this.noticeDate = noticeDate;
	}

	public String getNorArticleId() {
		return norArticleId;
	}
	public void setNorArticleId(String norArticleId) {
		this.norArticleId = norArticleId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getNorArticleTitle() {
		return norArticleTitle;
	}
	public void setNorArticleTitle(String norArticleTitle) {
		this.norArticleTitle = norArticleTitle;
	}
	public String getNorArticleContent() {
		return norArticleContent;
	}
	public void setNorArticleContent(String norArticleContent) {
		this.norArticleContent = norArticleContent;
	}
	public String getNorArticleWriterId() {
		return norArticleWriterId;
	}
	public void setNorArticleWriterId(String norArticleWriterId) {
		this.norArticleWriterId = norArticleWriterId;
	}
	public Timestamp getNorArticleDate() {
		return norArticleDate;
	}
	public void setNorArticleDate(Timestamp norArticleDate) {
		this.norArticleDate = norArticleDate;
	}
	public String getNoticeYn() {
		return noticeYn;
	}
	public void setNoticeYn(String noticeYn) {
		this.noticeYn = noticeYn;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
