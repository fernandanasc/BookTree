package br.com.booktree.dominio;

import br.com.booktree.anotacoes.InfoAutor;
import br.com.booktree.repositorio.NegocioException;

// Subclasse concreta de Publicacao
@InfoAutor(nome = "Fernanda Nascimento", data = "02/12/2025")
public class Livro extends Publicacao {

    private String isbn;

    public Livro(String titulo, String autor, int ano, String isbn) throws NegocioException {
        super(titulo, autor, ano);
        setIsbn(isbn);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) throws NegocioException {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new NegocioException("ISBN não pode ser vazio.");
        }
        this.isbn = isbn;
    }

    @Override
    public String getTipo() {
        return "Livro";
    }
}