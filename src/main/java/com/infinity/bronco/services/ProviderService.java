package com.infinity.bronco.services;

import com.infinity.bronco.models.Provider;
import com.infinity.bronco.repositories.ProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProviderService {


    private final ProviderRepository providerRepository;

    public Iterable<Provider> getProviders() {
        return providerRepository.findAll();
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

        providerRepository.save(updateProvider);

        return providerRepository.save(updateProvider);
    }

    public String deleteProvider(Long id) {
       providerRepository.deleteById(id);
       return "Provider deleted";
    }
}
