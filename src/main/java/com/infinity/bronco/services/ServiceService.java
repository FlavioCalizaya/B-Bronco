package com.infinity.bronco.services;

import com.infinity.bronco.models.Client;
import com.infinity.bronco.models.Servicey;
import com.infinity.bronco.repositories.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public Iterable<Servicey> getService() {
        return serviceRepository.findAll();
    }

    public Servicey saveServicey(Servicey servicey) {
        if (serviceRepository.existsById(servicey.getId())) servicey.setId(null);
        return serviceRepository.save(servicey);
    }

    public Optional<Servicey> getServiceyById(Integer id) {
        return serviceRepository.findById(id);
    }
    public Servicey updateServiceysById(Integer id, Servicey servicey){
        Servicey updateServicey = serviceRepository.findById(id).orElseThrow();

        updateServicey.setAmount(servicey.getAmount());

        serviceRepository.save(updateServicey);

        return serviceRepository.save(updateServicey);
    }

    public String deleteServicey(Integer id) {
        serviceRepository.deleteById(id);
        return "Servicey deleted";
    }

    public Iterable<Servicey> getServiceMant(Integer idUser) {
        return serviceRepository.findByIdAssignedMaintenanceUser(idUser);
   }


}
