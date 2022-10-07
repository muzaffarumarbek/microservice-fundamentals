/*
 * Copyright (c) 2021. Dandelion development
 */

package com.epam.resourceprocessor.storage;

import com.epam.resourceprocessor.model.domain.SongInfo;

import java.util.UUID;

public final class ItemInfoMockStorage {

    private ItemInfoMockStorage() {
    }

    public static SongInfo getMockItemInfo() {
        return new SongInfo()
                .setId(Long.valueOf(UUID.randomUUID().toString()))
                .setName("song-info-mock-name")
                .setArtist("artist")
                .setAlbum("album")
                .setSongLength("3:59")
                .setYear(2022);
    }
}
