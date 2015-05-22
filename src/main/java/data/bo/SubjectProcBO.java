package data.bo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import data.dao.SubjectProcDAO;
import data.vo.subjectproc.SubjectProc;

public class SubjectProcBO {
	// List
	public List<SubjectProc> getSubjectProcList(int grade) {
		SqlSession session = ConnectionFactory.getInstance().openSession();
		SubjectProcDAO dao = session.getMapper(SubjectProcDAO.class);
		List<SubjectProc> subjectProcVO = null;

		try {
			subjectProcVO = dao.SubjectProcList(grade);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return subjectProcVO;
	}
}
