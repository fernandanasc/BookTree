package br.com.booktree.ordenacao;

import br.com.booktree.dominio.Publicacao;

import java.util.Comparator;

// Classe com comparadores prontos para ordenar Publicacao
public class Comparadores {

    public static Comparator<Publicacao> porAno() {
        return Comparator.comparingInt(Publicacao::getAno);
    }

    public static Comparator<Publicacao> porTitulo() {
        return Comparator.comparing(Publicacao::getTitulo);
    }

    public static Comparator<Publicacao> porAutor() {
        return Comparator.comparing(Publicacao::getAutor);
    }
}
