package com.kakao.cafe.helper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionHelper {
    public static <U,T> List<T> convertMapToList (Map<U,T> map) {
        return map.values().stream().collect(Collectors.toList());
    }
}
