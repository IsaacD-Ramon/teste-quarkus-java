package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuickSetupDTO {

    private PessoaDTO pessoaDTO;

    private ContaDTO contaDTO;

    private CartaoDTO cartaoDTO;
}
