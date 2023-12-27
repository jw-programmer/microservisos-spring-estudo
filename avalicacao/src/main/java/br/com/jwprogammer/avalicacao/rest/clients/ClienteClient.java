package br.com.jwprogammer.avalicacao.rest.clients;

import br.com.jwprogammer.avalicacao.domain.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "clientes", path = "/clientes")
public interface ClienteClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<DadosCliente> dadosCliente(@RequestParam("cpf") String cpf);

}
