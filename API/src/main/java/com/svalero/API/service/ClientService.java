package com.svalero.API.service;

import com.svalero.API.domain.Client;
import com.svalero.API.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return this.clientRepository.findAll();
    }
}
