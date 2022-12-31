package com.marvelouspp.ppblog.domain;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import com.marvelouspp.ppblog.domain.enums.Code;

import lombok.Data;

@Data
public class ResponseObject<T> implements Serializable {

    private int code;

    private String message;
    
    private T data;

    public ResponseObject() {
        this.code = Code.SUCCESS.getCode();
        this.message = Code.SUCCESS.getMessage();
    }

    public ResponseObject(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseObject<?> success() {
        return new ResponseObject<>();
    }

    public static ResponseObject<?> success(Object data) {
        return new ResponseObject<>(Code.SUCCESS.getCode(), Code.SUCCESS.getMessage(), data);
    }

    public static ResponseObject<?> failure(Code code) {
        return new ResponseObject<>(code.getCode(), code.getMessage(), null);
    }

    public static ResponseObject<?> failure(Code code, String systemMessage) {
        return new ResponseObject<>(code.getCode(), systemMessage, code.getMessage());
    }

    public static void renderResult(HttpServletResponse response, String result) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
