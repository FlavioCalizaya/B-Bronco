package com.infinity.bronco.controllers;

import com.infinity.bronco.models.Servicey;
import lombok.AllArgsConstructor;
import com.infinity.bronco.services.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Component
@RestController
@RequestMapping("api/v1/servicess")
@CrossOrigin()
@AllArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;

    @GetMapping
    public ResponseEntity<Iterable<Servicey>> getAllService()   {
        return ResponseEntity.ok(serviceService.getService());
    }

    @PostMapping
    public ResponseEntity<Servicey> createServicey(@RequestBody Servicey servicey) {
        return ResponseEntity.ok(serviceService.saveServicey(servicey));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Servicey>> findById(@PathVariable("id") Integer serviceyId) {
        return ResponseEntity.ok(serviceService.getServiceyById(serviceyId));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Servicey> updateServicey(@PathVariable("id") Integer id, @RequestBody Servicey servicey){
        return ResponseEntity.ok(serviceService.updateServiceysById(id, servicey));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteServicey(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(serviceService.deleteServicey(id));
    }

    @GetMapping(path = "/user/{idUser}")
    public ResponseEntity<Iterable<Servicey>> getAllServiceMant(@PathVariable("idUser") Integer idUser)   {
        return ResponseEntity.ok(serviceService.getServiceMant(idUser));
    }


}
