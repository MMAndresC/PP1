package com.svalero.API.controller;

import com.svalero.API.domain.Client;
import com.svalero.API.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {
    private final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientService clientService;
    @GetMapping("/clients")
    public List<Client> getAll(){
        List<Client> allClients = this.clientService.getAll();
        this.logger.info("Get all clients");
        return allClients;
    }
}
