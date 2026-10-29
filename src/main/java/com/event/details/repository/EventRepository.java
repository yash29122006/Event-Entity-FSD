package com.event.details.repository;

import com.event.details.entity.EventEntity;
import com.event.details.entity.EventPublishEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EventRepository extends JpaRepository<EventPublishEntity, Integer>, JpaSpecificationExecutor<EventPublishEntity> {

    @Override
    @EntityGraph(attributePaths = {"event", "venue", "instruction"})
    List<EventPublishEntity> findAll(Specification<EventPublishEntity> spec);
}
