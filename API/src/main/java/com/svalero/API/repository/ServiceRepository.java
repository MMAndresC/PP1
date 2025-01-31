package com.svalero.API.repository;

import com.svalero.API.domain.Service;

import java.util.List;

public interface ServiceRepository {
    List<Service> findAll();
    Service findByName(String name);
}
