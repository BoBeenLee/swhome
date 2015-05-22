package data.bo;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import data.dao.ArticleDAO;
import data.dao.LotteryDAO;
import data.vo.lottery.*;
import data.vo.article.NorArticle;
import data.vo.check.Check;

public class LotteryBO {
	// List
	public List<Lottery> getLotteryWinList(String userId) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		LotteryDAO dao = session.getMapper(LotteryDAO.class);
		List<Lottery> lotteryEnterVO = null;

		try {
			lotteryEnterVO = dao.lotteryWinList(userId);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return lotteryEnterVO;
	}
	public List<LotteryPoint> getLotteryPointList(String userId, Date startDate, Date lastDate) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		LotteryDAO dao = session.getMapper(LotteryDAO.class);
		List<LotteryPoint> lotteryPointVO = null;

		try {
			lotteryPointVO = dao.lotteryPointList(userId, startDate, lastDate);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return lotteryPointVO;
	}
	public List<Lottery> getLotteryList(String userId, Date startDate, Date lastDate) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		LotteryDAO dao = session.getMapper(LotteryDAO.class);
		List<Lottery> lotteryPointVO = null;
		
		try {
			lotteryPointVO = dao.lotteryList(userId, startDate, lastDate);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return lotteryPointVO;
	}
	
	// Select
	public int getTotalPoint(String userId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		LotteryDAO dao = session.getMapper(LotteryDAO.class);	
		int totalPoint = 0;
		
		try {
			 totalPoint = dao.getTotalPoint(userId).getLotteryPoint();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return totalPoint;
	}
	
	
	// Insert
	public int getInsertNorArticle(String userId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		LotteryDAO dao = session.getMapper(LotteryDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.insertLotteryEnter(userId, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
	
	// Props
	public int getPointUserLottery(String userId, int codeId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		LotteryDAO dao = session.getMapper(LotteryDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.pointUserLottery(userId, codeId, checkNum);
		
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
	
	// Win 
	public void getWinLottery(){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		LotteryDAO dao = session.getMapper(LotteryDAO.class);
		
		try {
			dao.winLottery();
		
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
	}
}
