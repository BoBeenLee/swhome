package data.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import data.vo.SwCalendar;
import data.vo.check.Check;
import data.vo.comment.Comment;

public interface CalendarDAO {
	// Select
	
	String SELECTED_CALENDAR_LIST = "select calendarid, startdate, lastdate, title" +
			" from calendar" + 
			" where (lastdate between to_date('${sDate}', 'yyyy-mm-dd') and to_date('3000-01-01', 'yyyy-mm-dd')) and (startDate between to_date('1990-01-01', 'yyyy-mm-dd') and to_date('${lDate}', 'yyyy-mm-dd'))";
	
	@Select(SELECTED_CALENDAR_LIST)
	List<SwCalendar> selectCalendarList(HashMap<String, String> hashmap);
	
	// Insert 
	String INSERT_CALENDAR = "{ CALL insert_calendar( #{SwCalendar.title}, #{SwCalendar.startDate}, #{SwCalendar.lastDate}, " +
			"#{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	@Select(value = INSERT_CALENDAR)
	@Options(statementType=StatementType.CALLABLE)
	Object insertCalendar(@Param("SwCalendar") SwCalendar swCalendar, @Param("Check") Check checkNum);
	
	// Delete
	String DELETE_CALENDAR = "{ CALL delete_calendar( #{calendarId}, " +
			"#{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	@Select(value = DELETE_CALENDAR )
	@Options(statementType=StatementType.CALLABLE)
	Object deleteCalendar(@Param("calendarId") int calendarId, @Param("Check") Check checkNum);
	
	
}