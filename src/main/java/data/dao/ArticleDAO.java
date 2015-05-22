package data.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import data.vo.CodeInfo;
import data.vo.article.NorArticle;
import data.vo.article.PartArticle;
import data.vo.article.PhotoArticle;
import data.vo.check.Check;

public interface ArticleDAO {
	// List
	// Nor
	String NORARTICLE_LIST = "select norarticleid, norarticletitle, norarticledate, noticeyn, hit, commentcount, username, noticedate "
			+ "from norarticleview where boardid = #{boardId} order by noticeyn desc, norarticledate desc";

	@Select(NORARTICLE_LIST)
	List<NorArticle> norArticleList(@Param("boardId") int boardId);
	
	// Part
	String PARTARTICLE_LIST = "select partarticleid, partarticletitle, partarticledate, noticeyn, hit, commentcount, username, noticedate, recommend, step, codeName, parentarticleid, partpassword "
			+ "from partarticleview where boardid = #{boardId} order by noticeyn desc, to_number(replace(parentarticleid,'part','')) desc, distance asc";

	@Select(PARTARTICLE_LIST)
	List<PartArticle> partArticleList(@Param("boardId") int boardId);
	

	// Photo
	String PHOTOARTICLE_LIST = "select norarticleid, norarticletitle, norarticledate, noticeyn, hit, commentcount, username, noticedate, f.fileurl, f.filetype, f.fileName "
			+ "from norarticleview n left outer join ( select * from filetable where fileid in ( select min(fileid) from filetable where lower(filetype) in ('jpg', 'gif', 'png', 'bmp') group by articleid ) ) f " +
			"on n.norarticleid = f.articleid " +
			"where boardid = #{boardId} order by noticeyn desc, norarticledate desc";
	
	@Select(PHOTOARTICLE_LIST)
	List<PhotoArticle> photoArticleList(@Param("boardId") int boardId);
	
	// 						Select
		// Nor
	String SEARCH_NORARTICLE = "select norarticleid, norarticletitle, norarticledate, noticeyn, hit, commentcount, username, noticedate "
			+ "from norarticleview "
			+ "where boardid = ${boardId} and ( ( username like '%' || '${writer}' || '%' ) and ( norarticletitle like '%' || '${title}' || '%' ) and ( norarticlecontent like '%' || '${content}' || '%' ) )  "
			+ "order by noticeyn desc, norarticledate desc";

	@Select(SEARCH_NORARTICLE)
	List<NorArticle> searchNorArticle(HashMap<String, String> hashmap);

		// Part
	String SEARCH_PARTARTICLE = "select partarticleid, partarticletitle, partarticledate, noticeyn, hit, commentcount, username, noticedate, recommend, step, codeName, parentarticleid, partpassword "
			+ "from partarticleview "
			+ "where boardid = ${boardId} and ( ( username like '%' || '${writer}' || '%' ) and ( partarticletitle like '%' || '${title}' || '%' ) and ( partarticlecontent like '%' || '${content}' || '%' ) )  "
			+ "order by noticeyn desc, to_number(replace(parentarticleid,'part','')) desc, distance asc";

	@Select(SEARCH_PARTARTICLE)
	List<PartArticle> searchPartArticle(HashMap<String, String> hashmap);
	
		// Photo
	String SEARCH_PHOTOARTICLE = "select norarticleid, norarticletitle, norarticledate, noticeyn, hit, commentcount, username, noticedate, f.fileurl, f.filetype "
		+ "from norarticleview n join ( select * from filetable where fileid in ( select min(fileid) from filetable where lower(filetype) in ('jpg', 'gif', 'png', 'bmp') group by articleid ) ) f " 
		+ "on n.norarticleid = f.articleid " 
		+ "where boardid = ${boardId} and ( ( username like '%' || '${writer}' || '%' ) and ( norarticletitle like '%' || '${title}' || '%' ) and ( norarticlecontent like '%' || '${content}' || '%' ) )  "
		+  "order by noticeyn desc, norarticledate desc";

	@Select(SEARCH_PHOTOARTICLE)
	List<PhotoArticle> searchPhotoArticle(HashMap<String, String> hashmap);
	
