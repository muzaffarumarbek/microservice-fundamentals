/*
 * Copyright (c) 2021. Dandelion development
 */

package com.epam.resourceprocessor.service.impl;

import com.epam.resourceprocessor.model.event.PublishEvent;
import com.epam.resourceprocessor.service.MessageConsumerService;
import com.epam.resourceprocessor.storage.PublishEventStorage;
import io.awspring.cloud.messaging.config.annotation.NotificationMessage;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class MessageConsumerServiceImpl implements MessageConsumerService {

  @Override
  @SqsListener(
      value = "${config.aws.sqs.queue-name}",
      deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void consumeMessage(
          @NotificationMessage PublishEvent publishEvent, String publishEventId) {
    if (Objects.nonNull(publishEvent)) {
      log.info("Publish event consumed: {}", publishEvent);
      PublishEventStorage.addPublishEventToStorage(publishEventId, publishEvent);
    }
  }
}
