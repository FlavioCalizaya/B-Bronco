package com.infinity.bronco.services;

import com.infinity.bronco.models.Servicey;
import com.infinity.bronco.repositories.ServiceRepository;
import com.infinity.bronco.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        return serviceRepository.save(servicey);
    }

    public Optional<Servicey> getServiceyById(Integer id) {
        return serviceRepository.findById(id);
    }

    public Servicey updateServiceysById(Integer id, Servicey servicey){
        Servicey updateServicey = serviceRepository.findById(id).orElseThrow();

        updateServicey.setAmount(servicey.getAmount());
        updateServicey.setServiceType(servicey.getServiceType());
        updateServicey.setDescription(servicey.getDescription());
        updateServicey.setStatusMaintenance(servicey.getStatusMaintenance());
        updateServicey.setDateInit(servicey.getDateInit());
        updateServicey.setDateEnd(servicey.getDateEnd());

        serviceRepository.save(updateServicey);

        return serviceRepository.save(updateServicey);
    }
    public String deleteServicey(Integer id) {
        serviceRepository.deleteById(id);
        return "Servicey deleted";
    }

    public Iterable<Servicey> getAllServiceMant(Integer assignedMaintenanceUser) {
            //User assignerUser = userRepository.getReferenceById(assignedMaintenanceUser);//revizar x que no junciona
        System.out.printf("user repo"+userRepository.getReferenceById(assignedMaintenanceUser).getNameUser());
        return serviceRepository.findAllByAssignedMaintenanceUser(userRepository.getReferenceById(assignedMaintenanceUser));
    }

}
