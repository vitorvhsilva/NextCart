package br.com.cart.writer.adapters.infra;

import br.com.cart.writer.application.ports.input.CartWriterCommandUseCaseInput;
import br.com.cart.writer.application.ports.output.CartWriterCommandPersistEventOutput;
import br.com.cart.writer.application.ports.output.CartWriterCommandPublishNotificationOutput;
import br.com.cart.writer.application.usecases.CartWriterCommandUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartWriterCommandUseCaseConfig {

    @Bean
    public CartWriterCommandUseCaseInput cartWriterCommandUseCase(
            CartWriterCommandPersistEventOutput writeEventOutput,
            CartWriterCommandPublishNotificationOutput publishMessageOutput) {
        return new CartWriterCommandUseCase(writeEventOutput, publishMessageOutput);
    }
}

