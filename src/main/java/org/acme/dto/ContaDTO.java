package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Data
public class ContaDTO {

    private String agencia;

    private Integer conta;

    private Float saldo;

    private Long pessoaId;

}
