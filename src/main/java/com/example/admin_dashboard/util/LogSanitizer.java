package com.example.admin_dashboard.util;

import com.example.admin_dashboard.annotation.LogMasked;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LogSanitizer {
    public static Object sanitize(Object object) {

        if (object == null) {
            return null;
        }

        if (object instanceof String ||
                object instanceof Number ||
                object instanceof Boolean ||
                object instanceof Character ||
                object instanceof Long ||
                object instanceof Enum<?>) {
            return object;
        }

        if (object instanceof Collection<?> collection) {

            return collection.stream()
                    .map(LogSanitizer::sanitize)
                    .toList();
        }

        Map<String, Object> result = new HashMap<>();

        for (Field field : object.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            try {

                Object value = field.get(object);

                if (field.isAnnotationPresent(LogMasked.class)) {

                    result.put(field.getName(), "****");

                } else {

                    result.put(field.getName(), value);
                }

            } catch (Exception e) {

                result.put(field.getName(), "ERROR");
            }
        }

        return result;
    }
}
