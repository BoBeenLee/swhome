package data.bo;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConnectionFactory {
	private static final SqlSessionFactory sqlSessionFactory;
	
	static{
		String resource = "data/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	System.out.println(inputStream);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	//	System.out.println(sqlSessionFactory);
	}
	
	public static SqlSessionFactory getInstance(){
		return sqlSessionFactory;
	}
}
