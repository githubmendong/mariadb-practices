package bookmall.dao;

import bookmall.BookMallDB;
import bookmall.vo.CartVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDao extends BookMallDB {

    public void findAll() {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. 데이터베이스 연결
            conn = getConnection();

            // 2. SQL 쿼리 준비
            String sql =
                    "SELECT c.no, title, b.name, SUM(c.quantity), SUM(c.quantity * a.price) " +
                            "FROM book a, member b, cart c " +
                            "LEFT JOIN orders_book ob ON c.no = ob.orders_no " +
                            "WHERE a.no = c.book_no " +
                            "AND b.no = c.member_no " +
                            "GROUP BY c.no, title, b.name " +  // GROUP BY를 사용하여 중복된 결과 합치기
                            "ORDER BY c.no";
            pstmt = conn.prepareStatement(sql);

            // 3. SQL 실행 및 결과 가져오기
            rs = pstmt.executeQuery();

            // 4. 결과 출력
            while (rs.next()) {
                int cartNo = rs.getInt(1);
                String title = rs.getString(2);
                String memberName = rs.getString(3);
                int totalQuantity = rs.getInt(4); // 합계 수량
                int totalPrice = rs.getInt(5);   // 합계 가격

                System.out.println("["+cartNo +"] " +" [서적 이름] "+ title
                        + " " + memberName + " [수량] " + totalQuantity + " [가격] " + totalPrice);
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



    // 카트에 도서 추가 메서드
    public void insert(CartVo cartVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. 데이터베이스 연결
            conn = getConnection();

            // 2. SQL 쿼리 준비
            String sql =
                    "INSERT INTO cart VALUES (null, ?, ?, ?);";
            pstmt = conn.prepareStatement(sql);

            // 3. 값 바인딩
            pstmt.setInt(1, cartVo.getBookNo());
            pstmt.setInt(2, cartVo.getMemberNo());
            pstmt.setInt(3, cartVo.getQuantity());

            // 4. SQL 실행
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                // 5. 자원 정리
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
}
