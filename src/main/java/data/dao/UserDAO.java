package data.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import data.vo.check.Check;
import data.vo.user.EmployeeUser;
import data.vo.user.ProfessorUser;
import data.vo.user.StudentUnion;
import data.vo.user.StudentUser;
import data.vo.user.User;

public interface UserDAO {
	// List
	String STUDENTUSER_LIST = "select * " +
			"from studentview";
	
	@Select(STUDENTUSER_LIST)
	public List<StudentUser> studentUserList();
	
	
	String PROFESSORUSER_LIST = "select * " +
			"from professorview";
	
	@Select(PROFESSORUSER_LIST)
	public List<ProfessorUser> professorUserList();
	
	String EMPLOYEEUSER_LIST = "select * " +
			"from employeeview";
	
	@Select(EMPLOYEEUSER_LIST)
	public List<EmployeeUser> employeeUserList();
	
	// Insert
	/*		
	"insert into usertable(userid, userpw, usertel, useremail, question," +
	" answer, codeid, usergrade, lotterypoint, department, finishyear, grouppw, newmessage)" +
	" values(#{userId}, '#{userPw}', '#{userTel}', '#{userEmail}', '#{question}'," +
	"'#{answer}', #{codeId}, #{userGrade}, #{lotteryPoint}, '#{department}'" +
	", #{finishYear}, '#{groupPw}', #{newMessage})";*/
	String INSERT_STUDENT_USER = "{ CALL insert_student_user( #{Stu.userId}, #{Stu.userName}, #{Stu.userPw}, #{Stu.userTel}, #{Stu.userEmail}, #{Stu.question}, #{Stu.answer}, " + 
			"#{Stu.userGrade}, #{Stu.department}, #{Stu.finishYear}, #{Stu.groupPw}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} ) }";
	
	@Select(value = INSERT_STUDENT_USER)
	@Options(statementType=StatementType.CALLABLE)
	public Object insertStudentUser(@Param("Stu") StudentUser su, @Param("Check") Check checkNum);
	
	String INSERT_PROFESSOR_USER = "{ CALL insert_professor_user(#{Pro.userId}, #{Pro.userName}, #{Pro.userPw}, #{Pro.userTel}, #{Pro.userEmail}, #{Pro.question}, #{Pro.answer}, " + 
			"#{Pro.professorLocation}, #{Pro.extensionTel}, #{Pro.professorAbility}, #{Pro.professorCareer}, #{Pro.groupPw}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} ) }";
	
	@Select(value = INSERT_PROFESSOR_USER)
	@Options(statementType=StatementType.CALLABLE)
	public Object insertProfessorUser(@Param("Pro") ProfessorUser pu, @Param("Check") Check checkNum);
	
	String INSERT_EMPLOYEE_USER = "{ CALL insert_employee_user(#{Emp.userId}, #{Emp.userName}, #{Emp.userPw}, #{Emp.userTel}, #{Emp.userEmail}, #{Emp.question}, #{Emp.answer}, " + 
			"#{Emp.officeLocation}, #{Emp.extensionTel}, #{Emp.groupPw}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} ) }";

	@Select(value = INSERT_EMPLOYEE_USER)
	@Options(statementType=StatementType.CALLABLE)
	public Object insertEmployeeUser(@Param("Emp") EmployeeUser eu, @Param("Check") Check checkNum);

	// Select
	String SELECT_USER = "select userid, userpw, username, usertel, useremail, question," + 
			" answer, codeid, grouppw, newmessage" +
			" from usertable" +
			" where userid = #{userid}";
	
	@Select(SELECT_USER)
	public User selectUser(String userId);
	
	String SELECT_STUDENT_USER = "select * " +
			" from studentview" +
			" where ( userid in ( '${userId}' ) or username in ( '${userName }' ) ) and codeid in ( '${codeId}' )";
	
	@Select(SELECT_STUDENT_USER)
	public List<StudentUser> selectStudentUser(HashMap<String, String> hashmap);
	
	String SELECT_PROFESSOR_USER = "select * " + 
			" from professorview" +
			" where ( userid in ( '${userId}' ) or username in ( '${userName }' ) ) and codeid in ( '${codeId}' )";
	
	@Select(SELECT_PROFESSOR_USER)
	public List<ProfessorUser> selectProfessorUser(HashMap<String, String> hashmap);
	
	String SELECT_EMPLOYEE_USER = "select * " +
			" from employeeview" +
			" where ( userid in ( '${userId}' ) or username in ( '${userName }' ) ) and codeid in ( '${codeId}' )";

