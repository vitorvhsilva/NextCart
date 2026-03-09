package br.com.cart.writer.application.ports.input;

import br.com.cart.writer.domain.model.cart.CartWriterCommand;

public interface CartWriterCommandUseCaseInput {
    void execute(CartWriterCommand command);
}
