package br.com.jwprogrammer.clienteseureka.dto;

import br.com.jwprogrammer.clienteseureka.domain.Cliente;
import lombok.Data;

@Data
public class ClienteDTO {
    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel(){
        return new Cliente(cpf, nome, idade);
    }
}
