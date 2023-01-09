package com.marvelouspp.ppblog.domain.entity;

import java.math.BigInteger;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_background")
public class Background {

    private BigInteger id;
    
    private String name;

    private String url;
}
