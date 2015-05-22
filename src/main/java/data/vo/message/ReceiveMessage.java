package data.vo.message;

import java.sql.Date;

public class ReceiveMessage {
	private int receiveMessageId;
	private Date receiveDate;
	private String receiveContent;
	private String messageYn;
	private String senderId;
	private String senderName;
	private int messageBoxId;
	
	public int getReceiveMessageId() {
		return receiveMessageId;
	}
	public void setReceiveMessageId(int receiveMessageId) {
		this.receiveMessageId = receiveMessageId;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getReceiveContent() {
		return receiveContent;
	}
	public void setReceiveContent(String receiveContent) {
		this.receiveContent = receiveContent;
	}
	public String getMessageYn() {
		return messageYn;
	}
	public void setMessageYn(String messageYn) {
		this.messageYn = messageYn;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public int getMessageBoxId() {
		return messageBoxId;
	}
	public void setMessageBoxId(int messageBoxId) {
		this.messageBoxId = messageBoxId;
	}
}
