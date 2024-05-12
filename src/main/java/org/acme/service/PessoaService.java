package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.PessoaDTO;
import org.acme.entity.PessoaEntity;
import org.acme.repository.PessoaRepository;

@ApplicationScoped
public class PessoaService {

    @Inject
    private PessoaRepository pessoaRepository;

    public PessoaEntity CadastrarPessoa(PessoaDTO pessoaDTO){

        return null;
    }
}
