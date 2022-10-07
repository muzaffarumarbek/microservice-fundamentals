/*
 * Copyright (c) 2021. Dandelion development
 */

package com.epam.resourceprocessor.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConsumeEventResult {

  SUCCESS("SUCCESS"),
  FAILURE("FAILURE");

  private final String value;
}
