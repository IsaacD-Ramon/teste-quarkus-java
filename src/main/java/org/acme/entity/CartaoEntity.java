package org.acme.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "cartao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartaoEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Integer numero;

    private Date validade;

    private String nomeImpreso;

    private Float limite;

    private String bandeira;

    @ManyToOne
    @JoinColumn(name = "contaId")
    private ContaEntity conta;
}
