package com.kakao.cafe.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionHelper {
    public static <U,T> List<T> convertMapToList (Map<U,T> map) {
        return new ArrayList<>(map.values());
    }

    public static <T> int getItemNumberOfList (List<T> list) {
        if( list == null ) {
            return 0;
        }
        return list.size();
    }
}
