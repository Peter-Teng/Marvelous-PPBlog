package com.marvelouspp.ppblog.configuration;

import java.nio.charset.Charset;

public class Constant {

    //JWT Configuration
    //有效期为
    public static final Long JWT_TTL = 24 * 60 * 60 *1000L;// 一天
    //设置秘钥明文
    public static final String JWT_KEY = "MarvelousPPMarvelousPPMarvelousPPMarvelousPPMarvelousPP";
    public static final String JWT_HEADER = "token";
    
    
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    public static final String COS_REGION = "ap-guangzhou";

    public static final int VALID = 1;
    //文章是草稿
    public static final int ARTICLE_STATUS_DRAFT = 0;
    //文章是正常分布状态
    public static final int ARTICLE_STATUS_NORMAL = 1;

    //Redis Key命名规则
    public static final String USER_PROFILE = "USER_PROFILE_CACHE";
    public static final String USER_LOGIN_OBJECT = "USER_LOGIN_OBJECT_%s";
    public static final String HEAT_MAP = "ARTICLE_HEAT_MAP";

}
