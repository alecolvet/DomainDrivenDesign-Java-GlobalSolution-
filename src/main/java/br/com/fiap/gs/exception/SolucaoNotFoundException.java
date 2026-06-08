package br.com.fiap.gs.exception;

public class SolucaoNotFoundException extends RuntimeException {

    public SolucaoNotFoundException(Long id) {
        super("Solução com ID " + id + " não encontrada.");
    }
}
