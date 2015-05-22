package data.vo.user;

import java.sql.Date;

public class StudentUnion {
	private Date unionStartDate;
	private Date unionLastDate;
	
	public StudentUnion() {
	}

	public StudentUnion(Date unionStartDate, Date unionLastDate) {
		this.unionStartDate = unionStartDate;
		this.unionLastDate = unionLastDate;
	}
	
	public Date getUnionStartDate() {
		return unionStartDate;
	}
	public void setUnionStartDate(Date unionStartDate) {
		this.unionStartDate = unionStartDate;
	}
	public Date getUnionLastDate() {
		return unionLastDate;
	}
	public void setUnionLastDate(Date unionLastDate) {
		this.unionLastDate = unionLastDate;
	}
}
