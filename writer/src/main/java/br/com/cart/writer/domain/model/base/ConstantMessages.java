package br.com.cart.writer.domain.model.base;

public class ConstantMessages {
    public final static String CART_WRITER_COMMAND_RECEIVED = "Cart writer command listener message received. id: %s, body: %s";
    public final static String CART_WRITER_COMMAND_SUCCESS = "Cart writer command listener success processing message";
    public final static String CART_WRITER_COMMAND_ERROR = "Cart writer command listener error processing message";

    public final static String CART_WRITER_COMMAND_NOTIFICATION_ERROR = "Error publishing notification. Command: %s";
    public final static String CART_WRITER_COMMAND_PERSIST_ERROR = "Error persisting event. Command: %s";

    public final static String JSON_UTIL_SERIALIZATION_ERROR = "Error while serializing object to JSON string. Object: %s";
    public final static String JSON_UTIL_DESERIALIZATION_ERROR = "Error while deserializing JSON string. JSON: %s";
}
