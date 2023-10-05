package com.controller;


import com.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "TodoRemoveController", value = "/todo/remove")
public class TodoRemoveController extends HttpServlet {

    private final TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tno = Long.parseLong(req.getParameter("tno"));
        log.info("tno : " + tno);
        try{
            todoService.remove(tno);
        } catch (Exception e){
            log.error(e.getMessage());
        }
        resp.sendRedirect("/todo/list");

    }
}


