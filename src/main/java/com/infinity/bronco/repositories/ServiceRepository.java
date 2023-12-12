package com.infinity.bronco.repositories;

import com.infinity.bronco.models.Servicey;
import com.infinity.bronco.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Servicey,Integer> {
    List<Servicey> findByEstado(int estado);

 List<Servicey> findAllByAssignedMaintenanceUser(User assignedMaintenanceUser);

}
