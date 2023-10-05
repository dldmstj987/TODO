package dao;


import com.dto.TodoDTO;
import com.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.List;

@Log4j2
public class TodoServiceTests {
    private TodoService todoService;

    @BeforeEach
    public void ready(){
        todoService = TodoService.INSTANCE;
    }
    
    @Test
    public void testResister() throws Exception{
        TodoDTO todoDTO = TodoDTO.builder()
                .title("JDBC Test Title")
                .dueDate(LocalDate.now())
                .build();
        log.info("-----------------------"); // 테스트 코드의 Log4j2 설정 확인
        log.info(todoDTO);
        todoService.register(todoDTO);

    }

    @Test
    public void testListAll() throws Exception{
        List<TodoDTO> dtoList = todoService.listAll();
        for(TodoDTO todoDTO : dtoList){
            log.info(todoDTO);
        }
    }

    @Test
    public void testGet() throws  Exception{
        long tno = 3;
        TodoDTO todoDTO = todoService.get(tno);
        log.info(todoDTO);

        log.info(todoService.get(2L));
    }

    @Test
    public void testRemove() throws  Exception{
        long tno = 8;
        log.info(todoService.get(tno));
        todoService.remove(tno);
    }

    @Test
    public  void testModify() throws Exception{
        long tno = 9;
        log.info(todoService.get(tno));
        todoService.modify(TodoDTO.builder()
                .tno(tno)
                .title("update")
                .dueDate(LocalDate.of(2022, 3, 21))
                .build());
        log.info(todoService.get(tno));
    }






}
