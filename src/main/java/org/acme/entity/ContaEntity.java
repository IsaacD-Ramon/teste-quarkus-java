package org.acme.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContaEntity {

    private Long id;

    private String agencia;

    private Integer conta;

    private Float saldo;

    private  Long pessoaId;
}
