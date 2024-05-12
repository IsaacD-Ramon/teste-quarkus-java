package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.acme.entity.ContaEntity;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Data
public class PessoaDTO {

    private String nome;

    private Integer cpf;

    private Date nascimento;

    private List<ContaEntity> conta ;
}
