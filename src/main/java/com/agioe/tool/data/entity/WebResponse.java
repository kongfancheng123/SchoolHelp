package com.agioe.tool.data.entity;

public class WebResponse {
    private int code;
    private String message;
    private Object data;

    public static WebResponse success() {
        return new WebResponse()
                .setMeta(Meta.SUCCESS);
    }

    public static WebResponse success(Object data) {
        return new WebResponse()
                .setMeta(Meta.SUCCESS)
                .setData(data);
    }


    public static WebResponse success(Object data, int code, String message) {
        return new WebResponse()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }


    public static WebResponse unauthorized() {
        return new WebResponse()
                .setMeta(Meta.UNAUTHORIZED);
    }

    public static WebResponse clientError() {
        return new WebResponse()
                .setMeta(Meta.BAD_REQUEST);
    }

    public static WebResponse serverError() {
        return new WebResponse()
                .setMeta(Meta.SERVER_ERROR);
    }

    public static WebResponse error(int code, String message) {
        return new WebResponse()
                .setCode(code)
                .setMessage(message);
    }


    public int getCode() {
        return code;
    }

    public WebResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public WebResponse setMessage(String message) {
        this.message = message;
        return this;
    }


    public WebResponse setMeta(Meta meta) {
        this.setCode(meta.value);
        this.setMessage(meta.message);
        return this;
    }


    public Object getData() {
        return data;
    }

    public WebResponse setData(Object data) {
        this.data = data;
        return this;
    }


    enum Meta {
        /**
         * 成功
         */
        SUCCESS(200, "\u8bf7\u6c42\u6210\u529f"),
        /**
         * 参数错误
         */
        BAD_REQUEST(400, "\u8bf7\u6c42\u53c2\u6570\u6709\u8bef"),
        /**
         * 鉴权失败
         */
        UNAUTHORIZED(401, "\u65e0\u6743\u8bbf\u95ee"),
        /**
         * 服务端异常
         */
        SERVER_ERROR(500, "\u670d\u52a1\u5f02\u5e38");

        private int value;
        private String message;

        Meta(int value, String message) {
            this.value = value;
            this.message = message;
        }
    }
}
