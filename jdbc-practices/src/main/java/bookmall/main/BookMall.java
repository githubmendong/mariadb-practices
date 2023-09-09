package bookmall.main;

import bookmall.dao.*;
import bookmall.vo.*;

public class BookMall {

    public static void main(String[] args) {
        // Member 데이터 생성
        MemberDao memberDao = new MemberDao();
        memberDao.insert(new MemberVo("동헌", "010-1234-1234", "1234@naver.com", "naver1234"));
        memberDao.insert(new MemberVo("동징", "010-3214-3124", "3214@naver.com", "naver4321"));
        // Category 데이터 생성
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.insert(new CategoryVo("자바"));
        categoryDao.insert(new CategoryVo("파이썬"));
        categoryDao.insert(new CategoryVo("씨플플"));
        // Book 데이터 생성
        BookDao bookDao = new BookDao();
        bookDao.insert(new BookVo(1, "자바의정석", 29000));
        bookDao.insert(new BookVo(2, "파이썬의정석", 39000));
        bookDao.insert(new BookVo(3, "씨플플의정석", 30000));
        // Cart 데이터 생성
        CartDao cartDao = new CartDao();
        cartDao.insert(new CartVo(1, 1, 1));
        cartDao.insert(new CartVo(2, 1, 2));
        // Order 데이터 생성
        OrderDao orderDao = new OrderDao();
        orderDao.insertOrder(new OrderVo(1, "20230111111", 50000, "서울시 서초구 서초대 231번지", "SDH"));
        // OrderBook 데이터 생성
        orderDao.insertOrderBook(new OrderBookVo(1, 1, 2));
        orderDao.insertOrderBook(new OrderBookVo(1, 2, 1));

        //        ## 회원리스트
        //                [1]  [이름] 동징 [전화번호] 010-1234-1234 [이메일] 1234@naver.com [비번] naver1234
        //                [2]  [이름] s머징 [전화번호] 010-3214-3124 [이메일] 3214@naver.com [비번] naver4321
        // Member 리스트 출력
        System.out.println("## 회원리스트");
        memberDao.findAll();
        System.out.println();

        //## 카테고리
        //                [1] [카테고리] 자바
        //                [2] [카테고리] 파이썬
        //                [3] [카테고리] 씨플플
        // Category 리스트 출력
        System.out.println("## 카테고리");
        categoryDao.findAll();
        System.out.println();



        //## 카트
        //                [1]  [서적 이름] 자바의정석  [수량] 50 [가격] 1450000
        //                [1]  [서적 이름] 자바의정석  [수량] 25 [가격] 725000
        //                [2]  [서적 이름] 파이썬의정석  [수량] 0 [가격] 0
        // Book 리스트 출력
        System.out.println("## 상품");
        bookDao.findAll();
        System.out.println();


        //        ## 카트
        //                [1]  [서적 이름] 자바의정석 동징 [가격] 29000
        //                [2]  [서적 이름] 파이썬의정석 머징 [가격] 39000
        // Cart 리스트 출력
        System.out.println("## 카트");
        cartDao.findAll();
        System.out.println();


        // Order 리스트 출력
        //        [1] [이름] 동징 [번호] 010-1234-1234 [이메일] 1234@naver.com [주문번호] 20230111111 [가격] 50000 [배송주소] 서울시
        System.out.println("## 주문");
        orderDao.findAllOrder();
        System.out.println();


        // OrderBook 리스트 출력
        //        ## 주문 도서
        //                [도서 번호] 1 [도서제목] 자바의정석 [수량] 25
        //                [도서 번호] 2 [도서제목] 파이썬의정석 [수량] 50
        System.out.println("## 주문 도서");
        orderDao.findAllOrderBook();
    }
}
