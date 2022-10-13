package epam.resourceservice.controller;

import epam.resourceservice.domain.ResourceFile;
import epam.resourceservice.service.ResourceFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/message")
@Slf4j
public class ResourceFilePublisher {

    @Value("${app.message}")
    private String response;

    private ResourceFileService resourceFileService;

    @Autowired
    public ResourceFilePublisher(ResourceFileService resourceFileService) {
        super();
        this.resourceFileService = resourceFileService;
    }

    @PostMapping
    public ResponseEntity<String> publishMessage(@RequestBody ResourceFile file) {
        log.info("Publishing...");
        resourceFileService.sendMessage(file);
        log.info("Notification stored in queue successfully");
        return ResponseEntity.ok(response);
    }
}
