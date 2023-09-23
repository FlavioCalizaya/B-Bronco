package com.infinity.bronco.controllers;

import com.infinity.bronco.models.Client;
import com.infinity.bronco.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/Clients")
@CrossOrigin()
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.saveClient(client));
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Client>> findById(@PathVariable("id") Long clientId) {
        return ResponseEntity.ok(clientService.getClientById(clientId));
    }

    @GetMapping
    public ResponseEntity<Iterable<Client>> getAllClient() {
        return ResponseEntity.ok(clientService.getClient());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client client){
        return ResponseEntity.ok(clientService.updateClientsById(id, client));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clientService.deleteClient(id));
    }
}
