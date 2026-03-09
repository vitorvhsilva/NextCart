package br.com.cart.writer.adapters.output.notification.sns.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.SnsClientBuilder;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.SqsClientBuilder;

import java.net.URI;

@Configuration
public class SnsConfig {

    @Value("${aws.endpoint:http://localhost:4566}")
    private String sqsEndpoint;

    @Value("${aws.region:us-east-1}")
    private String awsRegion;

    @Value("${aws.access-key:test}")
    private String accessKey;

    @Value("${aws.secret-key:test}")
    private String secretKey;

    @Bean
    public SnsClient snsClient() {
        SnsClientBuilder builder = SnsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                ));

        builder.endpointOverride(URI.create(sqsEndpoint));

        return builder.build();
    }
}
