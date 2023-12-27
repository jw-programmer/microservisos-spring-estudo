package br.com.jwprogammer.avalicacao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AvaliacaoCliente {
    List<CartaoAprovado> cartoes;
}
