package bookmall.dao;

import bookmall.BookMallDB;
import bookmall.vo.CartVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDao extends BookMallDB {

    public void findAll() {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. JDBC Driver Class 로딩

            conn = getConnection();

            // 2. 준비된 SQL 쿼리
            String sql =
                    "SELECT c.no, title, b.name, c.quantity * a.price " +
                            "FROM book a, member b, cart c " +
                            "WHERE a.no = c.book_no " +
                            "AND b.no = c.member_no " +
                            "ORDER BY c.no";
            pstmt = conn.prepareStatement(sql);

            // 3. SQL 실행
            rs = pstmt.executeQuery();

            // 4. 결과 출력
            while (rs.next()) {
                int cartNo = rs.getInt(1);
                String title = rs.getString(2);
                String memberName = rs.getString(3);
                int totalPrice = rs.getInt(4);

                System.out.println("["+cartNo +"] " +" [서적 이름] "+ title
                        + " " + memberName + " [가격] " + totalPrice);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                // 5. 자원 정리
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

    public void insert(CartVo cartVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = getConnection();

            //3. ready SQL
            String sql =
                    "insert into cart values(null,?,?,?);";
            pstmt = conn.prepareStatement(sql);

            //4. 값 binding
            pstmt.setInt(1, cartVo.getBookNo());
            pstmt.setInt(2, cartVo.getMemberNo());
            pstmt.setInt(3, cartVo.getQuantity());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                //6. 자원정리
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
