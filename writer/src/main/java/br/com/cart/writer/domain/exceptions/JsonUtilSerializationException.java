package br.com.cart.writer.domain.exceptions;

import static br.com.cart.writer.domain.model.base.ConstantMessages.JSON_UTIL_SERIALIZATION_ERROR;

public class JsonUtilSerializationException extends RuntimeException {
    public JsonUtilSerializationException(Object object) {
        super(JSON_UTIL_SERIALIZATION_ERROR.formatted(object));
    }
}
