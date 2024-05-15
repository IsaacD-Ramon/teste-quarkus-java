package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Data
public class CartaoDTO {

    private Integer numero;

    private String validade;

    private String nomeImpreso;

    private Float limite;

    private String bandeira;

    private Long contaId;

}
