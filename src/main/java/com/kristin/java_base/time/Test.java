package com.kristin.java_base.time;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Test {
    public static void main(String[] args) {
        Date date = Date.from(LocalDate.now().
                atTime(0, 0, 0).
                atZone(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(LocalDate.now().
                atTime(23, 59, 59).
                atZone(ZoneId.systemDefault()).toInstant());

        System.out.println(date);
        System.out.println(date2);

    }
}
