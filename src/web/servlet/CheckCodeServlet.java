package web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建一个对象。在内存中图片（验证码图片）
        int width=100;
        int height=50;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //2.画图
        //2.1填充背景色
        Graphics graphics = image.getGraphics();//画笔对象
        graphics.setColor(Color.pink);//设置画笔颜色
        graphics.fillRect(0,0,width,height);
        //2.2画边框
        graphics.setColor(Color.blue);
        graphics.drawRect(0,0,width-1,height-1);

        String str ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
        //生成随机角标
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <=4 ; i++) {
            int index = random.nextInt(str.length());
            //获取字符
            char ch = str.charAt(index);
            stringBuilder.append(ch);
            //2.3写验证码
            graphics.drawString(ch+"",width/5*i,height/2);

        }
        String checkCode_session = stringBuilder.toString();
        request.getSession().setAttribute("checkCode_session",checkCode_session);
        //2.4画干扰线
        graphics.setColor(Color.green);

        //随机生成坐标点
        for (int i = 0; i <10 ; i++) {
            int x1 = random.nextInt(width-1);
            int x2 = random.nextInt(width-1);
            int y1 = random.nextInt(height-1);
            int y2 = random.nextInt(height-1);

            graphics.drawLine(x1,x2,y1,y2);
        }






        //3.将图片输出到页面展示
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
