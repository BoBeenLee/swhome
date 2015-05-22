package data.handler;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class Clob implements TypeHandler{

	public Object getResult(ResultSet rs, String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return rs.getString(columnLabel);
	}

	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return rs.getString(columnIndex);
	}

	public Object getResult(CallableStatement cs, int parameterIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		
		return cs.getObject(parameterIndex);
	}

	public void setParameter(PreparedStatement ps, int parameterIndex, Object parameter,
			JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		String str = (String) parameter;
		StringReader reader = new StringReader(str);
		ps.setCharacterStream(parameterIndex, reader, str.length());
	}
	
}
