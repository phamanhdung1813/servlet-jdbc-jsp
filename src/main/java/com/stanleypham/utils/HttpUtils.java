package com.stanleypham.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtils {
    private String jsonValue;

    public HttpUtils(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public static HttpUtils of(BufferedReader bufferedReader) {
        // BufferedReader is json from client
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HttpUtils(stringBuilder.toString());
    }

    public <T> T toModel(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(jsonValue, tClass);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

}
