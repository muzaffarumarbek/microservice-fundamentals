package epam.songservice.repository;

import epam.songservice.domain.SongInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SongRepository extends CrudRepository<SongInfo, Long> {

    SongInfo findSongInfoByName(String name);

    void deleteByName(String name);
}
