package org.acme.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Table(name = "cartao")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
