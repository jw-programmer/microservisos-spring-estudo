package br.com.jwprogrammer.eurekacartoes.services;

import br.com.jwprogrammer.eurekacartoes.domain.Cartao;
import br.com.jwprogrammer.eurekacartoes.repositories.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repo;

    @Transactional
    public Cartao save(Cartao cartao){
        return repo.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
        var rendaBigDecimal = BigDecimal.valueOf(renda);
        return repo.findByRendaLessThanEqual(rendaBigDecimal);
    }
}
