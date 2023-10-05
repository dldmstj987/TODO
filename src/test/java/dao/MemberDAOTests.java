package dao;


import com.dao.MemberDAO;
import com.dao.TodoDAO;
import com.domain.MemberVO;
import com.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

@Log4j2
public class MemberDAOTests {
    private MemberDAO memberDAO;

    @BeforeEach //테스트 실행전 이 구문 먼저 실행
    public void ready(){memberDAO = new MemberDAO();
    }

    @Test
    public void testGetWithPassword() throws Exception{
        String mid = "user00";
        String mpw = "1111";

        MemberVO memberVO = memberDAO.getWithPassword(mid, mpw);

        if (memberVO != null){
            log.info("로그인 성공");
            log.info(memberVO);
        }

        mpw = "2222";
        memberVO = memberDAO.getWithPassword(mid, mpw);
        if(memberVO == null){
            log.info("로그인 실패");
            log.info(memberVO);
        }

    }

    @Test
    public void testUpdateUuid() throws Exception{
        String mid = "user00";
        String uuid = UUID.randomUUID().toString();
        log.info(uuid);
        memberDAO.updateUuid(mid, uuid);
        log.info(memberDAO.getWithPassword(mid, "1111"));
    }

    @Test
    public void testSelectUuid() throws Exception{
        String mid = "user01";
        MemberVO memberVO = memberDAO.selectUuid(mid);
        System.out.println(memberVO);


    }
}



