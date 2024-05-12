package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.repository.ContaRepository;

@ApplicationScoped
public class ContaService {

    @Inject
    private ContaRepository contaRepository;
}
