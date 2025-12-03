package br.com.booktree.arvore;

// Nó da árvore AVL
public class NoAVL<K, V> {
    public K chave;
    public V valor;
    public NoAVL<K, V> esquerda;
    public NoAVL<K, V> direita;
    public int altura;

    public NoAVL(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
        this.altura = 1;
    }
}
