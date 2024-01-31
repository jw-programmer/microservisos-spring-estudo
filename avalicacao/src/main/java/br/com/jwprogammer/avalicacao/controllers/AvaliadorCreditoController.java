package br.com.jwprogammer.avalicacao.controllers;

import br.com.jwprogammer.avalicacao.domain.*;
import br.com.jwprogammer.avalicacao.exception.DadosClienteNotFoundException;
import br.com.jwprogammer.avalicacao.exception.ErroComunicacaoException;
import br.com.jwprogammer.avalicacao.exception.ErroSolicitacaoCartaoException;
import br.com.jwprogammer.avalicacao.services.AvaliacaoCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    @GetMapping
    public String status(){
        return "Ok";
    }

    @Autowired
    private AvaliacaoCreditoService service;

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
        SituacaoCliente situacao = null;
        try {
            situacao = service.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacao);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getHttpError())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados){
        try {
            var avalicaoCliente =service.realizarAvaliacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(avalicaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getHttpError())).body(e.getMessage());
        }
    }

    @PostMapping("solicitacao-cartao")
    public ResponseEntity realizarSolicitacaoCartao(@RequestBody EmissaoCartao dados) {
        try {
            Protocolo protocolo = service
                    .solicitarEmissaoDeCartao(dados);
            return ResponseEntity.ok(protocolo);
        } catch (ErroSolicitacaoCartaoException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
