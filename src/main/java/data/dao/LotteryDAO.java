package data.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import data.vo.article.NorArticle;
import data.vo.check.Check;
import data.vo.lottery.Lottery;
import data.vo.lottery.LotteryPoint;
import data.vo.user.StudentUser;

public interface LotteryDAO {
	// List
		// LotteryWin
	String LOTTERY_WIN_LIST = "select * " +
			"from lotteryview " +
			"where enteruserid = #{userId} and lotteryid is not null " +
			"order by lotterydate asc ";
	
	@Select(LOTTERY_WIN_LIST)
	List<Lottery> lotteryWinList(@Param("userId") String userId);
	
		// LotteryPoint
	String LOTTERY_POINT_LIST = "select * " +
			"from pointcontentview " +
			"where userid = #{userId} and (pointdate between #{startDate} and #{lastDate}) " +
			"order by pointdate asc ";
	
	@Select(LOTTERY_POINT_LIST)
	List<LotteryPoint> lotteryPointList(@Param("userId") String userId, @Param("startDate") Date startDate, @Param("lastDate") Date lastDate);
		
		// Lottery
	String LOTTREY_LIST = "select * " +
			"from lotteryview " +
			"where enteruserid = #{userId} and enterdate between #{startDate} and #{lastDate} " +
			"order by enterdate asc ";
	
	@Select(LOTTREY_LIST)
	List<Lottery> lotteryList(@Param("userId") String userId, @Param("startDate") Date startDate, @Param("lastDate") Date lastDate);
	
	// Select
	String TOTAL_POINT = "select * " +
			"from studentview " +
			"where userid = #{userId}";
	
	@Select(TOTAL_POINT)
	StudentUser getTotalPoint(@Param("userId") String userId);
	
	// Insert
	String INSERT_LOTTERY_ENTER = "{ CALL insert_lottery_enter( #{userId},  #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";

	@Select(value = INSERT_LOTTERY_ENTER)
	@Options(statementType = StatementType.CALLABLE)
	Object insertLotteryEnter(@Param("userId") String userId,
			@Param("Check") Check checkNum);
	
	// Point_User_Lottery
	String POINT_USER_LOTTERY = "{ CALL point_user_lottery( #{userId}, #{codeId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	
	@Select(value = POINT_USER_LOTTERY)
	@Options(statementType = StatementType.CALLABLE)
	Object pointUserLottery(@Param("userId") String userId, @Param("codeId") int codeId,
			@Param("Check") Check checkNum);
	
	// WIN_LOTTERY
	String WIN_LOTTERY = "{ CALL win_lottery() }";
	
	@Select(value = WIN_LOTTERY)
	@Options(statementType = StatementType.CALLABLE)
	Object winLottery();
	
}
