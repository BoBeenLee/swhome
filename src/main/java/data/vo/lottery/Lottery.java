package data.vo.lottery;

import java.sql.Date;

public class Lottery {
	private int lotteryId;
	private String payYn;
	private String lotteryContent;
	private String lotteryRank;
	private int win;
	private int rankId;
	private Date enterDate;
	private String enterUserId;
	private int enterId;
	private Date lotteryDate;
	
	public int getLotteryId() {
		return lotteryId;
	}
	public void setLotteryId(int lotteryId) {
		this.lotteryId = lotteryId;
	}
	public String getPayYn() {
		return payYn;
	}
	public void setPayYn(String payYn) {
		this.payYn = payYn;
	}
	public String getLotteryContent() {
		return lotteryContent;
	}
	public void setLotteryContent(String lotteryContent) {
		this.lotteryContent = lotteryContent;
	}
	public String getLotteryRank() {
		return lotteryRank;
	}
	public void setLotteryRank(String lotteryRank) {
		this.lotteryRank = lotteryRank;
	}
	public Date getLotteryDate() {
		return lotteryDate;
	}
	public void setLotteryDate(Date lotteryDate) {
		this.lotteryDate = lotteryDate;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getRankId() {
		return rankId;
	}
	public void setRankId(int rankId) {
		this.rankId = rankId;
	}
	public Date getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}
	public String getEnterUserId() {
		return enterUserId;
	}
	public void setEnterUserId(String enterUserId) {
		this.enterUserId = enterUserId;
	}
	public int getEnterId() {
		return enterId;
	}
	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}
}
