package br.com.cart.writer.adapters.output.persistence.dynamo;

import br.com.cart.writer.adapters.output.persistence.dynamo.entity.CartWriterCommandEntity;
import br.com.cart.writer.application.ports.output.CartWriterCommandPersistEventOutput;
import br.com.cart.writer.domain.exceptions.CartWriterCommandPersistEventException;
import br.com.cart.writer.domain.model.base.ConstantMessages;
import br.com.cart.writer.domain.model.cart.CartWriterCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Service
public class CartWriterCommandPersistEvent implements CartWriterCommandPersistEventOutput {

    private static final Logger logger = LoggerFactory.getLogger(CartWriterCommandPersistEvent.class);
    private static final String layer = "CartWriterCommandPersistEvent.execute";
    private static final String TABLE_NAME = "tb_cart_events_writer";

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final DynamoDbTable<CartWriterCommandEntity> table;

    public CartWriterCommandPersistEvent(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
        this.table = dynamoDbEnhancedClient.table(TABLE_NAME, TableSchema.fromClass(CartWriterCommandEntity.class));
    }

    @Override
    public void execute(CartWriterCommand command) {
        try {
            CartWriterCommandEntity entity = CartWriterCommandEntity.fromDomain(command);
            table.putItem(entity);
        } catch (Exception e) {
            logger.error("[{}] {}", layer, ConstantMessages.CART_WRITER_COMMAND_PERSIST_ERROR.formatted(command.toString()), e);
            throw new CartWriterCommandPersistEventException(command);
        }
    }
}
