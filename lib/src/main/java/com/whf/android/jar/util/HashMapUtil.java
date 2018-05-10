package com.whf.android.jar.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Tool class of JavaBean and map conversion based on java reflection
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class HashMapUtil {
    /**
     * javaBean Transformation Map
     *
     * @param object:JavaBean needed to convert
     * @return Conversion result map
     * @throws Exception
     */
    public static Map<String, Object> beanToMap(Object object) throws Exception {
        Map<String, Object> map = new HashMap<>();

        Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }

    /**
     * @param map:map
     * @param cls:Class object of object javaBean
     * @return Target class object
     * @throws Exception
     */
    public static Object mapToBean(Map<String, Object> map, Class cls) throws Exception {
        Object object = cls.newInstance();
        for (String key : map.keySet()) {
            Field temFields = cls.getDeclaredField(key);
            temFields.setAccessible(true);
            temFields.set(object, map.get(key));
        }
        return object;
    }
}
