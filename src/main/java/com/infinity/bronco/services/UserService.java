package com.infinity.bronco.services;
import com.infinity.bronco.models.User;
import com.infinity.bronco.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt; // Importa BCrypt

// ...


import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;




    @Value("${app.upload.dir}")
    private String uploadDir;

    public UserService(UserRepository  userRepository  ) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getUser() {
        return userRepository.findByEstado(1);
    }

    public Optional<User> getUserById(Integer idUser) {
        return userRepository.findById( idUser);
    }

    public User saveUser(User user) {

        if (userRepository.existsById(user.getIdUser())) {
            user.setIdUser(null);

        }
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);


        return userRepository.save(user);
    }

    public User removeUser(Integer id) {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            existingUser.setEstado(0);

            return userRepository.save(existingUser);
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
    }

    public User updateUser(Integer id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {

            User userToUpdate = existingUser.get();
            userToUpdate.setNombre(updatedUser.getNombre());
            userToUpdate.setPrimerApellido(updatedUser.getPrimerApellido());
            userToUpdate.setSegundoApellido(updatedUser.getSegundoApellido());
            userToUpdate.setCi(updatedUser.getCi());
            userToUpdate.setRol(updatedUser.getRol());

            userToUpdate.setNameUser(updatedUser.getNameUser());
            userToUpdate.setPassword(updatedUser.getPassword());

            return userRepository.save(userToUpdate);
        } else {
            throw new EntityNotFoundException("user not found with id: " + id);
        }
    }
}
