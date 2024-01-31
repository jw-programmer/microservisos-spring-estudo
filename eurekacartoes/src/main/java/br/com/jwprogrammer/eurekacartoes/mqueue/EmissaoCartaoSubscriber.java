package br.com.jwprogrammer.eurekacartoes.mqueue;

import br.com.jwprogrammer.eurekacartoes.domain.Cartao;
import br.com.jwprogrammer.eurekacartoes.domain.CartaoCliente;
import br.com.jwprogrammer.eurekacartoes.domain.EmissaoCartao;
import br.com.jwprogrammer.eurekacartoes.repositories.CartaoClienteRepository;
import br.com.jwprogrammer.eurekacartoes.repositories.CartaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartaoSubscriber {

    @Autowired
    private CartaoRepository CartaoRepo;

    @Autowired
    private CartaoClienteRepository cartaoClienteRepo;

    @RabbitListener(queues = "${mq.queues.emissao-de-cartao}")
    public void receberSolicitcaoEmissao(@Payload String payload) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            EmissaoCartao emissaoCartao =  mapper.readValue(payload, EmissaoCartao.class);
            Cartao cartao = CartaoRepo.findById(emissaoCartao.getIdCartao()).orElseThrow();
            CartaoCliente cartaoCliente = new CartaoCliente();

            cartaoCliente.setCartao(cartao);
            cartaoCliente.setCpf(emissaoCartao.getCpf());
            cartaoCliente.setLimite(emissaoCartao.getLimiteLiberado());

            cartaoClienteRepo.save(cartaoCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
