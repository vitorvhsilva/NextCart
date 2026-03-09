package br.com.cart.writer.application.ports.input;

import software.amazon.awssdk.services.sqs.model.Message;

public interface CartWriterCommandListenerInput {
    void listen(Message message);
}
