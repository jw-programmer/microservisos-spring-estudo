package br.com.jwprogammer.avalicacao.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoCliente {
    private String nome;
    private String Bandeira;
    private BigDecimal limiteLiberado;
}
