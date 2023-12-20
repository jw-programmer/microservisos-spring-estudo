package br.com.jwprogrammer.eurekacartoes.dto;

import br.com.jwprogrammer.eurekacartoes.domain.CartaoCliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoesPorClienteDTO {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartoesPorClienteDTO fromModel(CartaoCliente model) {
        return new CartoesPorClienteDTO(
                model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getCartao().getLimite()
        );
    }
}
