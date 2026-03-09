package br.com.cart.writer.domain.exceptions;

import br.com.cart.writer.domain.model.cart.CartWriterCommand;

import static br.com.cart.writer.domain.model.base.ConstantMessages.CART_WRITER_COMMAND_PERSIST_ERROR;

public class CartWriterCommandPersistEventException extends RuntimeException {
    public CartWriterCommandPersistEventException(CartWriterCommand command) {
        super(CART_WRITER_COMMAND_PERSIST_ERROR.formatted(command));
    }
}
