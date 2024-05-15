package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.PessoaDTO;
import org.acme.entity.PessoaEntity;
import org.acme.repository.PessoaRepository;
import org.acme.service.exceptions.ObjectNotFoundException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


@ApplicationScoped
public class PessoaService {

    @Inject
    private PessoaRepository pessoaRepository;

    public PessoaEntity CadastrarPessoa(PessoaDTO pessoaDTO){

        if (verificaIdade(pessoaDTO.getNascimento())) {
            return  pessoaRepository.CadastrarPessoa(mapPessoaDtoToEntity(pessoaDTO));
        }
        throw new IllegalArgumentException("sua idade tem que ser igual ou superior a 18!");

    }

    public PessoaDTO buscarPessoaCpf(String cpf){
        PessoaEntity pessoa = pessoaRepository.buscarPessoaCpf(cpf);
        if(pessoa.getId() == null) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrado cpf: " + cpf + " tipo: " + PessoaDTO.class.getName());
        }
        return mapPessoaEntityToDto(pessoa);
    }

    public void EditarPessoa(PessoaDTO pessoaDTO, Long id){
        try{
            pessoaRepository.EditarPessoa(pessoaDTO,id);

        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado id: " + id + " tipo: " + PessoaDTO.class.getName());
        }
    }

    private PessoaEntity mapPessoaDtoToEntity(PessoaDTO pessoaDTO){
        PessoaEntity pessoa = new PessoaEntity();
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setNascimento(pessoaDTO.getNascimento());
        return pessoa;
    }
    private PessoaDTO mapPessoaEntityToDto(PessoaEntity pessoaEntity){
        PessoaDTO pessoa = new PessoaDTO();
        pessoa.setCpf(pessoaEntity.getCpf());
        pessoa.setNome(pessoaEntity.getNome());
        pessoa.setNascimento(pessoaEntity.getNascimento());
        return pessoa;
    }

    private Boolean verificaIdade(String nascimento){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(nascimento, formatter);

        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(dataNascimento, dataAtual);
        int idade = periodo.getYears();

        return idade >= 18;
    }

}
