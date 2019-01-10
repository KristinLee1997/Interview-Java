package com.kristin.fastjson.complex;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestFastJson {

    /**
     * 读取类路径下的配置文件
     * 解析成对象数组并返回
     *
     * @throws IOException
     */
    @Test
    public List<Query> test() throws IOException {
        // 读取类路径下的query.json文件
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream inputStream = cl.getResourceAsStream("query.json");
        String jsontext = IOUtils.toString(inputStream, "utf8");
        // 先将字符节串转为List数组
        List<Query> queryList = JSON.parseArray(jsontext, Query.class);
        for (Query query : queryList) {
            List<Column> columnList = new ArrayList<Column>();
            List<LinkedHashMap<String, Object>> columns = query.getColumn();
            for (LinkedHashMap<String, Object> linkedMap : columns) {
                //将map转化为java实体类
                Column column = (Column) mapToObject(linkedMap, Column.class);
                System.out.println(column.toString());
                columnList.add(column);
            }
            query.setColumnList(columnList); //为columnList属性赋值
        }
        return queryList;
    }

    /**
     * Map --> 实体对象
     *
     * @param map   map实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String flag = (String) map.get(field.getName());
                if (flag != null) {
                    if (flag.equals("false") || flag.equals("true")) {
                        field.set(obj, Boolean.parseBoolean(flag));
                    } else {
                        field.set(obj, map.get(field.getName()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        try {
            List<Query> list = new TestFastJson().test();
            for (Query query : list) {
                System.out.println(query);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
