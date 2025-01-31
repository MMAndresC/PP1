package com.svalero.API.controller;

import com.svalero.API.domain.Service;
import com.svalero.API.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @GetMapping("/services")
    public List<Service> getAll(){
        return this.serviceService.getAll();
    }
}
