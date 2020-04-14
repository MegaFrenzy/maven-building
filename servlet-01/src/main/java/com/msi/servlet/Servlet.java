package com.msi.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * @Description: 生成验证码
 * @Author: MegaFrenzy
 * @CreateTime: 2020-04-11 18:19
 */
@WebServlet("/code")
public class Servlet extends javax.servlet.http.HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        PrintWriter writer = response.getWriter();
//        writer.print("Hello,Servlet");
//        ServletContext servletContext = this.getServletContext();
        //如何让浏览器3秒自动刷新一次;
        response.setHeader("refresh", "3");
        //在内存中创建一个图片
        BufferedImage bufferedImage = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();//画笔
        //设置图片的背景颜色
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 80, 20);
        //给图片写数据
        graphics.setColor(Color.BLUE);
        graphics.setFont(new Font(null, Font.BOLD, 20));
        graphics.drawString(randomNum(), 0, 20);
        //告诉浏览器，这个请求用图片的方式打开
        response.setContentType("image/jpeg");
        //网站存在缓存，不让浏览器缓存
        response.setDateHeader("expires", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        //把图片写给浏览器
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
    }

    private String randomNum() {
        Random random = new Random();
        String s = random.nextInt(999999) + "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6 - s.length(); i++) {
            sb.append("0");
        }
        s = sb.toString() + s;
        return s;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request, response);
    }
}
