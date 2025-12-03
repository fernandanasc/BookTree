package br.com.booktree.ordenacao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Implementacao generica do MergeSort (estrutura inicial)
public class MergeSort<T> implements Ordenacao<T> {

    private Comparator<T> comparator;

    public MergeSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void ordenar(List<T> lista) {
        if (lista.size() <= 1) return;
        mergeSort(lista, 0, lista.size() - 1);
    }

    private void mergeSort(List<T> lista, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(lista, inicio, meio);
            mergeSort(lista, meio + 1, fim);
            merge(lista, inicio, meio, fim);
        }
    }

    private void merge(List<T> lista, int inicio, int meio, int fim) {
        // Criar sublistas temporárias
        List<T> esquerda = new ArrayList<>();
        List<T> direita = new ArrayList<>();

        // Copiar dados para as sublistas
        for (int i = inicio; i <= meio; i++) {
            esquerda.add(lista.get(i));
        }
        for (int i = meio + 1; i <= fim; i++) {
            direita.add(lista.get(i));
        }

        // Indices para mesclar as sublistas
        int i = 0, j = 0, k = inicio;

        // Mesclar as sublistas de volta na lista original
        while (i < esquerda.size() && j < direita.size()) {
            if (comparator.compare(esquerda.get(i), direita.get(j)) <= 0) {
                lista.set(k, esquerda.get(i));
                i++;
            } else {
                lista.set(k, direita.get(j));
                j++;
            }
            k++;
        }

        // Copiar os elementos restantes da esquerda (se houver)
        while (i < esquerda.size()) {
            lista.set(k, esquerda.get(i));
            i++;
            k++;
        }

        // Copiar os elementos restantes da direita (se houver)
        while (j < direita.size()) {
            lista.set(k, direita.get(j));
            j++;
            k++;
        }
    }
}
