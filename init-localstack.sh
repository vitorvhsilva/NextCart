#!/bin/bash

awslocal sns create-topic --name cart-writer-command-topic --region us-east-1

awslocal sqs create-queue --queue-name cart-writer-command-queue --region us-east-1

awslocal sns subscribe \
  --topic-arn "arn:aws:sns:us-east-1:000000000000:cart-writer-command-topic" \
  --protocol sqs \
  --notification-endpoint "arn:aws:sqs:us-east-1:000000000000:cart-writer-command-queue" \
  --region us-east-1

awslocal dynamodb create-table \
  --table-name tb_cart_events_writer \
  --attribute-definitions \
    AttributeName=event_id,AttributeType=S \
  --key-schema \
    AttributeName=event_id,KeyType=HASH \
  --billing-mode PAY_PER_REQUEST \
  --region us-east-1