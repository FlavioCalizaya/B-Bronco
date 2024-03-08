package com.infinity.bronco.services;

import com.infinity.bronco.models.Servicey;
import com.infinity.bronco.repositories.ServiceRepository;
import com.infinity.bronco.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;


@Service
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    public Iterable<Servicey> getService() {
        return serviceRepository.findAll();
    }

    public Servicey saveServicey(Servicey servicey) {
        if (serviceRepository.existsById(servicey.getId())) servicey.setId(null);
        if(servicey.getCreatedAtt()==null) servicey.setCreatedAtt(LocalDateTime.now());
        return serviceRepository.save(servicey);
    }

    public Optional<Servicey> getServiceyById(Integer id) {
        return serviceRepository.findById(id);
    }

    public Servicey updateServiceysById(Integer id, Servicey servicey){
        Servicey updateServicey = serviceRepository.findById(id).orElseThrow();

        updateServicey.setClient(servicey.getClient());
        updateServicey.setAmount(servicey.getAmount());
        updateServicey.setServiceType(servicey.getServiceType());
        updateServicey.setDescription(servicey.getDescription());
        updateServicey.setStatusMaintenance(servicey.getStatusMaintenance());
        updateServicey.setDateInit(servicey.getDateInit());
        updateServicey.setDateEnd(servicey.getDateEnd());
        // updateServicey.setAssignedMaintenanceUser(servicey.getAssignedMaintenanceUser());

        serviceRepository.save(updateServicey);

        return serviceRepository.save(updateServicey);
    }

    public Servicey deleteServicey(Integer id) {
        Servicey serviceyToDelete = serviceRepository.findById(id).get();
        serviceRepository.deleteById(id);
        return serviceyToDelete;
    }

    public Iterable<Servicey> getAllServiceMant(Integer assignedMaintenanceUser) {
            //User assignerUser = userRepository.getReferenceById(assignedMaintenanceUser);//revizar x que no junciona
        System.out.printf("user repo"+userRepository.getReferenceById(assignedMaintenanceUser).getNameUser());
        return serviceRepository.findAllByAssignedMaintenanceUser(userRepository.getReferenceById(assignedMaintenanceUser));
    }

}
