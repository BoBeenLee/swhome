package data.vo.message;

import java.sql.Date;

public class SendMessage {
	private int sendMessageId;
	private Date sendDate;
	private String sendContent;
	private String receiverId;
	private String receiverName;
	private int messageBoxId;
	
	public int getSendMessageId() {
		return sendMessageId;
	}
	public void setSendMessageId(int sendMessageId) {
		this.sendMessageId = sendMessageId;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getSendContent() {
		return sendContent;
	}
	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public int getMessageBoxId() {
		return messageBoxId;
	}
	public void setMessageBoxId(int messageBoxId) {
		this.messageBoxId = messageBoxId;
	}
}
