package org.acme.repository;


import org.acme.dto.CartaoDTO;
import org.acme.entity.CartaoEntity;
import org.acme.entity.ContaEntity;

public interface CartaoRepository {
    void cadastrarCartao(CartaoEntity cartao);

    CartaoEntity buscarCartaoId(Long id);

    void editarCartao(CartaoDTO cartaoDTO, Long id);

    void alterarLimite(Float limite, Long id);

    CartaoEntity buscarCartaoNumeroCartao(int numeroCartao);
}
