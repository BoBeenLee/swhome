package data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import data.vo.Test;
import data.vo.check.Check;
import data.vo.message.ReceiveMessage;
import data.vo.message.SendMessage;

public interface MessageDAO {
	// Message LIST
	String RECEIVE_LIST = "select receivemessageid, receivedate, receivecontent, messageyn, senderid, messageboxid, sendername from receivemessageview where messageboxid in " +
			"( select messageboxid from messageboxtable where userid = #{userId} ) order by receivedate desc";
	@Select(RECEIVE_LIST)
	List<ReceiveMessage> receiveList(@Param("userId") String userId);
	
	String SEND_LIST = "select sendmessageid, senddate, sendcontent, receiverid, messageboxid, receivername from sendmessageview where messageboxid in " +
			"( select messageboxid from messageboxtable where userid = #{userId} ) order by senddate desc";
	@Select(SEND_LIST)
	List<SendMessage> sendList(@Param("userId") String userId );
	
	// Insert
	String SEND_MSG = "{ CALL send_msg(#{userId}, #{receiverId}, #{content}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	@Select(value = SEND_MSG)
	@Options(statementType=StatementType.CALLABLE)
	public Object sendMsg(@Param("receiverId") String receiverId, @Param("userId") String userId, @Param("content") String content, @Param("Check") Check checkNum);
	
	// Delete
	String DELETE_RECEIVE_MSG = "{ CALL delete_receive_message(#{receiveMessageId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	@Select(value = DELETE_RECEIVE_MSG)
	@Options(statementType=StatementType.CALLABLE)
	public Object deleteReceiveMsg(@Param("receiveMessageId") int receiveMessageId, @Param("Check") Check checkNum);
	
	String DELETE_SEND_MSG = "{ CALL delete_send_message(#{sendMessageId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	@Select(value = DELETE_SEND_MSG)
	@Options(statementType=StatementType.CALLABLE)
	public Object deleteSendMsg(@Param("sendMessageId") int sendMessageId, @Param("Check") Check checkNum);
	
	// Select
	String SELECT_RECEIVE_MSG = "select receivemessageid, receivedate, receivecontent, messageyn, senderid, messageboxid, sendername from receivemessageview" +
			" where receivemessageid = #{receiveMessageId}";
	@Select(value = SELECT_RECEIVE_MSG)
	public ReceiveMessage selectReceiveMsg(@Param("receiveMessageId") int receiveMessageId);
	
	String SELECT_SEND_MSG = "select sendmessageid, senddate, sendcontent, receiverid, messageboxid, receivername from sendmessageview " +
			"where sendmessageid = #{sendMessageId}";
	@Select(value = SELECT_SEND_MSG)
	public SendMessage selectSendMsg(@Param("sendMessageId") int sendMessageId);
	
	// message check YN
	String MSG_CHECK = "{ CALL message_checkyn(#{userId}, #{receiveMessageId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	@Select(value = MSG_CHECK)
	@Options(statementType=StatementType.CALLABLE)
	public Object msgCheckYn(@Param("userId") String userId, @Param("receiveMessageId") int receiveMessageId, @Param("Check") Check checkNum);
	
	// Test
	@Select("select receiveContent from receivemessageview")
	Test test(); 
}