package data.vo.check;

public class Check {
	private int checkNum;
	private String checkStr;
	
	public Check(){
		this.checkNum = 1;
		this.checkStr = "error";
	}
	
	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	public String getCheckStr() {
		return checkStr;
	}

	public void setCheckStr(String checkStr) {
		this.checkStr = checkStr;
	}
}
