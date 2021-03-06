package com.fire.begin.utils;

import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Auther: nacl
 * @Date: 2018/10/3 0003 10:38
 * @Description: 反射工具类
 */
public class ReflectObjectUtils {


    /**
     * 反射获取对象的field对应的值，通过get方法获取
     *
     * @param data
     * @param field
     * @return
     */
    public static Object getValueByField(Object data, Field field) {

        String sFieldName = field.getName();// 获取字段名称
        Method[] methods = data.getClass().getMethods();// 获取类中所有方法
        Object value = null;// 存放值

        for (Method method : methods) {
            if (!method.getName().startsWith("get") && !method.getName().startsWith("is"))  // 非get或is方法不取
                continue;

            if (method.getName().substring(3).equalsIgnoreCase(sFieldName) || method.getName()
                    .equalsIgnoreCase(sFieldName)) {// 找到了字段的get方法
                try {
                    value = method.invoke(data, new Object[]{});// 调用get方法
                    return value;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }


    /**
     * 判断类是否为简单类型
     * @param type
     * @return
     */
    public static boolean isSimpleClassType(Class type) {

        return ClassUtils.isPrimitiveOrWrapper(type) || String.class.equals(type);
    }

}
