package br.com.cart.writer.adapters.input.messaging.sqs.listeners;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.model.Message;

import static io.awspring.cloud.sqs.annotation.SqsListenerAcknowledgementMode.ON_SUCCESS;

@Component
public class CartWriterCommandListener {

    @SqsListener(
            value = "${sqs.cart-writer-command-queue.name}",
            acknowledgementMode = ON_SUCCESS
    )
    public void listen(Message message) {
        System.out.println("Received message: " + message.body());
    }
}
