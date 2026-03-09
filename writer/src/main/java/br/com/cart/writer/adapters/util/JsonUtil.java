package br.com.cart.writer.adapters.util;

import br.com.cart.writer.domain.exceptions.JsonUtilDeserializationException;
import br.com.cart.writer.domain.exceptions.JsonUtilSerializationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtil {

    private static final ObjectMapper mapper = createObjectMapper();

    private JsonUtil() {}

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);

        return objectMapper;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new JsonUtilDeserializationException(e.getMessage());
        }
    }

    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonUtilSerializationException(object);
        }
    }
}
