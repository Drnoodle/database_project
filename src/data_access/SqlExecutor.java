package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SqlExecutor {
	
	
	protected static final String URL = 
			new StringBuilder("jdbc:mysql://localhost:3306/everywords")
			.append("?useUnicode=true&characterEncoding=utf8")
			.append("&connectionCollation=utf8_general_ci")
			.toString();
	
	
	protected static final String USER = "root";
	
	protected static final String PASSWORD = "hb1992";
	
	
	
	private Connection connection;

	
	private static SqlExecutor instance = new SqlExecutor();
	
	
	
	private SqlExecutor(){
		
		if(instance != null){
			throw new IllegalAccessError();
		}
		
		try {
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public static SqlExecutor getInstance(){
		return instance;
	}
	

	
	
	public PreparedStatement getStatement(String request) throws SQLException{
		
		return connection.prepareStatement(request);
	}
	
	
	
	public String[][] getData(PreparedStatement statement) throws SQLException{
		
		
		ResultSet resultSet = statement.executeQuery();
		
		
		ResultSetMetaData metaData = resultSet.getMetaData(); 

		
		resultSet.last(); 
		int totalRow = resultSet.getRow(); 
		resultSet.beforeFirst();
		int totalCol = resultSet.getMetaData().getColumnCount(); 
		
		String[][] results = new String[totalRow+1][totalCol];
		
		for(int i = 0; i < totalCol; i++){
			results[0][i] = metaData.getColumnName(i+1);
		}
		
		
		int currentRow = 0; 
		while(resultSet.next()){
			
			for(int currentCol = 0; currentCol<totalCol; currentCol++){
				results[currentRow][currentCol] = resultSet.getString(currentCol+1);
			}
			
			currentRow++;
		}
		
		
		return results;
	}

	
	
	
}

