package bookmall.dao.test;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

import java.util.List;

public class BookDaoTest {
    public static void main(String[] args) {
        EmaillistVo vo = new EmaillistVo();
        vo.setFirstName("둘");
        vo.setLastName("리3");
        vo.setEmail("dooly3@gmail.com");

        testInsert(vo);
        testFindAll();
        testDeleteByEmail("dooly3@gmail.com");
        testFindAll();
    }

    private static void testDeleteByEmail(String email) {
        new EmaillistDao().deleteByEmail(email);
    }

    private static void testFindAll() {
        List<EmaillistVo> list = new EmaillistDao().findAll();
        for(EmaillistVo vo : list) {
            System.out.println(vo);
        }
    }

    private static void testInsert(EmaillistVo vo) {
        new EmaillistDao().insert(vo);
    }
}
