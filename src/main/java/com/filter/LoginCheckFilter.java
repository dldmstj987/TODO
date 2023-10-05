package com.filter;
import com.dto.MemberDTO;
import com.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns = {"/todo/*"})
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //로그인 확인 여부
        log.info("Login check filter...");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        log.info("session loginInfo" + session.getAttribute("loginInfo"));
        //로그인이 된 경우 종료로 수정
        if (session.getAttribute("loginInfo") == null) {
            resp.sendRedirect("/login");
            return;
        }


        //session에 loginInfo값이 없는 경우, 쿠키값을 확인해서 자동로그인 처리.
        Cookie cookie = findCookie(req.getCookies(), "remember-me");

        if(cookie == null){
            resp.sendRedirect("/login");
        }
        else {
            log.info("cookie는 존재하는 상황");
            String uuid = cookie.getValue();
            try{
                MemberDTO memberDTO = MemberService.INSTANCE.getByUUID(uuid);

                log.info("쿠키의 값으로 조회한 사용자 정보 : " + memberDTO);
                if (memberDTO == null){
                    throw new Exception("Cookie value is not vaild");
                }
                else {
                    session.setAttribute("loginInfo", memberDTO);
                    chain.doFilter(req, resp);
                }
            } catch (Exception e){
                log.error(e.getMessage());
                resp.sendRedirect("/login");
            }
        }


    }

    private Cookie findCookie(Cookie[] cookies, String cookieName){
        if (cookies == null || cookies.length == 0){
            return null;
        }

        for (Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName)){
                return  cookie;
            }
        }
        return null;
    }

}






