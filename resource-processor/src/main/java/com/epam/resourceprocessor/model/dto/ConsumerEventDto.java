/*
 * Copyright (c) 2021. Dandelion development
 */

package com.epam.resourceprocessor.model.dto;

import com.epam.resourceprocessor.model.event.PublishEvent;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ConsumerEventDto {

    private PublishEvent publishEvent;
    private LocalDateTime consumedAt;
    private ConsumeEventResult consumeEventResult;
}
