package br.com.jwprogrammer.eurekacartoes.repositories;

import br.com.jwprogrammer.eurekacartoes.domain.CartaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoClienteRepository extends JpaRepository<CartaoCliente, Long> {
    List<CartaoCliente> findByCpf(String cpf);
}
