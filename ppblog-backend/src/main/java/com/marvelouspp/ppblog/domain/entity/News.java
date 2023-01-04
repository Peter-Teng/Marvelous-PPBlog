package com.marvelouspp.ppblog.domain.entity;

import java.math.BigInteger;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("news")
public class News {

    private BigInteger id;

    private String title;

    private String content;

    private Integer topped;

    private Integer valid;
}
