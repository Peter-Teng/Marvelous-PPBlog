package com.marvelouspp.ppblog.domain.enums;

public enum Code {
    
    // 通用状态码
    SUCCESS(200, "Success!"),
    PARAM_FAULT(400, "请求的参数出错啦!"),
    NO_AUTH(403, "警告!未授权访问."),
    SYSTEM_ERROR(500, "系统内部有错误哦"),

    // 登录、注册相关状态码
    NO_USER(1000, "本站还没有主人,请先注册哦."),
    USER_EXIST(1001, "本站已经存在主人了, 快去登录吧!"),
    NAME_OR_PASSWORD_ERROR(1002, "用户名或密码出错了"),
    REGISTER_FAILURE(1003, "注册失败了!"),
    EMPTY_INFO(1004, "没收到用户名或者密码哦"),
    TOKEN_PARSED_FAILURE(1005, "Token没通过验证呢!"),
    TOKEN_EXPIRED(1006, "Token过期啦, 重新去登录吧!"),
    UPDATE_FAILURE(1007, "更新用户信息失败"),
    
    // 文章相关状态码
    ARTICLE_NOT_EXIST(2000, "查询的文章不存在哦"),
    // 标签相关状态码
    TAG_NOT_EXIST(3000, "查询的标签不存在哦"),
    
    // 文件上传相关状态码
    UPLOAD_FAILURE(4000, "文件上传失败"),

    // 其它功能
    NEWS_LOAD_FAILURE(5000, "加载公告失败");


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
