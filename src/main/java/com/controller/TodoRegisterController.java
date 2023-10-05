package com.controller;

import com.dto.TodoDTO;
import com.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "todoRegisterController", value = "/todo/register")
@Log4j2
public class TodoRegisterController extends HttpServlet {
    private final TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/register GET...");

        HttpSession session = req.getSession();
        if(session.isNew()){ //서버 특에서 새로운 HttpSession 객체를 생성한 경우에 true 반환
            log.info("/login");
            return;
        }
        if(session.getAttribute("loginInfo") == null){
            log.info("로그인한 정보가 없는 사용자");
            resp.sendRedirect("/login");
            return;
        }
        //정상적인 경우에는 입력화면으로
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoDTO todoDTO = TodoDTO.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"), dateTimeFormatter))
                .build();
        log.info("/todo/register POST....");
        log.info(todoDTO);
        try{
            todoService.register(todoDTO);
        } catch (Exception e){
            log.info(e.getMessage());
            throw new ServletException("read error");
        }
        resp.sendRedirect("/todo/list");
    }
}
