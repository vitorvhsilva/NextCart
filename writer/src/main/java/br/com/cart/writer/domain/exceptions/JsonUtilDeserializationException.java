package br.com.cart.writer.domain.exceptions;

import static br.com.cart.writer.domain.model.base.ConstantMessages.JSON_UTIL_DESERIALIZATION_ERROR;

public class JsonUtilDeserializationException extends RuntimeException {
    public JsonUtilDeserializationException(String message) {
        super(JSON_UTIL_DESERIALIZATION_ERROR.formatted(message));
    }
}
