package com.svalero.API.controller;

import com.svalero.API.domain.Client;
import com.svalero.API.domain.dto.ClientDto;
import com.svalero.API.exceptions.ClientNotFoundException;
import com.svalero.API.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    private final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientService clientService;
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAll(){
        List<Client> allClients = this.clientService.getAll();
        logger.info("Get all clients");
        return new ResponseEntity<>(allClients, HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClient(@PathVariable long id) throws ClientNotFoundException {
        Client client = this.clientService.getById(id);
        logger.info("Get client");
        return new ResponseEntity<>(client, HttpStatus.FOUND);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@RequestBody ClientDto newClient){
        Client client = this.clientService.add(newClient);
        logger.info("Client added");
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> addClient(@PathVariable long id, @RequestBody ClientDto newClient) throws ClientNotFoundException{
        Client client = this.clientService.modify(id, newClient);
        logger.info("Modified client");
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Integer> removeClient(@PathVariable long id) throws ClientNotFoundException {
        this.clientService.remove(id);
        logger.info("Removed client");
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
