package br.com.jwprogrammer.eurekacartoes.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmissaoCartao {
    private Long idCartao;
    private String cpf;
    private BigDecimal limiteLiberado;
}