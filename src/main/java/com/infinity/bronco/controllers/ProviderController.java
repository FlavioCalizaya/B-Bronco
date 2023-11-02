package com.infinity.bronco.controllers;

import com.infinity.bronco.models.Provider;
import com.infinity.bronco.response.ResponseHandler;
import com.infinity.bronco.services.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/providers")
@CrossOrigin(origins = "*")
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping
    public ResponseEntity<Object> createProvider(@RequestBody Provider provider) {
        try {
            Provider result = providerService.saveProvider(provider);
            if (result != null)
            {
                return ResponseHandler.generateResponse( HttpStatus.CREATED, "Proveedor creado con Exito!", result);
            }else {
                return ResponseHandler.generateResponse( HttpStatus.BAD_REQUEST, "[Register Error:], Proveedor no registrado", null);
            }

        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long providerId) {
        try {
            Optional<Provider> result = providerService.getProviderById(providerId);
            return ResponseHandler.generateResponse( HttpStatus.OK, "Proveedor encontrado con ID: "+providerId, result);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllProviders() {
        try {
            Iterable<Provider> result = providerService.getProviders();
            if (result != null)
            {
                return ResponseHandler.generateResponse( HttpStatus.OK, "[Exito:], Proveedores encontrados", result);
            }else {
                return ResponseHandler.generateResponse( HttpStatus.BAD_REQUEST, "[Get Error:], Proveedores no encontrados", null);
            }

        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateProvider(@PathVariable("id") Long id, @RequestBody Provider provider){
        try {
            Provider result = providerService.updateProviderById(id, provider);
            if (result != null)
            {
                return ResponseHandler.generateResponse( HttpStatus.OK, "[Exito:], El Proveedor fue actualizado", result);
            }else {
                return ResponseHandler.generateResponse( HttpStatus.NOT_FOUND, "[Update Error:], El Proveedor no fue actualizado", null);
            }

        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteProvider(@PathVariable("id") Long id) {
        try {
            Provider result = providerService.deleteProvider(id);
            if (result != null)
            {
                return ResponseHandler.generateResponse( HttpStatus.OK, "[Exito:], El Proveedor fue eliminado", result);
            }else {
                return ResponseHandler.generateResponse( HttpStatus.NOT_FOUND, "[Delete Error:], El Proveedor no fue eliminado", null);
            }

        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
