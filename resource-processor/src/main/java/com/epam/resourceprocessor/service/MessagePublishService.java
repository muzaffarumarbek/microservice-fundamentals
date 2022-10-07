/*
 * Copyright (c) 2021. Dandelion development
 */

package com.epam.resourceprocessor.service;

import com.epam.resourceprocessor.model.event.PublishEvent;

public interface MessagePublishService {

  void publishMessage(PublishEvent publishEvent);
}
