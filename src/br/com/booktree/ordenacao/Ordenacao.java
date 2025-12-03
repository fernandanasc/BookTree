package br.com.booktree.ordenacao;

import java.util.List;

// Interface generica para algoritmos de ordenacao
public interface Ordenacao<T> {
    void ordenar(List<T> lista);
}
