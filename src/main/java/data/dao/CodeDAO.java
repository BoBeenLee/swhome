package data.dao;

import org.apache.ibatis.annotations.Select;

import data.vo.CodeInfo;

public interface CodeDAO {
	// Select 
	
	@Select("select codeid, codename, groupcode" +
	" from codetable" + 
	" where codeid = #{codeId}")
	CodeInfo selectCode(int codeId);
}
