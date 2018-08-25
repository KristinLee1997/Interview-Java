package com.kristin;


import java.util.HashMap;

/**
 * @author Kristin
 * @since 2018/7/27 23:09
 **/
public class Test {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        System.out.println(map.get(1));
        if(map.get(1)==null){
            map.put(1,1);
        }
        System.out.println(map.get(1));
        if(map.get(1)==null){
            map.put(1,1);
        }else{
            map.put(1,map.get(1));
        }
    }
}