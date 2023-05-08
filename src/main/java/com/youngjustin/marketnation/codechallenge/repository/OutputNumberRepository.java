package com.youngjustin.marketnation.codechallenge.repository;

import com.youngjustin.marketnation.codechallenge.entity.OutputNumberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * CRUD Repository for OutputNumberEntity.
 */
@Repository
public interface OutputNumberRepository extends CrudRepository<OutputNumberEntity, Integer> {}
