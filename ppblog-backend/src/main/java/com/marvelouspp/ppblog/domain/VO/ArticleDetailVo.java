package com.marvelouspp.ppblog.domain.VO;

import java.math.BigInteger;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {

    private BigInteger id;

    private String title;

    private String content;

    private String summary;

    private String thumbnail;

    private Integer heat;

    private Date createDate;

    private Date modifyDate;

    private BigInteger tagId;

    private String tagName;

    private String userName;
}
