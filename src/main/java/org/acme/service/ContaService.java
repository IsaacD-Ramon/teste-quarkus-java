package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.ContaDTO;
import org.acme.dto.PessoaDTO;
import org.acme.entity.ContaEntity;
import org.acme.repository.ContaRepository;
import org.acme.service.exceptions.ObjectNotFoundException;

@ApplicationScoped
public class ContaService {

    @Inject
    private ContaRepository contaRepository;

    public ContaEntity cadastrarConta(ContaDTO contaDTO){
        return  contaRepository.cadastrarConta(mapContaDtoToEntity(contaDTO));
    }

    public ContaDTO buscarContaId(Long id){
        ContaEntity conta = contaRepository.buscarContaId(id);
        if(conta.getId() == null) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrado id: " + id + " tipo: " + ContaDTO.class.getName());
        }
        return mapContaEntityToDto(conta);
    }

    public void editarConta(ContaDTO contaDTO, Long id){
        try{
            contaRepository.editarConta(contaDTO,id);
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado id: " + id + " tipo: " + ContaDTO.class.getName());
        }
    }

    public void alterarSaldo(Float deposito, Long id){
        try{
            ContaEntity conta = contaRepository.buscarContaId(id);
            deposito += conta.getSaldo();
            contaRepository.alterarSaldo(deposito,id);
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado id: " + id + " tipo: " + ContaDTO.class.getName());
        }
    }

    private ContaEntity mapContaDtoToEntity(ContaDTO contaDTO){
        ContaEntity conta = new ContaEntity();
        conta.setConta(contaDTO.getConta());
        conta.setAgencia(contaDTO.getAgencia());
        conta.setSaldo(contaDTO.getSaldo());
        conta.setPessoaId(contaDTO.getPessoaId());
        return conta;
    }

    private ContaDTO mapContaEntityToDto(ContaEntity contaEntity){
       ContaDTO contaDTO = new ContaDTO();
        contaDTO.setConta(contaEntity.getConta());
        contaDTO.setAgencia(contaEntity.getAgencia());
        contaDTO.setSaldo(contaEntity.getSaldo());
        contaDTO.setPessoaId(contaEntity.getPessoaId());
        return contaDTO;
    }

}
