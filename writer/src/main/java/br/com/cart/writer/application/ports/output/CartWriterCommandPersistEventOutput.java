package br.com.cart.writer.application.ports.output;

import br.com.cart.writer.domain.model.cart.CartWriterCommand;

public interface CartWriterCommandPersistEventOutput {
    void execute(CartWriterCommand command);
}
