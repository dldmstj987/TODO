package com.controller;

import com.dto.MemberDTO;
import com.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@Log4j2
@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Login GET....");
        req.getRequestDispatcher("/WEB-INF/todo/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Login POST....");

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");
        String auto = req.getParameter("auto");
        boolean rememberMe = auto != null && auto.equals("on"); //auto를 체크하면 true;

        try{
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid, mpw);

            if(rememberMe){
                //로그인 성공을 해야 임의의 문자열 생성
                String uuid = UUID.randomUUID().toString(); //임의의 문자열 생성

                MemberService.INSTANCE.modifyUuid(mid, uuid);
                memberDTO.setUuid(uuid);

                Cookie rememberCookie = new Cookie("remember-me", uuid);
                rememberCookie.setMaxAge(7 * 24 * 60 * 60) ; // 쿠키 유효기간은 1주일;
                rememberCookie.setPath("/");
            }
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", memberDTO);
            resp.sendRedirect("/todo/list");
        } catch (Exception e){
            resp.sendRedirect("/todo/list");
        }
    }

}
