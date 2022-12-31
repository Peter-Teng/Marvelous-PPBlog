package com.marvelouspp.ppblog.domain.VO;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenVo {

    private BigInteger id;

    private String username;
    
    private String token;
}
