package com.apiautomationtestframework.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFileUtil<T> {

    private final Class<T> classType;

    public JsonFileUtil(Class<T> classType) {
        this.classType = classType;
    }

    public T readJsonFile(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(jsonFilePath), classType);
    }

}
