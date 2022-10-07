package epam.resourceservice.repository;

import epam.resourceservice.domain.ResourceFile;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ResourceFileRepository extends ReactiveCrudRepository<ResourceFile, Long> {

    Flux<ResourceFile>findResourceFileByFileName(String name);
}
