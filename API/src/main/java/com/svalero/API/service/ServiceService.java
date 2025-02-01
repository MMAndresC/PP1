package com.svalero.API.service;

import com.svalero.API.domain.Service;
import com.svalero.API.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    public List<Service> getAll(){
        return this.serviceRepository.findAll();
    }
}
