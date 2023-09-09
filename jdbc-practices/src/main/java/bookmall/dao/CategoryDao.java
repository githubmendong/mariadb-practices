package bookmall.dao;

import bookmall.BookMallDB;
import bookmall.vo.CategoryVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDao extends BookMallDB {
    public void findAll() {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            // 준비된 SQL 쿼리
            String sql = "SELECT * FROM category";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int categoryNo = rs.getInt(1);
                String name = rs.getString(2);

                System.out.println("["+categoryNo +"]"+  " [카테고리] " + name);
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                // 자원 정리
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void insert(CategoryVo categoryVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql = "insert into category values(null,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, categoryVo.getName());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if(pstmt != null) {
                    pstmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
