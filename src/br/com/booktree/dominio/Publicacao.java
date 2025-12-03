package br.com.booktree.dominio;

import br.com.booktree.repositorio.NegocioException;

// Classe base para Livro e Revista
public abstract class Publicacao {
    private String titulo;
    private String autor;
    private int ano;

    public Publicacao(String titulo, String autor, int ano) throws NegocioException {
        setTitulo(titulo);
        setAutor(autor);
        setAno(ano);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) throws NegocioException {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new NegocioException("Titulo nao pode ser vazio.");
        }
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) throws NegocioException {
        if (autor == null || autor.trim().isEmpty()) {
            throw new NegocioException("Autor não pode ser vazio.");
        }
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) throws NegocioException {
        if (ano < 1500) {
            throw new NegocioException("Ano deve ser maior ou igual a 1500.");
        }
        this.ano = ano;
    }

    // polimorfismo ativo
    public abstract String getTipo();

    @Override
    public String toString() {
        return "[" + getTipo() + "] " + titulo + " - " + autor + " (" + ano + ")";
    }
}