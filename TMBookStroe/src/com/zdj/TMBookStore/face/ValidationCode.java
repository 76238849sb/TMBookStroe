package com.zdj.TMBookStore.face;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author 华韵流风
 */
@WebServlet(name = "validationCode", urlPatterns = "/face/validationCode")
public class ValidationCode extends HttpServlet {
    private static final String codeChars = "1234567890abcdefghijklmnopqrstuvwxyz";

    private static Color getRandomColor(int minColor, int maxColor) {
        Random random = new Random();
        if (minColor > 255) {
            minColor = 255;
        }
        if (maxColor > 255) {
            maxColor = 255;
        }
        int red = minColor + random.nextInt(maxColor - minColor);
        int green = minColor + random.nextInt(maxColor - minColor);
        int blue = minColor + random.nextInt(maxColor - minColor);
        return new Color(red, green, blue);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int charsLength = codeChars.length();

        response.setHeader("ragma", "No-cache");
        response.setHeader("Cach-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        int width = 100, height = 32;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandomColor(180, 250));
        g.fillRect(0, 0, width, height);

        g.setFont(new Font("Times New Roman", Font.ITALIC, height));
        g.setColor(getRandomColor(120, 180));
        StringBuilder validationCode = new StringBuilder();

        String[] fontNames = {"Times New Roman", "Book antiqua", "Arial"};

        for (int i = 0; i < 3 + random.nextInt(3); i++) {

            g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC, height));

            char codeChar = codeChars.charAt(random.nextInt(charsLength));
            validationCode.append(codeChar);

            g.setColor(getRandomColor(10, 100));
            //在图形上输出验证码字符，x y随机生成
            g.drawString(String.valueOf(codeChar), 16 * i + random.nextInt(7), height - random.nextInt(6));
        }

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(5 * 60);
        //将验证码保存在session对象中，key为validation_code
        session.setAttribute("validation_code", validationCode.toString());
        g.dispose();
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "JPEG", os);

    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
