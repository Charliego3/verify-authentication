package io.github.nzlong.verify.controller;

import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpRequest;
import com.blade.mvc.http.HttpResponse;
import com.blade.mvc.http.HttpSession;
import io.github.nzlong.verify.kit.imageCode.ValidateCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author: nzlong
 * @description:
 * @date: Create in 2018 03 18 下午9:03
 */
@Path
public class VerifyCodeController {

    /**
     * 生成随机图片
     */
    @Route(value = "getPic")
    public void getValidateCode(HttpRequest request, HttpResponse response, HttpSession session) {

        //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(ValidateCode.width, ValidateCode.height, BufferedImage.TYPE_INT_BGR);

        //产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, ValidateCode.width, ValidateCode.height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        g.setColor(ValidateCode.getRandColor(110, 133));

        // 绘制干扰线
        for (int i = 0; i <= ValidateCode.lineSize; i++) {
            ValidateCode.drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= ValidateCode.stringNum; i++) {
            randomString = ValidateCode.drowString(g, randomString, i);
        }
        System.out.println("verify code: "+ randomString);
        session.removeAttribute(ValidateCode.RANDOMCODEKEY);
        session.attribute(ValidateCode.RANDOMCODEKEY, randomString.toUpperCase());
        g.dispose();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byte[] bytes = baos.toByteArray();

            //将内存中的图片输出到客户端
            response.header("Content-Type", "image/jpeg");
            response.outputStream().write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
