package data.bo;

import org.apache.ibatis.session.SqlSession;

import data.dao.CodeDAO;
import data.vo.CodeInfo;

public class CodeBO {
	// select
	public CodeInfo getCode(int codeId){
		SqlSession session = ConnectionFactory.getInstance().openSession();
		CodeDAO dao = session.getMapper(CodeDAO.class);
		CodeInfo codeInfo = null;
		
		try{
			codeInfo = dao.selectCode(codeId);
		} catch(Exception e){
			
		} finally{
			session.close();
		}
		return codeInfo;
	}
}
