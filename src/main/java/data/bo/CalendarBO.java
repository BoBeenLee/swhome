package data.bo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import data.dao.CalendarDAO;
import data.dao.CommentDAO;
import data.vo.SwCalendar;
import data.vo.check.Check;
import data.vo.comment.Comment;

public class CalendarBO {
	// Select
	public List<SwCalendar> getSwCalendarList(HashMap<String, String> hashmap){
		List<SwCalendar> list = new ArrayList<SwCalendar>();
		
		SqlSession session = ConnectionFactory.getInstance().openSession();
		CalendarDAO dao = session.getMapper(CalendarDAO.class);
		try{
			list = dao.selectCalendarList(hashmap);
		} catch(Exception e){
			
		} finally{
			session.close();
		}
		return list;
	}
	
	// Insert
	public int getInsertCalendar(SwCalendar swCalendar){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		CalendarDAO dao = session.getMapper(CalendarDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.insertCalendar(swCalendar, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
	
	// Delete
	public int getDeleteCalendar(int calendarId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		CalendarDAO dao = session.getMapper(CalendarDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.deleteCalendar(calendarId, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
}
