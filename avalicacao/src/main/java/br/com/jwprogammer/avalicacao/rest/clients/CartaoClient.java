package br.com.jwprogammer.avalicacao.rest.clients;

import br.com.jwprogammer.avalicacao.domain.Cartao;
import br.com.jwprogammer.avalicacao.domain.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "cartoes", path = "/cartoes")
public interface CartaoClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoCliente>> getCartoesAprovados(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda);
}
