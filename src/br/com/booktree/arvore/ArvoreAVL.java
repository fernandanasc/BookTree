package br.com.booktree.arvore;

import java.util.ArrayList;
import java.util.List;

public class ArvoreAVL<K extends Comparable<K>, V> implements Arvore<K, V> {

    private NoAVL<K, V> raiz;

    @Override
    public void inserir(K chave, V valor) {
        raiz = inserirRec(raiz, chave, valor);
    }

    private NoAVL<K, V> inserirRec(NoAVL<K, V> node, K chave, V valor) {
        if (node == null) {
            return new NoAVL<>(chave, valor);
        }

        int cmp = chave.compareTo(node.chave);
        if (cmp < 0) {
            node.esquerda = inserirRec(node.esquerda, chave, valor);
        } else if (cmp > 0) {
            node.direita = inserirRec(node.direita, chave, valor);
        } else {
            // mesma chave: atualiza o valor
            node.valor = valor;
            return node;
        }

        atualizarAltura(node);
        return balancear(node);
    }

    @Override
    public V buscar(K chave) {
        NoAVL<K, V> n = buscarRec(raiz, chave);
        return n == null ? null : n.valor;
    }

    private NoAVL<K, V> buscarRec(NoAVL<K, V> node, K chave) {
        if (node == null) return null;
        int cmp = chave.compareTo(node.chave);
        if (cmp < 0) return buscarRec(node.esquerda, chave);
        else if (cmp > 0) return buscarRec(node.direita, chave);
        else return node;
    }

    @Override
    public void remover(K chave) {
        raiz = removerRec(raiz, chave);
    }

    private NoAVL<K, V> removerRec(NoAVL<K, V> node, K chave) {
        if (node == null) return null;

        int cmp = chave.compareTo(node.chave);
        if (cmp < 0) {
            node.esquerda = removerRec(node.esquerda, chave);
        } else if (cmp > 0) {
            node.direita = removerRec(node.direita, chave);
        } else {
            // node a remover
            if (node.esquerda == null || node.direita == null) {
                NoAVL<K, V> temp = (node.esquerda != null) ? node.esquerda : node.direita;
                // sem filhos
                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                // dois filhos: substituir pelo sucessor inorder (mínimo na direita)
                NoAVL<K, V> succ = minValueNode(node.direita);
                node.chave = succ.chave;
                node.valor = succ.valor;
                node.direita = removerRec(node.direita, succ.chave);
            }
        }

        if (node == null) return null;

        atualizarAltura(node);
        return balancear(node);
    }

    private NoAVL<K, V> minValueNode(NoAVL<K, V> node) {
        NoAVL<K, V> current = node;
        while (current.esquerda != null) current = current.esquerda;
        return current;
    }

    @Override
    public List<V> emOrdem() {
        List<V> lista = new ArrayList<>();
        inorderRec(raiz, lista);
        return lista;
    }

    private void inorderRec(NoAVL<K, V> node, List<V> lista) {
        if (node == null) return;
        inorderRec(node.esquerda, lista);
        lista.add(node.valor);
        inorderRec(node.direita, lista);
    }

    // ---------- Helpers de AVL (altura, rotacoes, balance) ----------

    private int altura(NoAVL<K, V> n) {
        return n == null ? 0 : n.altura;
    }

    private void atualizarAltura(NoAVL<K, V> n) {
        n.altura = 1 + Math.max(altura(n.esquerda), altura(n.direita));
    }

    private int getBalance(NoAVL<K, V> n) {
        return n == null ? 0 : altura(n.esquerda) - altura(n.direita);
    }

    private NoAVL<K, V> rotateRight(NoAVL<K, V> y) {
        NoAVL<K, V> x = y.esquerda;
        NoAVL<K, V> T2 = x.direita;

        // rotacao
        x.direita = y;
        y.esquerda = T2;

        // atualizar alturas
        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    private NoAVL<K, V> rotateLeft(NoAVL<K, V> x) {
        NoAVL<K, V> y = x.direita;
        NoAVL<K, V> T2 = y.esquerda;

        // rotacao
        y.esquerda = x;
        x.direita = T2;

        // atualizar alturas
        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    private NoAVL<K, V> balancear(NoAVL<K, V> node) {
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && getBalance(node.esquerda) >= 0) {
            return rotateRight(node);
        }

        // Left Right Case
        if (balance > 1 && getBalance(node.esquerda) < 0) {
            node.esquerda = rotateLeft(node.esquerda);
            return rotateRight(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance(node.direita) <= 0) {
            return rotateLeft(node);
        }

        // Right Left Case
        if (balance < -1 && getBalance(node.direita) > 0) {
            node.direita = rotateRight(node.direita);
            return rotateLeft(node);
        }

        // já balanceada
        return node;
    }

    // ---------- Debug / utilitarios opcionais ----------
    /**
     * Retorna a altura da árvore (útil para testes)
     */
    public int alturaArvore() {
        return altura(raiz);
    }

    /**
     * Imprime a árvore - percurso em ordem com chaves (útil para debugging)
     */
    public void imprimirEmOrdemComChaves() {
        imprimirEmOrdemRec(raiz);
        System.out.println();
    }

    private void imprimirEmOrdemRec(NoAVL<K, V> node) {
        if (node == null) return;
        imprimirEmOrdemRec(node.esquerda);
        System.out.print(node.chave + " ");
        imprimirEmOrdemRec(node.direita);
    }
}
