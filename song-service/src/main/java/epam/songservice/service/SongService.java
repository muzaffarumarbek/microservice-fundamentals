package epam.songservice.service;

import epam.songservice.domain.SongInfo;
import epam.songservice.repository.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Slf4j
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Mono<SongInfo> saveSongInfo(SongInfo songInfo) {
        log.info("Saving resource file: '{}'", songInfo);
        songRepository.save(Objects.requireNonNull(songInfo));
        log.info("Item info '{}' saved successfully", songInfo);
        return Mono.just(songRepository.findSongInfoByName(songInfo.getName()));
    }

    public void deleteSongInfo(String name) {
        log.info("Deleting item by name '{}'", name);
        songRepository.deleteByName(name);
    }

    public Flux<SongInfo> findAllISongInfo() {
        log.info("Retrieving all items info");
        return Flux.fromIterable(songRepository.findAll());
    }

    public Mono<SongInfo> getSongInfoByName(String name) {
        log.info("Retrieving item by name '{}'", name);
        SongInfo itemInfo = songRepository.findSongInfoByName(name);
        if (Objects.nonNull(itemInfo)) {
            return Mono.just(songRepository.findSongInfoByName(name));
        }
        return Mono.empty();
    }

}
