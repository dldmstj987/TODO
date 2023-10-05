package com.controller;

import com.dto.TodoDTO;
import com.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "todoReadController", value = "/todo/read")
public class TodoReadController extends HttpServlet {
    private final TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long tno = Long.parseLong(req.getParameter("tno"));

            TodoDTO todoDTO = todoService.get(tno);

            //데이터담기
            req.setAttribute("dto", todoDTO);


            //쿠키찾기
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
            String todoListStr = viewTodoCookie.getValue();

            boolean exist = false;
            if(todoListStr != null && todoListStr.contains(" - " + tno + "-")){
                //보고 있는 게시물의 tno를 가진 값이 문자열에 존재하면
                exist = true;
            }
            log.info("exits : " + exist); //로그 확인이 필요없으묜 위와 아래의 구문을 하나로 묶는게 좋음
            if(!exist){ //보고 있는 게시물의 tno를 가진 값이 문자열에 존재하지 않으면
                todoListStr += tno + "-";
                viewTodoCookie.setValue(todoListStr); //해당 게시물의 tno를 포함한 문자열을 쿠키의 값으로 변경
                viewTodoCookie.setPath("/");
                viewTodoCookie.setMaxAge(24 * 60 * 60);
                resp.addCookie(viewTodoCookie);
            }

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ServletException("read error");
        }
    }

    private Cookie findCookie(Cookie[] cookies, String cookiesName) {
        /*매개변수로 받은 Cookies에서 cookieName 이름을 가진 쿠키를 찾아서 리턴.
         * 없으면 cookieName 이름의 쿠키를 만들어서 반환*/

        Cookie targetCookie = null;

        if (cookies != null && cookies.length > 0) { //매개변수로 받은 쿠키가 있는 경우
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookiesName)) { //찾는 쿠키가 있는 경우
                    targetCookie = cookie;
                    break;

                }
            }
        }

        if (targetCookie == null) {
            targetCookie = new Cookie(cookiesName, "-");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(24 * 60 * 60);
        }
        return targetCookie;

    }

}
