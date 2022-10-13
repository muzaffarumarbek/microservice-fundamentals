
package com.epam.resourceprocessor.service.impl;

import com.epam.resourceprocessor.model.domain.SongInfo;
import com.epam.resourceprocessor.repository.ResourceProcessorRepository;
import com.epam.resourceprocessor.service.ResourceProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResourceProcessorServiceImpl implements ResourceProcessorService {

    private final ResourceProcessorRepository resourceProcessorRepository;

    public ResourceProcessorServiceImpl(ResourceProcessorRepository resourceProcessorRepository) {
        this.resourceProcessorRepository = resourceProcessorRepository;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveMessage(SongInfo info) {
        SongInfo save = resourceProcessorRepository.save(info);
        log.info("persisted" + save);
    }
}
