package br.com.jwprogrammer.eurekacartoes.services;

import br.com.jwprogrammer.eurekacartoes.domain.CartaoCliente;
import br.com.jwprogrammer.eurekacartoes.repositories.CartaoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoClienteService {

    @Autowired
    private CartaoClienteRepository repo;

    public List<CartaoCliente> findByCpf(String cpf) {
        return repo.findByCpf(cpf);
    }
}
