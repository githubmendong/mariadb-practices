package bookmall.dao;

import bookmall.BookMallDB;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDao extends BookMallDB {
    public void findAllOrder() {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            //3. ready SQL
            String sql = "select a.no, b.name, b.phone, b.email, a.orders_no, a.price, a.receive_address"
                    + " from orders a, member b"
                    + " where a.member_no = b.no";
            pstmt = conn.prepareStatement(sql);

            //5. SQL 실행
            rs = pstmt.executeQuery();

            while(rs.next()) {
                int no = rs.getInt(1);
                String name = rs.getString(2);
                String phone = rs.getString(3);
                String email = rs.getString(4);
                String orders_no = rs.getString(5);
                int price = rs.getInt(6);
                String receive_address = rs.getString(7);

                System.out.println(no + " - " + name + " " + phone
                        + " " + email + " " + orders_no + " " + price + " " + receive_address);
            }

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

    public void insertOrder(OrderVo orderVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql = "insert into orders values(null,? ,?,? ,?, ?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, orderVo.getMember_no());
            pstmt.setString(2, orderVo.getOrders_no());
            pstmt.setInt(3, orderVo.getOrders_price());
            pstmt.setString(4, orderVo.getAddress());
            pstmt.setString(5, orderVo.getName());

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

    public void findAllOrderBook() {
        ResultSet rs = null;
        ResultSet rs2 = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = getConnection();

            // 준비된 SQL 쿼리
            String sql = "SELECT * FROM orders_book;";
            pstmt = conn.prepareStatement(sql);
            String sql2 = "SELECT * FROM book;";
            pstmt2 = conn.prepareStatement(sql2);


            // SQL 실행 및 결과 가져오기
            rs = pstmt.executeQuery();
            rs2 = pstmt2.executeQuery();


            while (rs.next()) {
                String num = rs.getString("book_no"); // orders_book 테이블의 열 이름 사용

                String quantity = rs.getString("quantity"); // orders_book 테이블의 열 이름 사용

                // orders_book 테이블의 book_no를 이용해 book 테이블에서 도서 제목 가져오기
                String bookname = getBookTitleByNo(rs.getString("book_no"), rs2);

                System.out.println("[도서 번호] " + num + " [도서제목] " + bookname + " [수량] " + quantity);
            }

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

    private static String getBookTitleByNo(String bookNo, ResultSet bookResultSet) throws SQLException {
        while (bookResultSet.next()) {
            String no = bookResultSet.getString("no"); // book 테이블의 열 이름 사용
            String title = bookResultSet.getString("title"); // book 테이블의 열 이름 사용

            if (no.equals(bookNo)) {
                bookResultSet.beforeFirst(); // ResultSet 포인터 위치를 처음으로 돌립니다.
                return title;
            }
        }
        return "도서를 찾을 수 없음";
    }

    public void insertOrderBook(OrderBookVo orderbookVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql = "insert into orders_book values(?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, orderbookVo.getOrders_no());
            pstmt.setInt(2, orderbookVo.getBook_no());
            pstmt.setInt(3, orderbookVo.getQuantity());

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
