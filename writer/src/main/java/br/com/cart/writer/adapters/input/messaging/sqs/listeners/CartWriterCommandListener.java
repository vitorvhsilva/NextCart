package br.com.cart.writer.adapters.input.messaging.sqs.listeners;

import br.com.cart.writer.adapters.input.messaging.sqs.dto.CartWriterCommandMessage;
import br.com.cart.writer.application.ports.input.CartWriterCommandListenerInput;
import br.com.cart.writer.application.ports.input.CartWriterCommandUseCaseInput;
import br.com.cart.writer.domain.model.base.ConstantMessages;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.model.Message;

import static io.awspring.cloud.sqs.annotation.SqsListenerAcknowledgementMode.ON_SUCCESS;

@Component
public class CartWriterCommandListener implements CartWriterCommandListenerInput {

    private static final Logger logger = LoggerFactory.getLogger(CartWriterCommandListener.class);
    private static final String layer = "CartWriterCommandListener.listen";

    private final CartWriterCommandUseCaseInput commandUseCase;

    public CartWriterCommandListener(CartWriterCommandUseCaseInput commandUseCase) {
        this.commandUseCase = commandUseCase;
    }

    @Override
    @SqsListener(
            value = "${sqs.cart-writer-command-queue.name}",
            acknowledgementMode = ON_SUCCESS
    )
    public void listen(Message message) {
        try {
            logger.info("[{}] {}", layer, ConstantMessages.CART_WRITER_COMMAND_RECEIVED.formatted(message.messageId(), message.body()));

            var cartWriterCommandMessage = CartWriterCommandMessage.fromJson(message.body());

            commandUseCase.execute(cartWriterCommandMessage.toDomain());

            logger.info("[{}] {}", layer, ConstantMessages.CART_WRITER_COMMAND_SUCCESS.formatted(message.messageId(), message.body()));
        } catch (Exception e) {
            logger.error("[{}] {}", layer, ConstantMessages.CART_WRITER_COMMAND_ERROR.formatted(message.messageId(), message.body()), e);
        }
    }
}
