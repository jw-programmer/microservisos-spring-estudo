package br.com.jwprogammer.avalicacao.exception;

public class DadosClienteNotFoundException extends Exception{
    public DadosClienteNotFoundException() {
        super("Dados do cliente não encontrados para CPF informado.");
    }
}
