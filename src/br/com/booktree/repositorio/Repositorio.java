package br.com.booktree.repositorio;

// Interface genérica para CRUD
public interface Repositorio<T> {

    void inserir(T obj);

    T buscar(String chave);

    boolean remover(String chave);

    java.util.List<T> listar();
}