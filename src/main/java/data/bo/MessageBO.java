package data.bo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import data.dao.MessageDAO;
import data.vo.Test;
import data.vo.check.Check;
import data.vo.message.ReceiveMessage;
import data.vo.message.SendMessage;

public class MessageBO {
	// List
	public List<ReceiveMessage> getReceiveList(String userId) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		MessageDAO dao = session.getMapper(MessageDAO.class);
		List<ReceiveMessage> receiveVO = null;

		try {
			receiveVO = dao.receiveList(userId);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return receiveVO;
	}

	public List<SendMessage> getSendList(String userId) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		MessageDAO dao = session.getMapper(MessageDAO.class);
		List<SendMessage> sendVO = null;

		try {
			sendVO = dao.sendList(userId);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return sendVO;
	}

	// Insert
	public int getSendMsg(String receiverId, String userId, String content) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		MessageDAO dao = session.getMapper(MessageDAO.class);
		Check checkNum = new Check();

		try {
			dao.sendMsg(receiverId, userId, content, checkNum);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return checkNum.getCheckNum();
	}

	// Delete
	public int getDeleteReceiveMsg(int receiveMsgId) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		MessageDAO dao = session.getMapper(MessageDAO.class);
		Check checkNum = new Check();

		try {
			dao.deleteReceiveMsg(receiveMsgId, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return checkNum.getCheckNum();
	}
	public int getDeleteSendMsg(int sendMsgId) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		MessageDAO dao = session.getMapper(MessageDAO.class);
		Check checkNum = new Check();

		try {
			dao.deleteSendMsg(sendMsgId, checkNum);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	// Select
	public ReceiveMessage getSelectReceiveMsg(int receiveMsgId, String userId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		MessageDAO dao = session.getMapper(MessageDAO.class);
		Check checkNum = new Check();
		ReceiveMessage receiveMessage = null;
		
		try {
			receiveMessage = dao.selectReceiveMsg(receiveMsgId);
			dao.msgCheckYn(userId, receiveMsgId, checkNum);
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		if(checkNum.getCheckNum() != 0)
			return null;
		return receiveMessage;
	}
	
	public SendMessage getSelectSendMsg(int sendMsgId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		MessageDAO dao = session.getMapper(MessageDAO.class);
		Check checkNum = new Check();
		SendMessage sendMessage = null;
		
		try {
			sendMessage = dao.selectSendMsg(sendMsgId);
			
		} catch(Exception e){
			System.out.println(e);
		} finally {
			session.close();
		}
		return sendMessage;
	}
	
	// Test
	public void getTest() {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		MessageDAO dao = session.getMapper(MessageDAO.class);
		Test sendVO = null;

		try {
			sendVO = dao.test();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		System.out.println(sendVO.getReceiveContent());
	}

}