package br.com.jwprogammer.avalicacao.services;

import br.com.jwprogammer.avalicacao.domain.*;
import br.com.jwprogammer.avalicacao.exception.DadosClienteNotFoundException;
import br.com.jwprogammer.avalicacao.exception.ErroComunicacaoException;
import br.com.jwprogammer.avalicacao.exception.ErroSolicitacaoCartaoException;
import br.com.jwprogammer.avalicacao.mqueue.SolicitacaoEmissaoCartaoPublisher;
import br.com.jwprogammer.avalicacao.rest.clients.CartaoClient;
import br.com.jwprogammer.avalicacao.rest.clients.ClienteClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliacaoCreditoService {

    @Autowired
    private ClienteClient clientRest;

    @Autowired
    private CartaoClient cartaoRest;

    @Autowired
    private SolicitacaoEmissaoCartaoPublisher publisher;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoException {
        try {
            ResponseEntity<DadosCliente> dadosCliente = clientRest.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> dadosCartao = cartaoRest.getCartoesAprovados(cpf);
            return SituacaoCliente.builder()
                    .cliente(dadosCliente.getBody())
                    .cartoes(dadosCartao.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            var status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }

            throw new ErroComunicacaoException(e.getMessage(), status);
        }
    }

    public AvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ErroComunicacaoException {
        try {
            ResponseEntity<DadosCliente> dadosCliente = clientRest.dadosCliente(cpf);
            ResponseEntity<List<Cartao>> cartoes = cartaoRest.getCartoesRendaAte(renda);

            List<Cartao> listCartoes = cartoes.getBody();

            List<CartaoAprovado> listaCartoesAprovados = listCartoes.stream()
                    .map(cartao -> {
                CartaoAprovado aprovado = new CartaoAprovado();

                BigDecimal limiteBasico = cartao.getLimite();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getBody().getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal valorAprovado = fator.multiply(limiteBasico);

                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(valorAprovado);

                return aprovado;
            })
                    .toList();


             return new AvaliacaoCliente(listaCartoesAprovados);
        } catch (FeignException.FeignClientException e) {
            var status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }

            throw new ErroComunicacaoException(e.getMessage(), status);
        }
    }

    public Protocolo solicitarEmissaoDeCartao(EmissaoCartao dados) {
        try {
            publisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new Protocolo(protocolo);
        }catch (Exception e){
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }
}
