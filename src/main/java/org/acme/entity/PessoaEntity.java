package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity_;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class PessoaEntity extends PanacheEntity_ {

    private Long id;

    private String nome;

    private String cpf;

    private String nascimento;

}
