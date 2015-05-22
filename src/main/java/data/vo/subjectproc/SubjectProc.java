package data.vo.subjectproc;

public class SubjectProc {
	private int subjectProcId;
	private int semester;
	private String classification;
	private String subjectName;
	private int grade;
	private String preRequiredSubject;
	private String preRecommendedSubject;
	private String summary;
	private int credit;
	
	public int getSubjectProcId() {
		return subjectProcId;
	}
	public void setSubjectProcId(int subjectProcId) {
		this.subjectProcId = subjectProcId;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getPreRequiredSubject() {
		return preRequiredSubject;
	}
	public void setPreRequiredSubject(String preRequiredSubject) {
		this.preRequiredSubject = preRequiredSubject;
	}
	public String getPreRecommendedSubject() {
		return preRecommendedSubject;
	}
	public void setPreRecommendedSubject(String preRecommendedSubject) {
		this.preRecommendedSubject = preRecommendedSubject;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
}
