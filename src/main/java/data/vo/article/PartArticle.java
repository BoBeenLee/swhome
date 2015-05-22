package data.vo.article;

import java.sql.Date;
import java.sql.Timestamp;

public class PartArticle {
	private String partArticleId;
	private int boardId;
	private String partArticleTitle;
	private String partArticleContent;
	private String partArticleWriterId;
	private Timestamp partArticleDate;
	private String noticeYn;
	private int hit;
	private int commentCount;
	private int recommend;
	private String partPassword;
	private int distance;
	private int step;
	private String codeName;
	private String userName;
	private Date noticeDate;
	private int processId;
	private String parentArticleId;
	
	public PartArticle() {
		super();
	}
	public PartArticle(int boardId, String partArticleTitle,
			String partArticleContent, String partArticleWriterId,
			Timestamp partArticleDate, String noticeYn, int hit, int commentCount,
			int recommend, String partPassword, int distance, int step,
			String codeName, String userName, Date noticeDate, int processId,
			String parentArticleId) {
		super();
		this.boardId = boardId;
		this.partArticleTitle = partArticleTitle;
		this.partArticleContent = partArticleContent;
		this.partArticleWriterId = partArticleWriterId;
		this.partArticleDate = partArticleDate;
		this.noticeYn = noticeYn;
		this.hit = hit;
		this.commentCount = commentCount;
		this.recommend = recommend;
		this.partPassword = partPassword;
		this.distance = distance;
		this.step = step;
		this.codeName = codeName;
		this.userName = userName;
		this.noticeDate = noticeDate;
		this.processId = processId;
		this.parentArticleId = parentArticleId;
	}
	
	public String getParentArticleId() {
		return parentArticleId;
	}
	public void setParentArticleId(String parentArticleId) {
		this.parentArticleId = parentArticleId;
	}
	public int getProcessId() {
		return processId;
	}
	public void setProcessId(int processId) {
		this.processId = processId;
	}
	public Date getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}
	public String getPartArticleId() {
		return partArticleId;
	}
	public void setPartArticleId(String partArticleId) {
		this.partArticleId = partArticleId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getPartArticleTitle() {
		return partArticleTitle;
	}
	public void setPartArticleTitle(String partArticleTitle) {
		this.partArticleTitle = partArticleTitle;
	}
	public String getPartArticleContent() {
		return partArticleContent;
	}
	public void setPartArticleContent(String partArticleContent) {
		this.partArticleContent = partArticleContent;
	}
	public String getPartArticleWriterId() {
		return partArticleWriterId;
	}
	public void setPartArticleWriterId(String partArticleWriterId) {
		this.partArticleWriterId = partArticleWriterId;
	}
	public Timestamp getPartArticleDate() {
		return partArticleDate;
	}
	public void setPartArticleDate(Timestamp partArticleDate) {
		this.partArticleDate = partArticleDate;
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
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public String getPartPassword() {
		return partPassword;
	}
	public void setPartPassword(String partPassword) {
		this.partPassword = partPassword;
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
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
