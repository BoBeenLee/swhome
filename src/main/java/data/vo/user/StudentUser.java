package data.vo.user;

import java.sql.Date;

public class StudentUser extends User{
	private int lotteryPoint;
	private int userGrade;
	private String department;
	private Date finishYear;
	
	public StudentUser(){
		
	}
	public StudentUser(User user, int userGrade, String department, Date finishYear){
		super(user);
		this.userGrade = userGrade;
		this.department = department;
		this.finishYear = finishYear;
	}
	
	public int getLotteryPoint() {
		return lotteryPoint;
	}
	public void setLotteryPoint(int lotteryPoint) {
		this.lotteryPoint = lotteryPoint;
	}
	public int getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(int userGrade) {
		this.userGrade = userGrade;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Date getFinishYear() {
		return finishYear;
	}
	public void setFinishYear(Date finishYear) {
		this.finishYear = finishYear;
	}
}
