package br.com.cart.writer.application.usecases;

import br.com.cart.writer.application.ports.input.CartWriterCommandUseCaseInput;
import br.com.cart.writer.application.ports.output.CartWriterCommandPublishNotificationOutput;
import br.com.cart.writer.application.ports.output.CartWriterCommandPersistEventOutput;
import br.com.cart.writer.domain.model.cart.CartWriterCommand;

public class CartWriterCommandUseCase implements CartWriterCommandUseCaseInput {

    private final CartWriterCommandPersistEventOutput writeEventOutput;
    private final CartWriterCommandPublishNotificationOutput publishMessageOutput;

    public CartWriterCommandUseCase(CartWriterCommandPersistEventOutput writeEventOutput, CartWriterCommandPublishNotificationOutput publishMessageOutput) {
        this.writeEventOutput = writeEventOutput;
        this.publishMessageOutput = publishMessageOutput;
    }

    @Override
    public void execute(CartWriterCommand command) {
        writeEventOutput.execute(command);
        publishMessageOutput.execute(command);
    }
}
