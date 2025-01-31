package com.svalero.API.service;

import com.svalero.API.domain.Client;
import com.svalero.API.domain.dto.ClientDto;
import com.svalero.API.exceptions.ClientNotFoundException;
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

    public Client getById(long id) throws ClientNotFoundException {
        Client client = this.clientRepository.findById(id);
        if(client == null)
            throw new ClientNotFoundException("Client with ID " + id + " not found");
        return this.clientRepository.findById(id);
    }

    public Client add(ClientDto newClient) {
        Client client = modelMapper.map(newClient, Client.class);
        return this.clientRepository.save(client);
    }

    public int remove(long id) throws ClientNotFoundException {
        Client client = this.clientRepository.findById(id);
        if(client == null)
            throw new ClientNotFoundException("Client with ID " + id + " not found");
        this.clientRepository.deleteById(id);
        return 1;
    }

    public Client modify(long id, ClientDto updatedClient) throws ClientNotFoundException {
        Client client = this.clientRepository.findById(id);
        if(client == null)
            throw new ClientNotFoundException("Client with ID " + id + " not found");
        modelMapper.map(updatedClient, client);
        return this.clientRepository.save(client);
    }
}
