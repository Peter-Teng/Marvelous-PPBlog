package com.marvelouspp.ppblog.domain.VO;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileVo {

    private BigInteger id;

    private String username;
    
    private String avatar;

    private String taboo;

    private String email;

    private String github;

    private String bilibili;

    private String wechat;

    private String leetcode;
    
}
