package br.com.jwprogrammer.eurekacartoes.dto;

import br.com.jwprogrammer.eurekacartoes.domain.Cartao;
import br.com.jwprogrammer.eurekacartoes.domain.enums.BandeiraCartao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoDTO {
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda, limite);
    }
}
