package com.service;

import com.dao.TodoDAO;
import com.domain.TodoVO;
import com.dto.TodoDTO;
import com.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public enum TodoService {
    INSTANCE;

    private TodoDAO todoDAO;

    private ModelMapper modelMapper;

    TodoService(){
        todoDAO = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.getInstance();
    }

    public void register(TodoDTO todoDTO) throws Exception{
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
//        System.out.println("todoVO : " + todoVO);
        log.info("todoVO : " + todoVO);
        todoDAO.insert(todoVO);
    }

    public List<TodoDTO> listAll() throws Exception{
        List<TodoVO> voList = todoDAO.selectAll();
        log.info("voList...");
        log.info(voList);
        List<TodoDTO> dtoList = new ArrayList<>();
        for(TodoVO vo : voList){
            dtoList.add(modelMapper.map(vo, TodoDTO.class));
        }
        return dtoList;
    }

    public TodoDTO get(Long tno) throws Exception{
        log.info("tno : " + tno);
        TodoVO todoVO = todoDAO.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }

    //삭제, 수정 기능 구현
    public void remove(Long tno) throws Exception{
        log.info("remove(Long tno)...");
        log.info("tno : " + tno);

        todoDAO.deleteOne(tno);
    }

    public void modify(TodoDTO todoDTO) throws Exception{
        log.info("modify(TodoDTO todoDTO)...");
        log.info("todoDTO : " + todoDTO);

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        todoDAO.updateOne(todoVO);
    }
}
