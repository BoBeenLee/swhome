package data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import data.vo.subjectproc.SubjectProc;

public interface SubjectProcDAO {
	// List
	String SUBJECTPROC_LIST = "select * " +
							"from subjectproctable " +
							"where grade = #{ grade }";
	
	@Select(SUBJECTPROC_LIST)
	List<SubjectProc> SubjectProcList(@Param("grade") int grade);
}
