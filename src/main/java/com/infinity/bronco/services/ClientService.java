package com.infinity.bronco.services;

import com.infinity.bronco.models.Client;
import com.infinity.bronco.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    public Iterable<Client> getClient() {
        return clientRepository.findAll();
    }
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }
    public Client saveClient(Client client) {
        if (clientRepository.existsById(client.getId())) {
            client.setId(null);
        }
        return clientRepository.save(client);
    }
    public Client  updateClientsById(Long id, Client client){
        Client updateClient = clientRepository.findById(id)
                .orElseThrow();

        updateClient.setNitCi(client.getNitCi());
        updateClient.setBusinessName(client.getBusinessName());
        updateClient.setPhoneNumber(client.getPhoneNumber());
        updateClient.setEstado(client.getEstado());
        clientRepository.save(updateClient);

        return clientRepository.save(updateClient);
    }
    public String deleteClient(Long id) {
        clientRepository.deleteById(id);
       return "Client deleted";
    }
}
