package com.msi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description:
 * @Author: MegaFrenzy
 * @CreateTime: 2020-04-12 11:45
 */
@WebServlet("/session01")
public class Session01 extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //得到Session
        HttpSession session = req.getSession();
        //给Session中存东西
        session.setAttribute("name","Mega");
        //获取Session的ID
        String sessionId = session.getId();

        //判断Session是不是新创建
        if (session.isNew()){
            resp.getWriter().write("session创建成功,ID:"+sessionId);
        }else {
            resp.getWriter().write("session以及在服务器中存在了,ID:"+sessionId);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
