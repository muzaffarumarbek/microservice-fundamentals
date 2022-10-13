package epam.resourceservice.service;

import epam.resourceservice.domain.ResourceFile;
import epam.resourceservice.repository.ResourceFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class ResourceFileService {

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;
    private final ResourceFileRepository resourceFileRepository;
    private RabbitTemplate rabbitTemplate;

    public ResourceFileService(RabbitTemplate rabbitTemplate, ResourceFileRepository resourceFileRepository) {
        this.rabbitTemplate = rabbitTemplate;
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

    public void sendMessage(ResourceFile file) {
        rabbitTemplate.convertAndSend(exchange, routingkey, file);
    }

}
