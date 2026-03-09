package br.com.cart.writer.application.ports.output;

import br.com.cart.writer.domain.model.cart.CartWriterCommand;

public interface CartWriterCommandPublishNotificationOutput {
    void execute(CartWriterCommand command);
}
