package data.bo;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

import data.dao.ArticleDAO;
import data.vo.article.NorArticle;
import data.vo.article.PartArticle;
import data.vo.article.PhotoArticle;
import data.vo.check.Check;


public class ArticleBO {
	// List
		// Nor
	public List<NorArticle> getNorArticleList(int boardId) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		List<NorArticle> articleVO = null;

		try {
			articleVO = dao.norArticleList(boardId);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return articleVO;
	}
		// Part
	public List<PartArticle> getPartArticleList(int boardId) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		List<PartArticle> articleVO = null;

		try {
			articleVO = dao.partArticleList(boardId);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return articleVO;
	}
	
		// Photo
	public List<PhotoArticle> getPhotoArticleList(int boardId) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		List<PhotoArticle> articleVO = null;

		try {
			articleVO = dao.photoArticleList(boardId);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return articleVO;
	}
	
	// Search 
		// Nor
	public List<NorArticle> getSearchNorArticle(HashMap<String, String> hashmap) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		List<NorArticle> articleVO = null;

		try {
			articleVO = dao.searchNorArticle(hashmap);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return articleVO;
	}
	
		// Part
	public List<PartArticle> getSearchPartArticle(HashMap<String, String> hashmap) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		List<PartArticle> articleVO = null;

		try {
			articleVO = dao.searchPartArticle(hashmap);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return articleVO;
	}
	
		// Photo	
	public List<PhotoArticle> getSearchPhotoArticle(HashMap<String, String> hashmap) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		List<PhotoArticle> articleVO = null;

		try {
			articleVO = dao.searchPhotoArticle(hashmap);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return articleVO;
	}
	
	// Search rowNum
	public List<NorArticle> getSearchNorArticleRowNum(int boardId, int rowNum) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		List<NorArticle> articleVO = null;

		try {
			articleVO = dao.searchNorArticleRowNum(boardId, rowNum);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return articleVO;
	}
	
	// Insert
	public int getInsertNorArticle(NorArticle norArticle){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.insertNorArticle(norArticle, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
	
	public int getInsertPartArticle(PartArticle partArticle, String targetArticleId) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.insertPartArticle(partArticle, targetArticleId, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
	
	// Delete
	public int getDeleteNorArticle(String norArticleId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.deleteNorArticle(norArticleId, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
	
	public int getDeletePartArticle(String partArticleId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.deletePartArticle(partArticleId, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
	
	// Update
	public int getUpdateNorArticle(String norArticleId, String norArticleTitle, String norArticleContent, String noticeYn, Date noticeDate){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.updateNorArticle(norArticleId, norArticleTitle, norArticleContent, noticeYn, noticeDate, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
	
	public int getUpdatePartArticle(String partArticleId, String partArticleTitle, String partArticleContent, String noticeYn, String partPassword, Date noticeDate){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		Check checkNum = new Check();
		
		try {
			dao.updatePartArticle(partArticleId, partArticleTitle, partArticleContent, noticeYn, partPassword, noticeDate, checkNum);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return checkNum.getCheckNum();
	}
	
	// Select
	public NorArticle getSelectNorArticle(String norArticleId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		NorArticle norArticle = null;
		
		try{
			norArticle = dao.selectNorArticle(norArticleId);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return norArticle; 
	}
	
	public PartArticle getSelectPartArticle(String partArticleId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		PartArticle partArticle = null;
		
		try{
			partArticle = dao.selectPartArticle(partArticleId);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		
		return partArticle; 
	}
	
	
	// Add hit
	public int getAddArticleHit(String articleId, String articleName){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.addArticleHit(articleId, articleName, checkNum);
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			session.close();
		}
		return checkNum.getCheckNum();
	}

	// Add Recommend
	public int getAddRecommend(String articleId, String userId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.addRecommend(articleId, userId, checkNum);
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	// Update Process
	public int getUpdateProcess(String articleId, int processId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.updateProcess(articleId, processId, checkNum);
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	// Check_Notice_Date
	public int getCheckNoticeDate(){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		ArticleDAO dao = session.getMapper(ArticleDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.checkNoticeDate();
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			session.close();
		}
		return checkNum.getCheckNum();
	}
}
