package br.com.jwprogrammer.clienteseureka.services;

import br.com.jwprogrammer.clienteseureka.domain.Cliente;
import br.com.jwprogrammer.clienteseureka.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    @Transactional
    public Cliente save(Cliente cliente){
        return repo.save(cliente);
    }

    public Optional<Cliente> getByCpf(String cpf){
        return  repo.findByCpf(cpf);
    }
}
