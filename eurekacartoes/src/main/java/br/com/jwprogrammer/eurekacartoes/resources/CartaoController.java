package br.com.jwprogrammer.eurekacartoes.resources;

import br.com.jwprogrammer.eurekacartoes.domain.Cartao;
import br.com.jwprogrammer.eurekacartoes.domain.CartaoCliente;
import br.com.jwprogrammer.eurekacartoes.dto.CartaoDTO;
import br.com.jwprogrammer.eurekacartoes.dto.CartoesPorClienteDTO;
import br.com.jwprogrammer.eurekacartoes.services.CartaoClienteService;
import br.com.jwprogrammer.eurekacartoes.services.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
public class CartaoController {

    @Autowired
    private CartaoService service;

    @Autowired
    private CartaoClienteService cartaoClienteService;

    @GetMapping
    public String status() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoDTO request){
        Cartao cartao = request.toModel();
        service.save(cartao);

        return ResponseEntity.status(201).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        List<Cartao> cartoesRendaMenorIgual = service.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(cartoesRendaMenorIgual);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteDTO>> getCartoesAprovados(@RequestParam("cpf") String cpf) {
        List<CartaoCliente> listCartaoCliente = cartaoClienteService.findByCpf(cpf);
        List<CartoesPorClienteDTO> listaDTO = listCartaoCliente.stream()
                .map(CartoesPorClienteDTO::fromModel)
                .collect(Collectors.toList());
        return  ResponseEntity.ok(listaDTO);
    }
}
