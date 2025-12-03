package br.com.booktree.repositorio;

// Excecao personalizada para regras de negocio
public class NegocioException extends Exception {
    public NegocioException(String mensagem) {
        super(mensagem);
    }
}