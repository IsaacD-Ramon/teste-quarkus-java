package org.acme.repository;

import org.acme.dto.ContaDTO;
import org.acme.entity.ContaEntity;

public interface ContaRepository {

    ContaEntity cadastrarConta(ContaEntity conta);

    ContaEntity buscarContaId(Long id);

    void editarConta(ContaDTO contaDTO, Long id);

    void alterarSaldo(Float deposito, Long id);
}
