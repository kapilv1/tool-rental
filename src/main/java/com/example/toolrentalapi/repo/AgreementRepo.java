
package com.example.toolrentalapi.repo;

import com.example.toolrentalapi.model.RentalAgreement;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AgreementRepo {
    private final Map<String, RentalAgreement> store = new LinkedHashMap<>();

    public RentalAgreement save(RentalAgreement a) {
        store.put(a.getId(), a);
        return a;
    }

    public Optional<RentalAgreement> findById(String id) { return Optional.ofNullable(store.get(id)); }
    public Collection<RentalAgreement> findAll() { return store.values(); }
}
