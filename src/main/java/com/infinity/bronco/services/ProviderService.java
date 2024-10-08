package com.infinity.bronco.services;

import com.infinity.bronco.models.Provider;
import com.infinity.bronco.repositories.ProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProviderService {


    private final ProviderRepository providerRepository;

   public Iterable<Provider> getProviders() {
        return providerRepository.findByState(1);
    }

    public Optional<Provider> getProviderById(Long id) {
        return providerRepository.findById(id);
    }

    public Provider saveProvider(Provider provider) {
        if (providerRepository.existsById(provider.getId())) {
            provider.setId(null);
        }
        return providerRepository.save(provider);
    }

    public Provider  updateProviderById(Long id, Provider provider){
        Provider updateProvider = providerRepository.findById(id)
                .orElseThrow();

        updateProvider.setNitCi(provider.getNitCi());
        updateProvider.setAddress(provider.getAddress());
        updateProvider.setBusinessName(provider.getBusinessName());
        updateProvider.setPhoneNumber(provider.getPhoneNumber());
        updateProvider.setUpdatedAt(LocalDateTime.now());

        providerRepository.save(updateProvider);

        return providerRepository.save(updateProvider);
    }

    public Provider deleteProvider(Long id) {
        Provider removeProvider = providerRepository.findById(id)
                .orElseThrow();
        removeProvider.setState((byte) 0);
        providerRepository.save(removeProvider);
        return providerRepository.save(removeProvider);
    }
}
