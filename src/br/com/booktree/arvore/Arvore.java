package br.com.booktree.arvore;

import java.util.List;

public interface Arvore<K extends Comparable<K>, V> {
    void inserir(K chave, V valor);
    V buscar(K chave);
    void remover(K chave);
    List<V> emOrdem(); // retorna lista de valores em ordem das chaves
}
