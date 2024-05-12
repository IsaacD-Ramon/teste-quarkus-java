package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class QuickSetupService {

    @Inject
    private PessoaService pessoaService;

    @Inject
    private ContaService contaService;

    @Inject
    private CartaoService cartaoService;


}
