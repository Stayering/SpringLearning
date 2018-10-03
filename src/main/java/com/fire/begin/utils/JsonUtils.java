package com.fire.begin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json封装工具类
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object data) {
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
