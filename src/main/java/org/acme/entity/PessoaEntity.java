package org.acme.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pessoa")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PessoaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private Integer cpf;

    private Date nascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<ContaEntity> conta ;

}
