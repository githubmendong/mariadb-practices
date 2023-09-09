package bookmall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookMallDB {
    protected static Connection getConnection() throws SQLException {

        Connection conn = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://172.20.10.3:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        }
        return conn;
    }
}
