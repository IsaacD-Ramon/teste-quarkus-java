package org.acme.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "conta")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String agencia;

    private Integer conta;

    private Float saldo;

    @ManyToOne
    @JoinColumn(name = "pessoaId")
    private  PessoaEntity pessoa;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<CartaoEntity> cartao;
}
