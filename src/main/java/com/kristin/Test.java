package com.kristin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kristin
 * @since 2018/7/27 23:09
 **/

public class Test {
    public static void main(String[] args) {
        Map<Long, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("aaa");
        map.put(2L, list);
        System.out.println(map);
        map.get(2L).add("bbb");
        System.out.println(map);
    }
}