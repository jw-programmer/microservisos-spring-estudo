package br.com.jwprogrammer.clienteseureka.resouces;

import br.com.jwprogrammer.clienteseureka.domain.Cliente;
import br.com.jwprogrammer.clienteseureka.dto.ClienteDTO;
import br.com.jwprogrammer.clienteseureka.services.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService service;

    /**
     * Apenas para teste do uso de caso do Eureka server. Não usara boas práticas no momento.
     * */
    @GetMapping
    public String status(){
        log.info("Obtendo micro-service do cliente");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteDTO client) {
        Cliente cliente = client.toModel();
        service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf) {
        var cliente = service.getByCpf(cpf);
        if(cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
}
    }
