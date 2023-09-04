package bookmall.dao.test;

import bookshop.main.BookDao;

public class MemberDaoTest {
    public static void main(String[] args) {
        insertTest();
        findAllTest();
    }

    private static void insertTest() {
        BookDao dao = new BookDao();

        dao.insert(bookVo);
        dao.insert(bookVo);
    }
}