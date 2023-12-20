package br.com.jwprogrammer.eurekacartoes.repositories;

import br.com.jwprogrammer.eurekacartoes.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    public List<Cartao> findByRendaLessThanEqual(BigDecimal rendaBigDecimal);
}
