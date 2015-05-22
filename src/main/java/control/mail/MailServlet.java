package control.mail;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import data.bo.UserBO;

/**
 * Servlet implementation class MailServlet
 */
@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String emailHost = "smtp.gmail.com";
	private static final String emailId = "cultisttp@gmail.com";
	private static final String emailPw = "ad1053272";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String idx = request.getParameter("idx");

		if (idx.equals("pwd")) {
			boolean checkBool = sendMail(request);
			String resultMsg = (checkBool) ? "Send Mail : Success"
					: "Send Mail : Fail";

			response.sendRedirect("index.jsp?resMsg=" + resultMsg);
		}
	}

	private boolean sendMail(HttpServletRequest request) {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", emailHost);
		props.put("mail.smtp.auth", true);
		
		EmailAuthenticator authenticator = new EmailAuthenticator(emailId, emailPw);

		Session sess = Session.getDefaultInstance(props, authenticator);
		Message msg = new MimeMessage(sess);
		
		// 보낼때 수신자 송신자 관련 및 여러 내용 처리
		UserBO userBo = new UserBO();

		String userId = request.getParameter("id");
		String receiver = userBo.getSearchPassword(userId);
		String sender = "cultist_tp@naver.com";

		if (receiver.equals(""))
			return false;
		
		String newPassword = Integer.toString((int) (Math.random() * 1000000));
		int checkNum = userBo.getUpdatePassword(userId, newPassword);

		if (checkNum == 1)
			return false;

		// System.out.println(newPassword);
		try {
			msg.setHeader("Content-Type", "text/html;charset=utf-8");
			msg.setFrom(new InternetAddress(sender));
			InternetAddress address = new InternetAddress(receiver);

			msg.addRecipient(Message.RecipientType.TO, address);
			msg.setSubject("Skhu " + userId + " : Password ");
			msg.setSentDate(new Date());
			msg.setContent(
					"Hello World! You will be looking for Password. I find that. Your Password is "
							+ newPassword + ".", "text/html;charset=utf-8");

			Transport.send(msg);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		} finally {

		}
		return true;
	}
	
	class EmailAuthenticator extends Authenticator{
		private String id;
		private String pw;
		
		public EmailAuthenticator(String id, String pw) {
			this.id = id;
			this.pw = pw;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			// TODO Auto-generated method stub
			return new PasswordAuthentication(id, pw);
		}
	}
}
