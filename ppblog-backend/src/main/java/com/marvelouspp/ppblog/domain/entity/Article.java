package com.marvelouspp.ppblog.domain.entity;

import java.math.BigInteger;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    public Article(BigInteger id, Integer heat) {
        this.id = id;
        this.heat = heat;
    }

    private BigInteger id;

    private String title;

    private String content;

    private String summary;

    private String thumbnail;

    private Integer heat;

    private Integer status;

    private Date createDate;

    private Date modifyDate;

    private BigInteger tagId;
}
