package com.svalero.API.service;

import com.svalero.API.domain.Gardener;
import com.svalero.API.domain.dto.GardenerDto;
import com.svalero.API.repository.GardenerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenerService {
    @Autowired
    private GardenerRepository gardenerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Gardener> getAll(){
        return this.gardenerRepository.findAll();
    }
    public Gardener getById(long id){
        return this.gardenerRepository.findById(id);
    }

    public Gardener add(GardenerDto newGardener) {
        Gardener gardener = modelMapper.map(newGardener, Gardener.class);
        return this.gardenerRepository.save(gardener);
    }

    public int remove(long id){
        //this.gardenerRepository.findById(id);
        this.gardenerRepository.deleteById(id);
        return 1;
    }

    public Gardener modify(long id, GardenerDto updatedGardener){
        Gardener gardener = this.gardenerRepository.findById(id);
        modelMapper.map(updatedGardener, gardener);
        return this.gardenerRepository.save(gardener);
    }
}
