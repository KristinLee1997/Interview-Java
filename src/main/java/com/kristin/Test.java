package com.kristin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kristin
 * @since 2018/7/27 23:09
 **/

public class Test {
    public static void main(String[] args) {
        String startDate = LocalDate.now().
                minusDays(3).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        System.out.println(startDate);
    }
}