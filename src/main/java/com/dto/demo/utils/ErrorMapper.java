package com.dto.demo.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ErrorMapper {

    /**
     * Creates map with key: "message" and value: exception's message.
     *
     * @param e - the thrown exception
     * @return the created map
     */
    public Map<String, String> createErrorMap(Throwable e) {
        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("message", e.getMessage());

        return errorMsg;
    }

    public Map<String, String> createErrorMap(String message) {
        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("message", message);

        return errorMsg;
    }
}
