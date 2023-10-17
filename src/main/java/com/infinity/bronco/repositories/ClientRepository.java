package com.infinity.bronco.repositories;
import com.infinity.bronco.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findByEstado(int estado);
}
