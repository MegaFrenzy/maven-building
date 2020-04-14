package com.msi;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: MegaFrenzy
 * @CreateTime: 2020-04-12 10:45
 */
@WebServlet("/cookie")
public class Cookie01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            out.write("上次访问时间为:");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if(cookie.getName().equals("lastLoginTime")){

                    long lTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lTime);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    out.write(sdf.format(date));
                }
            }
        }else{
            out.write("第一次访问本站");
        }
        Cookie cookie = new Cookie("lastLoginTime",System.currentTimeMillis()+"");
        response.addCookie(cookie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
