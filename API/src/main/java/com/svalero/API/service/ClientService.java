package com.svalero.API.service;

import com.svalero.API.domain.Client;
import com.svalero.API.domain.dto.ClientDto;
import com.svalero.API.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Client> getAll(){
        return this.clientRepository.findAll();
    }

    public Client geById(long id){
        return this.clientRepository.findById(id);
    }

    public Client add(ClientDto newClient) {
        Client client = modelMapper.map(newClient, Client.class);
        return this.clientRepository.save(client);
    }

    public int remove(long id){
        //this.clientRepository.findById(id);
        this.clientRepository.deleteById(id);
        return 1;
    }

    public Client modify(long id, ClientDto updatedClient){
        Client client = this.clientRepository.findById(id);
        modelMapper.map(updatedClient, client);
        return this.clientRepository.save(client);
    }
}
