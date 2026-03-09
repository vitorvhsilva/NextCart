package br.com.cart.writer.adapters.output.persistence.dynamo.entity;

import br.com.cart.writer.domain.model.cart.CartWriterCommand;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import br.com.cart.writer.adapters.output.persistence.dynamo.converter.LocalDateTimeConverter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@DynamoDbBean
public class CartWriterCommandEntity {

    private UUID eventId;
    private UUID cartId;
    private String eventType;
    private String costumerEmail;
    private LocalDateTime eventDateTime;
    private List<CartWriterCommandEntityProduct> products;

    public CartWriterCommandEntity() {}

    public CartWriterCommandEntity(UUID eventId, UUID cartId, String eventType,
                                   String costumerEmail, LocalDateTime eventDateTime,
                                   List<CartWriterCommandEntityProduct> products) {
        this.eventId = eventId;
        this.cartId = cartId;
        this.eventType = eventType;
        this.costumerEmail = costumerEmail;
        this.eventDateTime = eventDateTime;
        this.products = products;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("eventId")
    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    @DynamoDbAttribute("cartId")
    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    @DynamoDbAttribute("eventType")
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @DynamoDbAttribute("costumerEmail")
    public String getCostumerEmail() {
        return costumerEmail;
    }

    public void setCostumerEmail(String costumerEmail) {
        this.costumerEmail = costumerEmail;
    }

    @DynamoDbAttribute("eventDateTime")
    @DynamoDbConvertedBy(LocalDateTimeConverter.class)
    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    @DynamoDbAttribute("products")
    public List<CartWriterCommandEntityProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CartWriterCommandEntityProduct> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartWriterCommandEntity that = (CartWriterCommandEntity) o;
        return Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }

    public static CartWriterCommandEntity fromDomain(CartWriterCommand command) {
        return new CartWriterCommandEntity(
            command.eventId(),
            command.cartId(),
            command.eventType().name(),
            command.costumerEmail(),
            command.eventDateTime(),
            command.products().stream()
                .map(p -> new CartWriterCommandEntityProduct(p.productId(), p.name(), p.quantity()))
                .toList()
        );
    }

    @DynamoDbBean
    public static class CartWriterCommandEntityProduct {
        private UUID productId;
        private String name;
        private int quantity;

        public CartWriterCommandEntityProduct() {}

        public CartWriterCommandEntityProduct(UUID productId, String name, int quantity) {
            this.productId = productId;
            this.name = name;
            this.quantity = quantity;
        }

        @DynamoDbAttribute("productId")
        public UUID getProductId() {
            return productId;
        }

        public void setProductId(UUID productId) {
            this.productId = productId;
        }

        @DynamoDbAttribute("name")
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @DynamoDbAttribute("quantity")
        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CartWriterCommandEntityProduct that = (CartWriterCommandEntityProduct) o;
            return Objects.equals(productId, that.productId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(productId);
        }
    }
}
