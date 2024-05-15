package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.CompraDTO;
import org.acme.entity.CartaoEntity;
import org.acme.entity.ContaEntity;
import org.acme.repository.CartaoRepository;
import org.acme.repository.ContaRepository;

@ApplicationScoped
public class CompraService {

    @Inject
    private ContaRepository contaRepository;
    @Inject
    private CartaoRepository cartaoRepository;

    public void comprarCreditoDebito(CompraDTO compraDTO){
        CartaoEntity cartao = cartaoRepository.buscarCartaoNumeroCartao(compraDTO.getNumeroCartao());
        if(compraDTO.getTipoCartao() == "debito"){
            ContaEntity conta = contaRepository.buscarContaId(cartao.getContaId());
            Float saldo = conta.getSaldo() - compraDTO.getValorCompra();
            contaRepository.alterarSaldo(saldo, conta.getId());
        }else{
            Float limite = cartao.getLimite() - compraDTO.getValorCompra();
            cartaoRepository.alterarLimite(limite, cartao.getId());
        }

    }
}
