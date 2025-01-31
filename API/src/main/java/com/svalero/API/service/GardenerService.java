package com.svalero.API.service;

import com.svalero.API.domain.Gardener;
import com.svalero.API.repository.GardenerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GardenerService {
    @Autowired
    private GardenerRepository gardenerRepository;

    public List<Gardener> getAll(){
        return this.gardenerRepository.findAll();
    }
}