	// Search Writer
		// Nor
	/*String NORARTICLE_SEARCH_WRITER = "select norarticleid, norarticletitle, norarticledate, noticeyn, hit, commentcount, username, noticedate "
			+ "from norarticleview "
			+ "where boardid = #{boardId} and ( username like '%' || #{writer} || '%' ) "
			+ "order by noticeyn desc, norarticledate desc";

	@Select(NORARTICLE_SEARCH_WRITER)
	List<NorArticle> norArticleSearchWriter(@Param("boardId") int boardId,
			@Param("writer") String writer);

		// Part
	String PARTARTICLE_SEARCH_WRITER = "select partarticleid, partarticletitle, partarticledate, noticeyn, hit, commentcount, username, noticedate, recommend, step, codeName, parentarticleid, partpassword "
			+ "from partarticleview "
			+ "where boardid = #{boardId} and ( username like '%' || #{writer} || '%' ) "
			+ "order by noticeyn desc, to_number(replace(parentarticleid,'part','')) desc, distance asc";

	@Select(PARTARTICLE_SEARCH_WRITER)
	List<PartArticle> partArticleSearchWriter(@Param("boardId") int boardId,
			@Param("writer") String writer);
		
		// Photo
	String PHOTOARTICLE_SEARCH_WRITER = "select norarticleid, norarticletitle, norarticledate, noticeyn, hit, commentcount, username, noticedate, f.fileurl, f.filetype "
			+ "from norarticleview n join ( select * from filetable where fileid in ( select min(fileid) from filetable where lower(filetype) in ('jpg', 'gif', 'png', 'bmp') group by articleid ) ) f " +
			"on n.norarticleid = f.articleid " +
			"where boardid = #{boardId} and ( username like '%' || #{writer} || '%' ) " +
			"order by noticeyn desc, norarticledate desc";
	
	@Select(PHOTOARTICLE_SEARCH_WRITER)
	List<PhotoArticle> photoArticleSearchWriter(@Param("boardId") int boardId,
			@Param("writer") String writer);*/
	
	// Search RowNum
	String NORARTICLE_SEARCH_ROWNUM = "select norarticleid, norarticletitle, norarticledate "
			+ "from ( select boardid, norarticleid, norarticletitle, norarticledate from norarticleview order by norarticledate desc )"
			+ "where boardid = #{boardId} and rownum <= #{rowNum}";
	
	@Select(NORARTICLE_SEARCH_ROWNUM)
	List<NorArticle> searchNorArticleRowNum(@Param("boardId") int boardId, @Param("rowNum") int rowNum);
	
	// Insert
	// Nor
	String INSERT_NORARTICLE = "{ CALL insert_nor_article( #{Article.boardId}, #{Article.norArticleTitle}, #{Article.norArticleContent}, "
			+ "#{Article.norArticleWriterId}, #{Article.noticeYn}, #{Article.noticeDate, mode=IN, jdbcType=DATE, javaType=Date}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer}, #{Article.norArticleId, mode=OUT, jdbcType=VARCHAR, javaType=String} )}";

	@Select(value = INSERT_NORARTICLE)
	@Options(statementType = StatementType.CALLABLE)
	Object insertNorArticle(@Param("Article") NorArticle norArticle,
			@Param("Check") Check checkNum);
	
	// Part
	String INSERT_PARTARTICLE = "{ CALL insert_part_article( #{Article.boardId}, #{Article.partArticleTitle}, #{Article.partArticleContent}, "
			+ "#{Article.partArticleWriterId}, #{Article.partPassword}, #{Article.noticeYn}, #{Article.processId}, #{Article.partPassword}, #{Article.noticeDate, mode=IN, jdbcType=DATE, javaType=Date}, #{targetArticleId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer}, #{Article.partArticleId, mode=OUT, jdbcType=VARCHAR, javaType=String} )}";
	
	@Select(value = INSERT_PARTARTICLE)
	@Options(statementType = StatementType.CALLABLE)
	Object insertPartArticle(@Param("Article") PartArticle partArticle, @Param("targetArticleId") String targetArticleId,
			@Param("Check") Check checkNum);
	
	
	// Delete
	// Nor
	String DELETE_NORARTICLE = "{ CALL delete_nor_article( #{norArticleId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";

