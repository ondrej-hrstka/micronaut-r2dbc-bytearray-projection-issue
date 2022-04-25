package com.example;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface EntityJdbcRepository extends ReactorCrudRepository<Entity, UUID> {
    Flux<Projection> findAllById(UUID id);
}
