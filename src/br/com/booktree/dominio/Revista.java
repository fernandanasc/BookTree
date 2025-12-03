package br.com.booktree.dominio;

import br.com.booktree.repositorio.NegocioException;

// Subclasse concreta de Publicacao
public class Revista extends Publicacao {

    private int edicao;

    public Revista(String titulo, String autor, int ano, int edicao) throws NegocioException {
        super(titulo, autor, ano);
        setEdicao(edicao);
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) throws NegocioException {
        if (edicao <= 0) {
            throw new NegocioException("Edicao deve ser maior que zero.");
        }
        this.edicao = edicao;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }
}

