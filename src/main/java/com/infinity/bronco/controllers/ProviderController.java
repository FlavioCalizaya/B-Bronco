package com.infinity.bronco.controllers;

import com.infinity.bronco.models.Provider;
import com.infinity.bronco.services.ProviderService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
        return ResponseEntity.ok(providerService.saveProvider(provider));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Provider>> findById(@PathVariable("id") Long providerId) {
        return ResponseEntity.ok(providerService.getProviderById(providerId));
    }

    @GetMapping
    public ResponseEntity<Iterable<Provider>> getAllProviders() {
        return ResponseEntity.ok(providerService.getProviders());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable("id") Long id, @RequestBody Provider provider){
        return ResponseEntity.ok(providerService.updateProviderById(id, provider));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Provider> deleteProvider(@PathVariable("id") Long id) {
        return ResponseEntity.ok(providerService.deleteProvider(id));
    }
}
