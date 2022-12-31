package com.marvelouspp.ppblog.domain.entity;

import java.math.BigInteger;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User {

    private BigInteger id;

    private String username;
    
    private String password;

    private String avatar;

    private String taboo;
    
    private String email;

    private String github;

    private String bilibili;

    private String wechat;

    private String leetcode;
}
