package com.mashreq.confbooking.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TestUtil {

    public  <T> T loadTestJson(String fileName, Class<T> valueType) throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource(fileName).getFile());

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, valueType);
    }
}
