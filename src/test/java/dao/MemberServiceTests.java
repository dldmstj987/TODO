package dao;


import com.dto.MemberDTO;
import com.service.MemberService;
import com.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@Log4j2
public class MemberServiceTests {

    private MemberService memberService;

    @BeforeEach
    public void ready(){
        memberService= MemberService.INSTANCE;
    }

    @Test
    public void testLogin() throws Exception{
        String mid = "user00";
        String mpw = "1111";
        MemberDTO memberDTO = memberService.login(mid, mpw);

        if (memberDTO != null){
            log.info("로그인 성공");
            log.info(memberDTO);
        }

        try{
            mpw = "2222";
            memberDTO = memberService.login(mid, mpw);
            log.info(memberDTO);
        } catch (IllegalArgumentException e){
            log.info("로그인 실패");
            log.info(e.getMessage());
        }
    }
}
