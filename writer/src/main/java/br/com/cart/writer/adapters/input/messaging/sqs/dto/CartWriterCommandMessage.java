package br.com.cart.writer.adapters.input.messaging.sqs.dto;

import br.com.cart.writer.adapters.util.JsonUtil;
import br.com.cart.writer.domain.model.cart.CartWriterCommand;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record CartWriterCommandMessage(
    UUID eventId,
    UUID cartId,
    String eventType,
    String costumerEmail,
    LocalDateTime eventDateTime,
    List<CartWriterCommandMessageProduct> products
) {
    public record CartWriterCommandMessageProduct(
        UUID productId,
        String name,
        int quantity
    ) {
    }

    public static CartWriterCommandMessage fromJson(String json) {
        String messageJson = extractMessageFromSnsNotification(json);
        return JsonUtil.fromJson(messageJson, CartWriterCommandMessage.class);
    }

    private static String extractMessageFromSnsNotification(String json) {
        try {
            Map<String, Object> snsNotification = JsonUtil.fromJson(json, Map.class);

            if (snsNotification.containsKey("Message")) {
                String message = (String) snsNotification.get("Message");
                return message;
            }

            return json;
        } catch (Exception e) {
            return json;
        }
    }

    public CartWriterCommand toDomain() {
        return new CartWriterCommand(
            eventId,
            cartId,
            CartWriterCommand.CartWriterCommandEventType.valueOf(eventType),
            costumerEmail,
            eventDateTime,
            products.stream()
                .map(p -> new CartWriterCommand.CartWriterCommandProduct(p.productId, p.name, p.quantity))
                .toList()
        );
    }
}
