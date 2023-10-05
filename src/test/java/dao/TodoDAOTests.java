package dao;

import com.dao.TodoDAO;
import com.domain.TodoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTests {
    private TodoDAO todoDAO;

    @BeforeEach //테스트 실행전 이 구문 먼저 실행
    public void ready(){
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception{
        System.out.println(todoDAO.getTime());
    }

    @Test
    public void testInsert() throws Exception {
        for(int i = 1; i <= 100; i++) {
            TodoVO todoVO = TodoVO.builder()
                    .title("Sample TItle..." + i)
                    .dueDate(LocalDate.of(2021, 12, 31))
                    .build();
            todoDAO.insert(todoVO);
        }
    }

    @Test
    public void testSelectAll() throws  Exception{
        List<TodoVO> list = todoDAO.selectAll();

        //1) 람다와 스트림 이용해서 출력
        list.forEach(todoVO -> System.out.println(todoVO));

        //2)foreach 사용
        for(TodoVO todoVO : list){
            System.out.println(todoVO);
        }
    }

    @Test
    public void testSelectOne() throws Exception{
        Long tno = 2L; //반드시 존재하는 번호를 이용.
        TodoVO todoVO = todoDAO.selectOne(tno);
        System.out.println(todoVO);

    }

    @Test
    public void testDeleteOne() throws Exception{
        List<TodoVO> list = todoDAO.selectAll();
        list.forEach(todoVO -> System.out.println(todoVO));

        Long tno = 5L;
        todoDAO.deleteOne(tno);

        //삭제확인

        list = todoDAO.selectAll();
        list.forEach(todoVO -> System.out.println(todoVO));
    }

    @Test
    public void testUpdateOne() throws Exception{
            TodoVO todoVO = TodoVO.builder()
                    .tno(2L)
                    .title("Sample Title Update..." )
                    .dueDate(LocalDate.of(2021, 1, 1 ))
                    .finished(true)
                    .build();
            todoDAO.updateOne(todoVO);

    }

}