	@Select(value = DELETE_NORARTICLE)
	@Options(statementType = StatementType.CALLABLE)
	Object deleteNorArticle(@Param("norArticleId") String norArticleId,
			@Param("Check") Check checkNum);

	// Part
	String DELETE_PARTARTICLE = "{ CALL delete_part_article( #{partArticleId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";

	@Select(value = DELETE_PARTARTICLE)
	@Options(statementType = StatementType.CALLABLE)
	Object deletePartArticle(@Param("partArticleId") String norArticleId,
			@Param("Check") Check checkNum);

	
	
	// Update
	// Nor
	String UPDATE_NORARTICLE = "{ CALL update_nor_article( #{norArticleId}, #{norArticleTitle}, #{norArticleContent}, "
			+ "#{noticeYn}, #{noticeDate}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";

	@Select(UPDATE_NORARTICLE)
	@Options(statementType = StatementType.CALLABLE)
	Object updateNorArticle(@Param("norArticleId") String norArticleId,
			@Param("norArticleTitle") String norArticleTitle,
			@Param("norArticleContent") String norArticleContent,
			@Param("noticeYn") String noticeYn,
			@Param("noticeDate") Date noticeDate, @Param("Check") Check checkNum);
	
	// Part
	String UPDATE_PARTARTICLE = "{ CALL update_part_article( #{partArticleId}, #{partArticleTitle}, #{partArticleContent}, "
			+ "#{noticeYn}, #{partPassword}, #{noticeDate}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";

	@Select(UPDATE_PARTARTICLE)
	@Options(statementType = StatementType.CALLABLE)
	Object updatePartArticle(@Param("partArticleId") String partArticleId,
			@Param("partArticleTitle") String partArticleTitle,
			@Param("partArticleContent") String partArticleContent,
			@Param("noticeYn") String noticeYn,
			@Param("partPassword") String partPassword,
			@Param("noticeDate") Date noticeDate, @Param("Check") Check checkNum);
	
	
	// Select
	String SELECT_NORARTICLE = "select *"
			+ " from norarticleview where norarticleid = #{norArticleId}";

	@Select(SELECT_NORARTICLE)
	NorArticle selectNorArticle(@Param("norArticleId") String norArticleId);

	String SELECT_PARTARTICLE = "select *"
			+ " from partarticleview where partarticleid = #{partArticleId}";

	@Select(SELECT_PARTARTICLE)
	PartArticle selectPartArticle(@Param("partArticleId") String partArticleId);
	
	
	// Add Hit
	String ADD_ARTICLE_HIT = "{ CALL add_article_hit( #{articleId}, #{articleName}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";

	@Select(ADD_ARTICLE_HIT)
	@Options(statementType = StatementType.CALLABLE)
	Object addArticleHit(@Param("articleId") String articleId, @Param("articleName") String articleName, 
			@Param("Check") Check checkNum);
	
	
	// Add Recommend
	String ADD_RECOMMEND = "{ CALL insert_recommend( #{articleId}, #{userId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";

	@Select(ADD_RECOMMEND)
	@Options(statementType = StatementType.CALLABLE)
	Object addRecommend(@Param("articleId") String articleId, @Param("userId") String userId, 
			@Param("Check") Check checkNum);
	
	
	// Update Process
	String UPDATE_PROCESS = "{ CALL update_process( #{articleId}, #{processId}, #{Check.checkNum, mode=OUT, jdbcType=INTEGER, javaType=Integer} )}";
	
	@Select(UPDATE_PROCESS)
	@Options(statementType = StatementType.CALLABLE)
	Object updateProcess(@Param("articleId") String articleId, @Param("processId") int processId, 
			@Param("Check") Check checkNum);
	
	// Check Notice Date
	String CHECK_NOTICE_DATE = "{ CALL check_notice_date() }";
	
	@Select(CHECK_NOTICE_DATE)
	@Options(statementType = StatementType.CALLABLE)
	Object checkNoticeDate();

}
