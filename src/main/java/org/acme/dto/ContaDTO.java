package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.acme.entity.CartaoEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Data
public class ContaDTO {

    private String agencia;

    private Integer conta;

    private Float saldo;

    private Long pessoaId;

    private List<CartaoEntity> cartao;

}
