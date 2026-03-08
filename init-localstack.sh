#!/bin/bash

sleep 5

awslocal dynamodb create-table \
  --table-name tb_cart_events_writer \
  --attribute-definitions \
    AttributeName=event_id,AttributeType=S \
  --key-schema \
    AttributeName=event_id,KeyType=HASH \
  --billing-mode PAY_PER_REQUEST \
  --region us-east-1