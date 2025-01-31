package com.svalero.API.controller;

import com.svalero.API.domain.Gardener;
import com.svalero.API.service.GardenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class GardenerController {
    @Autowired
    private GardenerService gardenerService;
    @GetMapping("/gardeners")
    public List<Gardener> getAll(){
        return this.gardenerService.getAll();
    }
}
