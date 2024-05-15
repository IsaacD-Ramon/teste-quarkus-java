package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.CartaoDTO;
import org.acme.dto.QuickSetupDTO;
import org.acme.entity.CartaoEntity;
import org.acme.entity.ContaEntity;
import org.acme.entity.PessoaEntity;

@ApplicationScoped
public class QuickSetupService {

    @Inject
    private PessoaService pessoaService;

    @Inject
    private ContaService contaService;

    @Inject
    private CartaoService cartaoService;

    public void quickSetup(QuickSetupDTO quickSetupDTO){
        PessoaEntity pessoaEntity = pessoaService.CadastrarPessoa(quickSetupDTO.getPessoaDTO());
        quickSetupDTO.getContaDTO().setPessoaId(pessoaEntity.getId());
        ContaEntity contaEntity = contaService.cadastrarConta(quickSetupDTO.getContaDTO());
        quickSetupDTO.getCartaoDTO().setContaId(contaEntity.getId());
        cartaoService.cadastrarCartao(quickSetupDTO.getCartaoDTO());
    }
}
