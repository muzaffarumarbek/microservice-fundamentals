package epam.resourceservice.service;

import epam.resourceservice.domain.ResourceFile;
import epam.resourceservice.repository.ResourceFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class ResourceFileService {

    private final ResourceFileRepository resourceFileRepository;

    public ResourceFileService(ResourceFileRepository resourceFileRepository) {
        this.resourceFileRepository = resourceFileRepository;
    }

    public Flux<ResourceFile> saveResourceFile(Flux<ResourceFile> file) {
        log.info("Saving resource file: '{}'", file);
        return resourceFileRepository.saveAll(file);
    }

    public Flux<ResourceFile> findAllFiles() {
        log.info("Retrieving all files info");
        return resourceFileRepository.findAll();
    }

    public Flux<ResourceFile> getFileByName(String fileName) {
        log.info("Retrieving file by name: '{}'", fileName);
        return resourceFileRepository.findResourceFileByFileName(fileName);
    }

}
