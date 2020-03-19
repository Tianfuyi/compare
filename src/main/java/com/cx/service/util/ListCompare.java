package com.cx.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCompare {
    public static Map<String,Integer> mapCompare(List<String> oldList, List<String> newList) {
        long st = System.nanoTime();

        //若知道两个list大小区别较大，以大的list优先处理
        Map<String,Integer> map = new HashMap<>(oldList.size());

        //lambda for循环数据量越大，效率越高，小数据建议用普通for循环
        oldList.forEach(s -> map.put(s, 1) );

        newList.forEach(s -> {
            if(map.get(s)!=null)
            {
                //相同的数据
                map.put(s, 2);
            }else {
                //若只是比较不同数据，不需要此步骤，浪费资源
                map.put(s,3);   //
            }
        });

        System.out.println("mapCompare total times "+(System.nanoTime()-st));
        return map;
    }
}
