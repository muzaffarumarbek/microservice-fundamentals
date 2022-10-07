package epam.songservice.service;

import epam.songservice.domain.SongInfo;
import epam.songservice.repository.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Flux<SongInfo> saveSongInfo(Flux<SongInfo> file) {
        log.info("Saving resource file: '{}'", file);
        return songRepository.saveAll(file);
    }

    public Flux<SongInfo> findAllFiles() {
        log.info("Retrieving all files info");
        return songRepository.findAll();
    }

    public Flux<SongInfo> getFileByName(String fileName) {
        log.info("Retrieving file by name: '{}'", fileName);
        return songRepository.findSongInfoByName(fileName);
    }

}
