package control.calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.bo.CalendarBO;
import data.vo.SwCalendar;

/**
 * Servlet implementation class calendarListView
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalendarServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// DB 연동
		CalendarBO calendarBO = new CalendarBO();
		List<SwCalendar> selectedList = null;
		HashMap<String, String> hashmap = new HashMap<String, String>();

		// 출력하는 부분
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		String cDate = request.getParameter("date");
		SimpleDateFormat format_yymmdd = new SimpleDateFormat("yyyy-MM-dd"); // 달력
																				// 형식
																				// 포맷
		SimpleDateFormat format_mmdd = new SimpleDateFormat("MM.dd");
		Calendar calendarDate = Calendar.getInstance();

		try {
			calendarDate.setTime(format_yymmdd.parse(cDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hashmap.put("sDate", format_yymmdd.format(calendarDate.getTime()));
		calendarDate.add(Calendar.DATE, 10);
		hashmap.put("lDate", format_yymmdd.format(calendarDate.getTime()));

		// System.out.println(hashmap.get("sDate") + " - " +
		// hashmap.get("lDate"));
		selectedList = calendarBO.getSwCalendarList(hashmap); // DAO로 부터 달력 리스트
																// 가져오기

		out.println("<div style='width:100%; margin-left: 0px;'> "
				+ hashmap.get("sDate") + " - " + hashmap.get("lDate")
				+ "</div>");
		if (selectedList != null) {

			for (int i = 0; i < selectedList.size(); i++) {
				SwCalendar sc = ((SwCalendar) selectedList.get(i));
				String sDate = format_mmdd.format(sc.getStartDate()), lDate = format_mmdd
						.format(sc.getLastDate());

				out.print("<div style='width:100%; margin-left: 0px'>" + sDate
						+ " ~ " + lDate + "</div><div>" + sc.getTitle()
						+ "</div>");
			}
		}
	}
}
