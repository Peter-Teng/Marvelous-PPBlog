package com.marvelouspp.ppblog.domain.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pair<K, V> {
    
    private K key;

    private V value;
}
