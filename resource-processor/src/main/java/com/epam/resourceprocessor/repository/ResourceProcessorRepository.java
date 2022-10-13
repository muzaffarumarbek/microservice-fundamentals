package com.epam.resourceprocessor.repository;

import com.epam.resourceprocessor.model.domain.SongInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceProcessorRepository extends CrudRepository<SongInfo, Long> {
}
