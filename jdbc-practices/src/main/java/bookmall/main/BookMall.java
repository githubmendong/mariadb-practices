package bookmall.main;

import bookmall.dao.*;
import bookmall.vo.*;

public class BookMall {

    public static void main(String[] args) {
        // 데이터 생성
        initData();

        // 회원 리스트 출력
        System.out.println("## 회원리스트");
        printMembers();
        System.out.println();

        // 카테고리 리스트 출력
        System.out.println("## 카테고리");
        printCategories();
        System.out.println();

        // 상품 리스트 출력
        System.out.println("## 상품");
        printBooks();
        System.out.println();

        // 카트 리스트 출력
        System.out.println("## 카트");
        printCarts();
        System.out.println();

        // 주문 리스트 출력
        System.out.println("## 주문");
        printOrders();
        System.out.println();

        // 주문 도서 리스트 출력
        System.out.println("## 주문 도서");
        printOrderBooks();
    }

    private static void initData() {
        MemberDao memberDao = new MemberDao();
        CategoryDao categoryDao = new CategoryDao();
        BookDao bookDao = new BookDao();
        CartDao cartDao = new CartDao();
        OrderDao orderDao = new OrderDao();

        // Member 데이터 생성
        memberDao.insert(new MemberVo("동헌", "010-1234-1234", "1234@naver.com", "naver1234"));
        memberDao.insert(new MemberVo("동징", "010-3214-3124", "3214@naver.com", "naver4321"));

        // Category 데이터 생성
        categoryDao.insert(new CategoryVo("자바"));
        categoryDao.insert(new CategoryVo("파이썬"));
        categoryDao.insert(new CategoryVo("씨플플"));

        // Book 데이터 생성
        bookDao.insert(new BookVo(1, "자바의정석", 29000));
        bookDao.insert(new BookVo(2, "파이썬의정석", 39000));
        bookDao.insert(new BookVo(3, "씨플플의정석", 30000));

        // Cart 데이터 생성
        cartDao.insert(new CartVo(1, 1, 1));
        cartDao.insert(new CartVo(2, 1, 2));

        // Order 데이터 생성
        orderDao.insertOrder(new OrderVo(1, "20230111111", 50000, "서울시 서초구 서초대 231번지", "SDH"));

        // OrderBook 데이터 생성
        orderDao.insertOrderBook(new OrderBookVo(1, 1, 2));
        orderDao.insertOrderBook(new OrderBookVo(1, 2, 1));
    }

    private static void printMembers() {
        MemberDao memberDao = new MemberDao();
        memberDao.findAll();
    }

    private static void printCategories() {
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.findAll();
    }

    private static void printBooks() {
        BookDao bookDao = new BookDao();
        bookDao.findAll();
    }

    private static void printCarts() {
        CartDao cartDao = new CartDao();
        cartDao.findAll();
    }

    private static void printOrders() {
        OrderDao orderDao = new OrderDao();
        orderDao.findAllOrder();
    }

    private static void printOrderBooks() {
        OrderDao orderDao = new OrderDao();
        orderDao.findAllOrderBook();
    }
}
