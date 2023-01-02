package com.marvelouspp.ppblog.domain.enums;

public enum Code {
    
    // 通用状态码
    SUCCESS(200, "Success!"),
    PARAM_FAULT(400, "Request parameters error!"),
    NO_AUTH(403, "no authentication."),
    SYSTEM_ERROR(500, "System Internal Error!"),

    // 登录、注册相关状态码
    NO_USER(1000, "No user information, please register first."),
    USER_EXIST(1001, "An user already exists."),
    NAME_OR_PASSWORD_ERROR(1002, "Username or password error!"),
    REGISTER_FAILURE(1003, "Failed to register!"),
    EMPTY_INFO(1004, "Empty Username or password!"),
    TOKEN_ISSUE(1005, "The token may have expired! Please login again"),
    UPDATE_FAILURE(1006, "User Information update failed"),
    
    // 文章相关状态码
    ARTICLE_NOT_EXIST(2000, "The articile does not exist!"),
    // 标签相关状态码
    
    // 文件上传相关状态码
    UPLOAD_FAILURE(4000, "Failed to upload file");


    int code;
    String message;

    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
