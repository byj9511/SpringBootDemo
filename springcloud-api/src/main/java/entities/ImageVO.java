package entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageVO {
    private String text;
    private LocalDateTime expireTime;

    public ImageVO(String text, Long duration) {
        this.text = text;
        this.expireTime = LocalDateTime.now().plusSeconds(duration);
    }

    public boolean isExpire(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
