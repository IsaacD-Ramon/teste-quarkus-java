package org.acme.repository;

import org.acme.dto.PessoaDTO;
import org.acme.entity.PessoaEntity;


public interface PessoaRepository {
    PessoaEntity CadastrarPessoa(PessoaEntity pessoa);
    PessoaEntity buscarPessoaCpf(String cpf);
    void EditarPessoa(PessoaDTO pessoa, Long id);
}
