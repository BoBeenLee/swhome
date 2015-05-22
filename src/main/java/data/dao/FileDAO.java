package data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import data.vo.check.Check;
import data.vo.file.FileInfo;

public interface FileDAO {
	// List
	String FILE_LIST = "select * from filetable where articleid = #{articleId}";
	
	@Select(FILE_LIST)
	public List<FileInfo> fileList(@Param("articleId") String articleId);
	
	// Insert
	String INSERT_FILE = "{ CALL insert_file(#{File.fileName}, #{File.fileSize}, #{File.fileType}, #{File.fileUrl}, " +
			"#{File.articleId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} ) }";
	@Select(value = INSERT_FILE)
	@Options(statementType=StatementType.CALLABLE)
	public Object insertFile(@Param("File") FileInfo fileInfo, @Param("Check") Check checkNum);
	
	
	// Delete
	String DELETE_FILE = "{ CALL delete_file( #{fileId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} ) }";
	@Select(value = DELETE_FILE)
	@Options(statementType=StatementType.CALLABLE)
	public Object deleteFile(@Param("fileId") int fileId, @Param("Check") Check checkNum);
	
}
