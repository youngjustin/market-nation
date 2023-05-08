package com.youngjustin.marketnation.codechallenge.repository;

import com.youngjustin.marketnation.codechallenge.entity.InputNumberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * CRUD Repository for InputNumberEntity.
 */
@Repository
public interface InputNumberRepository extends CrudRepository<InputNumberEntity, Integer> {}
