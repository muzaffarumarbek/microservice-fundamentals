package epam.songservice.repository;

import epam.songservice.domain.SongInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SongRepository extends ReactiveCrudRepository<SongInfo, Long> {

    Flux<SongInfo> findSongInfoByName(String name);
}
