package com.mashreq.confbooking.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CommonUtil {

    public static <T> T fromJson(String data, Class<T> valueType) {
        ObjectMapper mapper = new ObjectMapper();
        if (StringUtils.isNotEmpty(data)){
            try {
                mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                return mapper.readValue(data, valueType);
            } catch (Exception e) {
                log.error("Failed to parse response in CommonUtil.fromJson {}", e);
            }
        }
        return null;
    }

    public static String toJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        if (ObjectUtils.isNotEmpty(o)) {
            try {
                return mapper.writeValueAsString(o);
            } catch (Exception e) {
                log.error("Failed to parse response in CommonUtil.toJson {}", e);
            }
        }
        return StringUtils.EMPTY;
    }

}
