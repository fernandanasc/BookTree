package br.com.booktree.repositorio;

import br.com.booktree.anotacoes.InfoAutor;
import java.util.*;
import java.util.function.Function;

@InfoAutor(nome = "Fernanda Nascimento", data = "02/12/2025")
public class RepositorioHash<T> implements Repositorio<T> {

    private final Map<String, T> mapa;
    private final Function<T, String> extratorDeChave;

    public RepositorioHash(Function<T, String> extratorDeChave) {
        this.mapa = new HashMap<>();
        this.extratorDeChave = extratorDeChave;
    }

    @Override
    public void inserir(T obj) {
        String chave = extratorDeChave.apply(obj);
        mapa.put(chave, obj);
    }

    @Override
    public T buscar(String chave) {
        return mapa.get(chave);
    }

    @Override
    public boolean remover(String chave) {
        return mapa.remove(chave) != null;
    }

    @Override
    public List<T> listar() {
        return new ArrayList<>(mapa.values());
    }
}
