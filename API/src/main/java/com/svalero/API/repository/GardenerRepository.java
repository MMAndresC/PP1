package com.svalero.API.repository;

import com.svalero.API.domain.Gardener;

import java.util.List;

public interface GardenerRepository {
    List<Gardener> findAll();
    Gardener findByName(String name);
}
