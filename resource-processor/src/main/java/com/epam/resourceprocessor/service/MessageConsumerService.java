/*
 * Copyright (c) 2021. Dandelion development
 */

package com.epam.resourceprocessor.service;

import com.epam.resourceprocessor.model.event.PublishEvent;
import io.awspring.cloud.messaging.config.annotation.NotificationMessage;

public interface MessageConsumerService {
  void consumeMessage(@NotificationMessage PublishEvent publishEvent, String publishEventId);
}
