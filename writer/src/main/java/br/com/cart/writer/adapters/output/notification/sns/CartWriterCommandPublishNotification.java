package br.com.cart.writer.adapters.output.notification.sns;

import br.com.cart.writer.application.ports.output.CartWriterCommandPublishNotificationOutput;
import br.com.cart.writer.domain.exceptions.CartWriterCommandPublishNotificationException;
import br.com.cart.writer.domain.model.base.ConstantMessages;
import br.com.cart.writer.domain.model.cart.CartWriterCommand;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CartWriterCommandPublishNotification implements CartWriterCommandPublishNotificationOutput {

    private static final Logger logger = LoggerFactory.getLogger(CartWriterCommandPublishNotification.class);
    private static final String layer = "CartWriterCommandPublishNotification.execute";

    private final SnsTemplate snsTemplate;
    @Value("${sns.cart-writer-cqrs-topic.arn}")
    private String topicArn;

    public CartWriterCommandPublishNotification(SnsTemplate snsTemplate) {
        this.snsTemplate = snsTemplate;
    }

    @Override
    public void execute(CartWriterCommand command) {
        try {
            snsTemplate.convertAndSend(topicArn, command);
        } catch (Exception e) {
            logger.error("[{}] {}", layer, ConstantMessages.CART_WRITER_COMMAND_NOTIFICATION_ERROR.formatted(command.toString()), e);
            throw new CartWriterCommandPublishNotificationException(command);
        }
    }
}
