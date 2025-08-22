
package com.example.toolrentalapi.controller;

import com.example.toolrentalapi.model.RentalAgreement;
import com.example.toolrentalapi.repo.AgreementRepo;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/agreements")
public class AgreementController {
    private final AgreementRepo repo;
    public AgreementController(AgreementRepo repo) { this.repo = repo; }

    @GetMapping
    public Collection<RentalAgreement> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public RentalAgreement get(@PathVariable("id") String id) { return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Agreement not found: " + id)); }
}
