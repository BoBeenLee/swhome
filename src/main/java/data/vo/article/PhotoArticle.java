package data.vo.article;

public class PhotoArticle extends NorArticle{
	private String fileUrl;
	private String fileType;
	private String fileName;
	
	public PhotoArticle() {
	}
	public PhotoArticle(String fileUrl, String fileType, String fileName) {
		this.fileUrl = fileUrl;
		this.fileType = fileType;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
