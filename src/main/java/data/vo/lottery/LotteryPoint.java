package data.vo.lottery;

import java.sql.Date;

public class LotteryPoint {
	private int pointContentId;
	private Date pointDate;
	private String userId;
	private int addPoint;
	private int addCode;
	private String pointContent;
	
	public int getPointContentId() {
		return pointContentId;
	}
	public void setPointContentId(int pointContentId) {
		this.pointContentId = pointContentId;
	}
	public Date getPointDate() {
		return pointDate;
	}
	public void setPointDate(Date pointDate) {
		this.pointDate = pointDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getAddPoint() {
		return addPoint;
	}
	public void setAddPoint(int addPoint) {
		this.addPoint = addPoint;
	}
	public int getAddCode() {
		return addCode;
	}
	public void setAddCode(int addCode) {
		this.addCode = addCode;
	}
	public String getPointContent() {
		return pointContent;
	}
	public void setPointContent(String pointContent) {
		this.pointContent = pointContent;
	}
}
