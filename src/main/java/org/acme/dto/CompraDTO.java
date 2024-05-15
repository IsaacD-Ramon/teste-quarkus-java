package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Data
public class CompraDTO {

    private Float valorCompra;
    private String tipoCartao;
    private int    numeroCartao;
}
