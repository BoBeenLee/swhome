package data.vo.user;

public class User {
	private String userId;
	private String userName;
	private String userPw;
	private String userTel;
	private String userEmail;
	private String question;
	private String answer;
	private int codeId;
	private String groupPw;
	private int newMessage;
	
	public User(){
		
	}
	public User(User user) {
		// TODO Auto-generated constructor stub
		this.userId = user.userId;
		this.userName = user.userName;
		this.userPw = user.userPw;
		this.userTel = user.userTel;
		this.userEmail = user.userEmail;
		this.question = user.question;
		this.answer = user.answer;
		this.codeId = user.codeId;
		this.groupPw = user.groupPw;
		this.newMessage = user.newMessage;
	}
	public User(String userId, String userName, String userPw, String userTel,
			String userEmail, String question, String answer, String groupPw) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPw = userPw;
		this.userTel = userTel;
		this.userEmail = userEmail;
		this.question = question;
		this.answer = answer;
		this.groupPw = groupPw;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getCodeId() {
		return codeId;
	}
	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}
	public String getGroupPw() {
		return groupPw;
	}
	public void setGroupPw(String groupPw) {
		this.groupPw = groupPw;
	}
	public int getNewMessage() {
		return newMessage;
	}
	public void setNewMessage(int newMessage) {
		this.newMessage = newMessage;
	}
}
