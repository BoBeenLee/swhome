package data.bo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import data.dao.FileDAO;
import data.vo.check.Check;
import data.vo.file.FileInfo;

public class FileBO {
	// List
	public List<FileInfo> getFileList(String articleId) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		FileDAO dao = session.getMapper(FileDAO.class);
		List<FileInfo> fileVO = null;

		try {
			fileVO = dao.fileList(articleId);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return fileVO;
	}
	
	// Insert 
	public int getInsertFile(FileInfo fileInfo){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		FileDAO dao = session.getMapper(FileDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.insertFile(fileInfo, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
	
	// Delete
	
	public int getDeleteFile(int fileId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		FileDAO dao = session.getMapper(FileDAO.class);
		Check checkNum = new Check();
		
		try{
			dao.deleteFile(fileId, checkNum);
		} catch(Exception e){
			System.out.println(e);
		} finally{
			session.close();
		}
		return checkNum.getCheckNum();
	}
}
