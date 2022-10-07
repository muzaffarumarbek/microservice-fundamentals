package com.epam.resourceprocessor.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table("song_info")
public class SongInfo {
    @Id
    private Long id;
    private String name;
    private String artist;
    private String album;
    private String songLength;
    private Integer resourceId;
    private Integer year;

    public SongInfo(String name, String artist, String album, String songLength, Integer resourceId, Integer year) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.songLength = songLength;
        this.resourceId = resourceId;
        this.year = year;
    }
}
