package data.bo;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import data.dao.UserDAO;
import data.vo.check.Check;
import data.vo.user.EmployeeUser;
import data.vo.user.ProfessorUser;
import data.vo.user.StudentUnion;
import data.vo.user.StudentUser;
import data.vo.user.User;

public class UserBO {
	// List
	public List<StudentUser> getStudentUserList(){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		List<StudentUser> articleVO = null;
		
		try{
			articleVO = dao.studentUserList();
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return articleVO;
	}
	
	public List<ProfessorUser> getProfessorUserList(){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		List<ProfessorUser> articleVO = null;
		
		try{
			articleVO = dao.professorUserList();
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return articleVO;
	}
	
	public List<EmployeeUser> getEmployeeUserList(){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		List<EmployeeUser> articleVO = null;
		
		try{
			articleVO = dao.employeeUserList();
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return articleVO;
	}
	
	// Insert 
	public int getInsertStudentUser(StudentUser su){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.insertStudentUser(su, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	public int getInsertProfessorUser(ProfessorUser pu){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.insertProfessorUser(pu, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	public int getInsertEmployeeUser(EmployeeUser eu){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.insertEmployeeUser(eu, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	// Select
	public User getUser(String userId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		User userVO = null;
		
		try{
			userVO = dao.selectUser(userId);
			
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return userVO;
	}
	
	public List<StudentUser> getStudentUser(HashMap<String, String> hashmap){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		List<StudentUser> studentUserVO = null;
		
		try{
			studentUserVO = dao.selectStudentUser(hashmap);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return studentUserVO;
	}
	
	public List<ProfessorUser> getProfessorUser(HashMap<String, String> hashmap){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		List<ProfessorUser> proefessorUserVO = null;
		
		try{
			proefessorUserVO = dao.selectProfessorUser(hashmap);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return proefessorUserVO;
	}
	
	public List<EmployeeUser> getEmployeeUser(HashMap<String, String> hashmap){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		List<EmployeeUser> employeeUserVO = null;
		
		try{
			employeeUserVO = dao.selectEmployeeUser(hashmap);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return employeeUserVO;
	}
	
	// Update 
	public int getUpdateStudentUser(StudentUser su){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.updateStudentUser(su, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	public int getUpdateProfessorUser(ProfessorUser pu){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.updateProfessorUser(pu, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	public int getUpdateEmployeeUser(EmployeeUser eu){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.updateEmployeeUser(eu, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	// Update Password
	public int getUpdatePassword(String userId, String userPw){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.updatePassword(userId, userPw, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	// Delete
	public int getDeleteUser(String userId, String userPw){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.deleteUser(userId, userPw, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	// LoginCheck
	public int getLoginCheck(String userId, String userPw){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.loginCheck(userId, userPw, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	// searchPassword
	public String getSearchPassword(String userId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		Check checkStr = new Check();
		
		try{
			dao.searchPassword(userId, checkStr);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkStr.getCheckStr();
	}
	
	// grantStudentUnion
	public int getGrantStudentUnion(String userId, Date sDate, Date lDate){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.grantStudentUnion(userId, sDate, lDate, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	// selectStudentUnion
	public StudentUnion getSelectStudentUnion(String userId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		StudentUnion userVO = null;
		
		try{
			userVO = dao.selectStudentUnion(userId);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		
		return userVO;
	}

	public int getRevokeStudentUnion(String userId) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		UserDAO dao = session.getMapper(UserDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.revokeStudentUnion(userId, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
}
