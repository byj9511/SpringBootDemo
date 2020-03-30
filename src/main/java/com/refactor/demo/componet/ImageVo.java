package com.refactor.demo.componet;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ImageVo {
    private String text;
    private LocalDateTime expireTime;

    public ImageVo(String text, Long duration) {
        this.text = text;
        this.expireTime = LocalDateTime.now().plusSeconds(duration);
    }

    public boolean isExpire(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
