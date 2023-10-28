package com.infinity.bronco.repositories;

import com.infinity.bronco.models.Servicey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Servicey,Integer> {
    List<Servicey> findByEstado(int estado);

    List<Servicey> findByIdAssignedMaintenanceUser(int idAssignedMaintenanceUser);
}
