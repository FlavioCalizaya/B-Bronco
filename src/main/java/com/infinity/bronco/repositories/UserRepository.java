package com.infinity.bronco.repositories;

import com.infinity.bronco.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Resto de tus importaciones


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEstado(int estado);

    User findByNameUserAndEstado(String nameUser, int estado);
    User findByPassword (String password);
}
