package com.svalero.API.service;

import com.svalero.API.domain.Gardener;
import com.svalero.API.domain.dto.GardenerDto;
import com.svalero.API.exceptions.GardenerNotFoundException;
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
    public Gardener getById(long id) throws GardenerNotFoundException {
        Gardener gardener = this.gardenerRepository.findById(id);
        if(gardener == null)
            throw new GardenerNotFoundException("Gardener with ID " + id + " not found");
        return gardener;
    }

    public Gardener add(GardenerDto newGardener) {
        Gardener gardener = modelMapper.map(newGardener, Gardener.class);
        return this.gardenerRepository.save(gardener);
    }

    public int remove(long id) throws GardenerNotFoundException {
        Gardener gardener = this.gardenerRepository.findById(id);
        if(gardener == null)
            throw new GardenerNotFoundException("Gardener with ID " + id + " not found");
        this.gardenerRepository.deleteById(id);
        return 1;
    }

    public Gardener modify(long id, GardenerDto updatedGardener) throws GardenerNotFoundException {
        Gardener gardener = this.gardenerRepository.findById(id);
        if(gardener == null)
            throw new GardenerNotFoundException("Gardener with ID " + id + " not found");
        modelMapper.map(updatedGardener, gardener);
        return this.gardenerRepository.save(gardener);
    }
}
