package com.marvelouspp.ppblog.domain.entity;

import java.math.BigInteger;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    private BigInteger id;

    private Date date;

    private Long articles;

    private Long tags;

    private Long links;

    private Long news;
}
