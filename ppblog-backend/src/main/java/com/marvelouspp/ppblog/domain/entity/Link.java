package com.marvelouspp.ppblog.domain.entity;

import java.math.BigInteger;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {

    private BigInteger id;

    private String name;

    private String description;

    private String icon;

    private String category;

    private String url;
    
    private Integer topped;
}
