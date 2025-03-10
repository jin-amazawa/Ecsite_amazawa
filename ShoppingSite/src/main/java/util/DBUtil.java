package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/shoppingsite_amazawa?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "2023weiyiZho";
	
	public static final Connection openConnection() throws SQLException{
		
		
		try {
            // JDBCドライバを明示的にロード（必要に応じて）
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBCドライバのロードに失敗しました", e);
        }
		
		
		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
		
		return con;
	}
}