	@Select(SELECT_EMPLOYEE_USER)
	public List<EmployeeUser> selectEmployeeUser(HashMap<String, String> hashmap);
	
	// Update
	String UPDATE_STUDENT_USER = "{ CALL update_student_user( #{Stu.userId}, #{Stu.userName}, #{Stu.userTel}, #{Stu.userEmail}, #{Stu.question}, #{Stu.answer}, #{Stu.userGrade}, " +
			"#{Stu.department}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} ) }";
	
	@Select(value = UPDATE_STUDENT_USER)
	@Options(statementType=StatementType.CALLABLE)
	public Object updateStudentUser(@Param("Stu") StudentUser su, @Param("Check") Check checkNum);
	
	String UPDATE_PROFESSOR_USER = "{ CALL update_professor_user( #{Pro.userId}, #{Pro.userName}, #{Pro.userTel}, #{Pro.userEmail}, #{Pro.question}, #{Pro.answer}, #{Pro.professorLocation}, " +
			"#{Pro.extensionTel}, #{Pro.professorAbility}, #{Pro.professorCareer}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} ) }";
	
	@Select(value = UPDATE_PROFESSOR_USER)
	@Options(statementType=StatementType.CALLABLE)
	public Object updateProfessorUser(@Param("Pro") ProfessorUser pu, @Param("Check") Check checkNum);
	
	String UPDATE_EMPLOYEE_USER = "{ CALL update_employee_user( #{Emp.userId}, #{Emp.userName}, #{Emp.userTel}, #{Emp.userEmail}, #{Emp.question}, #{Emp.answer}, #{Emp.officeLocation}, " +
			"#{Emp.extensionTel}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} ) }";
	
	@Select(value = UPDATE_EMPLOYEE_USER)
	@Options(statementType=StatementType.CALLABLE)
	public Object updateEmployeeUser(@Param("Emp") EmployeeUser eu, @Param("Check") Check checkNum);
	
	// Update Password
	String UPDATE_PASSWORD = "{ CALL update_password( #{userId}, #{userPw}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} ) }";
	
	@Select(value = UPDATE_PASSWORD)
	@Options(statementType=StatementType.CALLABLE)
	public Object updatePassword(@Param("userId") String userId, @Param("userPw") String userPw, @Param("Check") Check checkNum);
	
	// Delete
	String DELETE_USER = "{CALL delete_user( #{userId}, #{userPw}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	
	@Select(value = DELETE_USER)
	@Options(statementType=StatementType.CALLABLE)
	public Object deleteUser(@Param("userId") String userId, @Param("userPw") String userPw, @Param("Check") Check checkNum);
	
	// UserLoginCheck
	String LOGIN_CHECK = "{CALL login_check( #{userId}, #{userPw}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	
	@Select(value = LOGIN_CHECK)
	@Options(statementType=StatementType.CALLABLE)
	public Object loginCheck(@Param("userId") String userId, @Param("userPw") String userPw, @Param("Check") Check checkNum);
	
	// SEARCH_PASSWORD
	String SEARCH_PASSWORD = "{CALL search_password( #{userId}, #{Check.checkStr, mode=OUT, jdbcType=VARCHAR, javaType=String} )}";
	
	@Select(value = SEARCH_PASSWORD)
	@Options(statementType=StatementType.CALLABLE)
	public Object searchPassword(@Param("userId") String userId, @Param("Check") Check checkStr);
	
	
	// GRANT_STUDENT_UNION
	String GRANT_STUDENT_UNION = "{CALL grant_studentunion( #{userId}, #{sDate}, #{lDate}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	
	@Select(value = GRANT_STUDENT_UNION)
	@Options(statementType=StatementType.CALLABLE)
	public Object grantStudentUnion(@Param("userId") String userId, @Param("sDate") Date sDate, @Param("lDate") Date lDate, @Param("Check") Check checkNum);
	
	// REVOKE_STUDENT_UNION
	String REVOKE_STUDENT_UNION = "{ CALL revoke_studentunion( #{userId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	
	@Select(value = REVOKE_STUDENT_UNION)
	@Options(statementType=StatementType.CALLABLE)
	public Object revokeStudentUnion(@Param("userId") String userId, @Param("Check") Check checkNum);
	
	// SELECT_STUDENT_UNION
	String SELECT_STUDENT_UNION = "select unionstartdate, unionlastdate " +
			"from studentuniontable " +
			"where userid = #{userId}";
	
	@Select(value = SELECT_STUDENT_UNION)
	public StudentUnion selectStudentUnion(@Param("userId") String userId);
}
