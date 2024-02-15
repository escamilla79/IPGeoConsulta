package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/geo";
    private static final String USER = "root";
    private static final String PASSWORD = "Esc4mill4_1979";
    
    private static DBHelper instance;
    

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
    	 Class.forName("com.mysql.cj.jdbc.Driver");
    	 return DriverManager.getConnection(URL,USER,PASSWORD);

    }
    
    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }
    
}