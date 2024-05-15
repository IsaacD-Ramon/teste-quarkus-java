package org.acme.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartaoEntity {


    private Long id;

    private Integer numero;

    private String validade;

    private String nomeImpreso;

    private Float limite;

    private String bandeira;

    private Long contaId;
}
