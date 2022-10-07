/*
 * Copyright (c) 2021. Dandelion development
 */

package com.epam.resourceprocessor.model.event;

import com.epam.resourceprocessor.model.domain.SongInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PublishEvent {

    private String publishEventId;
    private PublishEventType publishEventType;
    private LocalDateTime publishedAt;
    private SongInfo songInfo;
}
