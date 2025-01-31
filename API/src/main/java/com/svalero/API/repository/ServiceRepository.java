package com.svalero.API.repository;

import com.svalero.API.domain.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, Long> {
    List<Service> findAll();
    Service findById(long id);
}
