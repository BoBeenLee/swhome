package data.vo.user;

public class ProfessorUser extends User{
	private String professorLocation;
	private String extensionTel;
	private String professorAbility;
	private String professorCareer;
	private String professorPosition;
	private String professorImg;
	
	public ProfessorUser(){
		
	}
	public ProfessorUser(User user, String professorLocation, String extensionTel, String professorAbility, String professorCareer, String professorPosition){
		super(user);
		this.professorLocation = professorLocation;
		this.extensionTel = extensionTel;
		this.professorAbility = professorAbility;
		this.professorCareer = professorCareer;
		this.professorPosition = professorPosition;
	}
	
	
	public String getProfessorImg() {
		return professorImg;
	}
	public void setProfessorImg(String professorImg) {
		this.professorImg = professorImg;
	}
	public String getProfessorPosition() {
		return professorPosition;
	}
	public void setProfessorPosition(String professorPosition) {
		this.professorPosition = professorPosition;
	}
	public String getProfessorAbility() {
		return professorAbility;
	}
	public void setProfessorAbility(String professorAbility) {
		this.professorAbility = professorAbility;
	}
	public String getProfessorCareer() {
		return professorCareer;
	}
	public void setProfessorCareer(String professorCareer) {
		this.professorCareer = professorCareer;
	}
	public String getProfessorLocation() {
		return professorLocation;
	}
	public void setProfessorLocation(String professorLocation) {
		this.professorLocation = professorLocation;
	}
	public String getExtensionTel() {
		return extensionTel;
	}
	public void setExtensionTel(String extensionTel) {
		this.extensionTel = extensionTel;
	}
}
