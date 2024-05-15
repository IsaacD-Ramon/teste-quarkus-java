package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.CartaoDTO;
import org.acme.dto.ContaDTO;
import org.acme.entity.CartaoEntity;
import org.acme.repository.CartaoRepository;
import org.acme.service.exceptions.ObjectNotFoundException;


@ApplicationScoped
public class CartaoService {

    @Inject
    private CartaoRepository cartaoRepository;

    public void cadastrarCartao(CartaoDTO cartaoDTO){
        cartaoRepository.cadastrarCartao(mapCartaoDtoToEntity(cartaoDTO));
    }

    public CartaoDTO buscarCartaoId(Long id){
        CartaoEntity cartao = cartaoRepository.buscarCartaoId(id);
        if(cartao.getId() == null) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrado id: " + id + " tipo: " + CartaoDTO.class.getName());
        }
        return mapContaEntityToDto(cartao);
    }

    public void editarCartao(CartaoDTO cartaoDTO, Long id){
        try{
           cartaoRepository.editarCartao(cartaoDTO, id);
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado id: " + id + " tipo: " + ContaDTO.class.getName());
        }
    }

    public void alterarLimite(Float limite, Long id){
        try{
            CartaoEntity cartaoEntity = cartaoRepository.buscarCartaoId(id);
            limite += cartaoEntity.getLimite();
            cartaoRepository.alterarLimite(limite,id);
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado id: " + id + " tipo: " + ContaDTO.class.getName());
        }
    }

    private CartaoEntity mapCartaoDtoToEntity(CartaoDTO cartaoDTO){
       CartaoEntity cartao = new CartaoEntity();
       cartao.setNumero(cartaoDTO.getNumero());
       cartao.setValidade(cartaoDTO.getValidade());
       cartao.setNomeImpreso(cartaoDTO.getNomeImpreso());
       cartao.setLimite(cartaoDTO.getLimite());
       cartao.setBandeira(cartaoDTO.getBandeira());
       cartao.setContaId(cartaoDTO.getContaId());
        return cartao;
    }

    private CartaoDTO mapContaEntityToDto(CartaoEntity cartaoEntity){
       CartaoDTO cartao = new CartaoDTO();
        cartao.setNumero(cartaoEntity.getNumero());
        cartao.setValidade(cartaoEntity.getValidade());
        cartao.setNomeImpreso(cartaoEntity.getNomeImpreso());
        cartao.setLimite(cartaoEntity.getLimite());
        cartao.setBandeira(cartaoEntity.getBandeira());
        cartao.setContaId(cartaoEntity.getContaId());
        return cartao;
    }



}
