package com.svalero.API.repository;

import com.svalero.API.domain.Gardener;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GardenerRepository extends CrudRepository<Gardener, Long> {
    List<Gardener> findAll();
    Gardener findById(long id);
}
