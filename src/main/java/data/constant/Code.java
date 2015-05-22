package data.constant;

public enum Code {
	USER(1), GRADUATE(2), STUDENT(3), STUDENTUNION(4), EMPLOYEE(5), PROFESSOR(6), ADMINISTRATOR(7), PROCESSSTATE(8), 
	UNCONFIRM(9), PROCESSING(10), PROCESSSUCCESS(11), PROCESSFAIL(12), BOARDTYPE(13), NORBOARD(14), PARTBOARD(15),
	RANK(20), RANKFIRST(21), RANKSECOND(22), RANKTHIRD1(23), RANKTHIRD2(24), RANKFOURTH(25);
	
	// 원소 설명
	private int value;
	
	Code(int value){
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
}
