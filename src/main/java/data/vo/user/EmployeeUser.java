package data.vo.user;

public class EmployeeUser extends User{
	private String officeLocation;
	private String extensionTel;
	
	public EmployeeUser(){
		
	}
	public EmployeeUser(User user, String officeLocation, String extensionTel){
		super(user);
		this.officeLocation = officeLocation;
		this.extensionTel = extensionTel;
	}
	
	public String getOfficeLocation() {
		return officeLocation;
	}
	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}
	public String getExtensionTel() {
		return extensionTel;
	}
	public void setExtensionTel(String extensionTel) {
		this.extensionTel = extensionTel;
	}
}
