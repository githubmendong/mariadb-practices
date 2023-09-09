package bookmall.main;

import bookmall.dao.*;
import bookmall.vo.*;

public class BookMall {

	public static void main(String[] args) {
		// Member 데이터 생성
		MemberDao memberDao = new MemberDao();
		memberDao.insert(new MemberVo("동징", "010-1234-1234", "1234@naver.com", "naver1234"));
		memberDao.insert(new MemberVo("머징", "010-3214-3124", "3214@naver.com", "naver4321"));

		// Member 리스트 출력
		System.out.println("## 회원리스트");
		memberDao.findAll();
		System.out.println();

		// Category 데이터 생성
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.insert(new CategoryVo("자바"));
		categoryDao.insert(new CategoryVo("파이썬"));
		categoryDao.insert(new CategoryVo("씨플플"));

		// Category 리스트 출력
		System.out.println("## 카테고리");
		categoryDao.findAll();
		System.out.println();

		// Book 데이터 생성
		BookDao bookDao = new BookDao();
		bookDao.insert(new BookVo(1, "자바의정석", 29000));
		bookDao.insert(new BookVo(2, "자바의정석2", 39000));
		bookDao.insert(new BookVo(3, "자바의정석3", 30000));

		// Book 리스트 출력
		System.out.println("## 상품");
		bookDao.findAll();
		System.out.println();

		// Cart 데이터 생성
		CartDao cartDao = new CartDao();
		cartDao.insert(new CartVo(1, 1, 1));
		cartDao.insert(new CartVo(2, 1, 2));

		// Cart 리스트 출력
		System.out.println("## 카트");
		cartDao.findAll();
		System.out.println();

		// Order 데이터 생성
		OrderDao orderDao = new OrderDao();
		orderDao.insertOrder(new OrderVo(1, "20230111111", 50000, "서울시", "SDH"));

		// Order 리스트 출력
		System.out.println("## 주문");
		orderDao.findAllOrder();
		System.out.println();

		// OrderBook 데이터 생성
		orderDao.insertOrderBook(new OrderBookVo(1, 1, 25));
		orderDao.insertOrderBook(new OrderBookVo(1, 2, 50));

		// OrderBook 리스트 출력
		System.out.println("## 주문 도서");
		orderDao.findAllOrderBook();
	}
}
