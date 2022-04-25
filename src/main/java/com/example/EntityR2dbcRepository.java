package com.example;

import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface EntityR2dbcRepository extends ReactorCrudRepository<Entity, UUID> {
    Flux<Projection> findAllById(UUID id);
}
