package com.marvelouspp.ppblog.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticLineVo <K, V> {
    
    private K field;

    private V number;
}
