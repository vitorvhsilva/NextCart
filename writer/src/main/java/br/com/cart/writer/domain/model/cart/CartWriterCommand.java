package br.com.cart.writer.domain.model.cart;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CartWriterCommand(
    UUID eventId,
    UUID cartId,
    CartWriterCommandEventType eventType,
    String costumerEmail,
    LocalDateTime eventDateTime,
    List<CartWriterCommandProduct> products
) {
    public enum CartWriterCommandEventType {
        POST,
        PUT,
        PATCH,
        DELETE
    }

    public record CartWriterCommandProduct(
        UUID productId,
        String name,
        int quantity
    ) {
    }
}
