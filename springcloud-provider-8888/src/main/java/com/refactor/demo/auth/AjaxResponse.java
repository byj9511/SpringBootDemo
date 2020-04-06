package com.refactor.demo.auth;


import lombok.Data;

@Data
public class AjaxResponse {
    private String message;
    private int code;
    private Object data;

    private AjaxResponse(){};

    public static AjaxResponse success(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("登陆成功");
        return ajaxResponse;
    }
    public static AjaxResponse failure(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(400);
        ajaxResponse.setMessage("登陆失败");
        return ajaxResponse;
    }
}
