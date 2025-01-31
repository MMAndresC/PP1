package com.svalero.API.controller;

import com.svalero.API.domain.Gardener;
import com.svalero.API.domain.dto.GardenerDto;
import com.svalero.API.exceptions.GardenerNotFoundException;
import com.svalero.API.service.GardenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class GardenerController {
    private final Logger logger = LoggerFactory.getLogger(GardenerController.class);
    @Autowired
    private GardenerService gardenerService;
    @GetMapping("/gardeners")
    public ResponseEntity<List<Gardener>> getAll(){
        List<Gardener> allGardeners = this.gardenerService.getAll();
        logger.info("Get all gardeners");
        return new ResponseEntity<>(allGardeners, HttpStatus.OK);
    }

    @GetMapping("/gardeners/{id}")
    public ResponseEntity<Gardener> getGardener(@PathVariable long id) throws GardenerNotFoundException {
        Gardener gardener = this.gardenerService.getById(id);
        logger.info("Get gardener");
        return new ResponseEntity<>(gardener, HttpStatus.FOUND);
    }

    @PostMapping("/gardeners")
    public ResponseEntity<Gardener> addGardener(@RequestBody GardenerDto newGardener){
        Gardener gardener = this.gardenerService.add(newGardener);
        logger.info("Gardener added");
        return new ResponseEntity<>(gardener, HttpStatus.CREATED);
    }

    @PutMapping("/gardeners/{id}")
    public ResponseEntity<Gardener> addGardener(@PathVariable long id, @RequestBody GardenerDto newGardener) throws GardenerNotFoundException {
        Gardener gardener = this.gardenerService.modify(id, newGardener);
        logger.info("Modified gardener");
        return new ResponseEntity<>(gardener, HttpStatus.OK);
    }

    @DeleteMapping("/gardeners/{id}")
    public ResponseEntity<Integer> removeGardener(@PathVariable long id) throws GardenerNotFoundException {
        this.gardenerService.remove(id);
        logger.info("Removed gardener");
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
