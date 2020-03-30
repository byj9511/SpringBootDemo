package com.refactor.demo.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.refactor.demo.componet.ImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class ImageController {
    @Resource
    DefaultKaptcha captchaProducer;
    @RequestMapping("/kaptcha")
    public void sendImage(HttpServletResponse response, HttpSession session) throws IOException {
        String text = captchaProducer.createText();
        //根据文本生成图片缓冲
        BufferedImage image = captchaProducer.createImage(text);
        ImageVo imageVo = new ImageVo(text, (long) 60);
        session.setAttribute("image", imageVo);
        //try()能自动管理资源的释放
        try(ServletOutputStream outputStream = response.getOutputStream()){
            ImageIO.write(image, "jpg",outputStream);
            outputStream.flush();
        }

    }
}
