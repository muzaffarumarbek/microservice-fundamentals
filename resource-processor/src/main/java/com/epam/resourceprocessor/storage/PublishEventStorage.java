/*
 * Copyright (c) 2021. Dandelion development
 */

package com.epam.resourceprocessor.storage;

import com.epam.resourceprocessor.model.event.PublishEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PublishEventStorage {

  private static final Map<String, PublishEvent> publishEventStorage = new ConcurrentHashMap<>();

  private PublishEventStorage() {}

  public static void addPublishEventToStorage(String publishEventId, PublishEvent publishEvent) {
    publishEventStorage.put(publishEventId, publishEvent);
  }

  public static PublishEvent getAndRemovePublishEventFromStorage(String publishEventId) {
    return publishEventStorage.remove(publishEventId);
  }
}
