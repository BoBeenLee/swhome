package control.init;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleTimerTask articleTimerTask = new ArticleTimerTask();
	private LotteryTimerTask lotteryTimerTask = new LotteryTimerTask();
	private Timer articleTimer;
	private Timer lotteryTimer;
	
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
    	super.init();
    	
    	Date firstTime = new Date(Calendar.getInstance().getTimeInMillis());
    	int articlePeriod = 1000 * 60 * 60 * 24; // 하루에 한번씩 작동
    	int lotteryPeriod = 1000 * 60 * 60 * 24 * 30; // 한달에 한번씩 작동
			
		articleTimer = new Timer();
		lotteryTimer = new Timer();
		//Date date = new Date(1000);
		
		articleTimer.schedule(articleTimerTask, firstTime, articlePeriod); // 공지사항 기간 갱신은 하루에 한번
		lotteryTimer.schedule(lotteryTimerTask, firstTime, lotteryPeriod); // 로터리 당첨은 한달에 한번 추첨
		
		//timer.schedule(myTimerTask, date);
	}
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		
		articleTimerTask.cancel();
		lotteryTimerTask.cancel();
		articleTimer.cancel();
		lotteryTimer.cancel();
	}

	
}
