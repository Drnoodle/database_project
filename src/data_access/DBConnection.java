package data_access;

import java.sql.*;

/**
 * Created by noodle on 17.05.16.
 */
public class DBConnection {


    private static final String URL =
            new StringBuilder("jdbc:mysql://localhost:3306/DB2016_G21")
                    .append("?useUnicode=true&characterEncoding=utf8")
                    .append("&connectionCollation=utf8_general_ci&useSSL=false")
                    .toString();

    private static final String USER = "root";

    private static final String PASSWORD = "hb1992";


    public static Connection getConnection() throws SQLException {

        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);

        return conn;

    }


    /*
     * Extract a 2 dimensional string array with a statement.
     */
    public static String[][] getData(PreparedStatement statement) throws SQLException{


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
