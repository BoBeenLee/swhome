package data.constant;

public enum Board {
	NOTICE(1), STUDENT(2), PHOTO(3), JOB(4), FREE(5), SUGGESTION(6), QA(7), DATA(8), HONOR(9), ASSO(10);
	
	// 원소 설명
	private int value;
	
	Board(int value){
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
}
