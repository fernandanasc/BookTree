package br.com.booktree.app;

import br.com.booktree.anotacoes.LeitorAnotacoes;
import br.com.booktree.arvore.ArvoreAVL;
import br.com.booktree.dominio.Livro;
import br.com.booktree.dominio.Publicacao;
import br.com.booktree.dominio.Revista;
import br.com.booktree.ordenacao.Comparadores;
import br.com.booktree.ordenacao.MergeSort;
import br.com.booktree.repositorio.NegocioException;
import br.com.booktree.repositorio.RepositorioHash;

import java.util.ArrayList;
import java.util.List;


public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== BOOKTREE - BIBLIOTECA DIGITAL ===\n");
        
        try {
            // 1. CRIAR REPOSITORIO E ARVORE (Interfaces genericas + Classes genericas)
            RepositorioHash<Publicacao> repo = new RepositorioHash<>(Publicacao::getTitulo);
            ArvoreAVL<String, Publicacao> arvore = new ArvoreAVL<>();
            
            // 2. CRIAR PUBLICACOES (Polimorfismo - classe base + 2 subclasses)
            System.out.println("1. ADICIONANDO PUBLICACOES:");
            Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis", 1899, "978-123");
            Livro livro2 = new Livro("O Cortico", "Aluisio Azevedo", 1890, "978-456");
            Revista revista1 = new Revista("Veja", "Editora Abril", 2023, 100);
            Revista revista2 = new Revista("Epoca", "Editora Globo", 2022, 250);
            
            // Adicionar no repositorio e arvore
            adicionarPublicacao(repo, arvore, livro1);
            adicionarPublicacao(repo, arvore, livro2);
            adicionarPublicacao(repo, arvore, revista1);
            adicionarPublicacao(repo, arvore, revista2);
            
            // 3. BUSCAR PUBLICACAO
            System.out.println("\n2. BUSCA NO REPOSITORIO:");
            System.out.println("Buscando publicacao com titulo: 'Dom Casmurro'");
            Publicacao encontrada = repo.buscar("Dom Casmurro");
            System.out.println("Resultado: " + encontrada);
            
            // 4. ARVORE AVL - TRAVESSIA EM ORDEM
            System.out.println("\n3. ARVORE AVL - TRAVESSIA EM ORDEM:");
            System.out.println("Publicacoes ordenadas alfabeticamente por titulo:");
            List<Publicacao> emOrdem = arvore.emOrdem();
            for (Publicacao pub : emOrdem) {
                System.out.println("  " + pub);
            }
            
            // 5. ORDENACAO COM MERGESORT
            System.out.println("\n4. ALGORITMO MERGESORT:");
            
            // Ordenação por ano
            System.out.println("Ordenando publicacoes por ano de publicacao:");
            List<Publicacao> listaAno = new ArrayList<>(repo.listar());
            MergeSort<Publicacao> mergeSortAno = new MergeSort<>(Comparadores.porAno());
            mergeSortAno.ordenar(listaAno);
            for (Publicacao pub : listaAno) {
                System.out.println("  " + pub);
            }
            
            // Ordenação por título
            System.out.println("\nOrdenando publicacoes por titulo:");
            List<Publicacao> listaTitulo = new ArrayList<>(repo.listar());
            MergeSort<Publicacao> mergeSortTitulo = new MergeSort<>(Comparadores.porTitulo());
            mergeSortTitulo.ordenar(listaTitulo);
            for (Publicacao pub : listaTitulo) {
                System.out.println("  " + pub);
            }
            
            // Ordenação por autor
            System.out.println("\nOrdenando publicacoes por autor:");
            List<Publicacao> listaAutor = new ArrayList<>(repo.listar());
            MergeSort<Publicacao> mergeSortAutor = new MergeSort<>(Comparadores.porAutor());
            mergeSortAutor.ordenar(listaAutor);
            for (Publicacao pub : listaAutor) {
                System.out.println("  " + pub);
            }
            
            // 6. REMOVER PUBLICACAO
            System.out.println("\n5. OPERACAO DE REMOCAO:");
            System.out.println("Removendo publicacao: 'O Cortico'");
            boolean removeu = repo.remover("O Cortico");
            arvore.remover("O Cortico");
            System.out.println("Operacao bem-sucedida: " + removeu);
            
            // 7. LISTAR TODAS RESTANTES
            System.out.println("\n6. LISTAGEM APOS REMOCAO:");
            System.out.println("Publicacoes restantes no acervo:");
            for (Publicacao pub : repo.listar()) {
                System.out.println("  " + pub);
            }
            
            // 8. REFLECTION - LER ANOTACOES
            System.out.println("\n7. REFLECTION - LEITURA DE ANOTACOES:");
            System.out.println("Lendo anotacao @InfoAutor das classes em tempo de execucao:");
            LeitorAnotacoes.imprimirTodasInfoAutores();
            
            // 9. TESTE DE EXCECOES DE NEGOCIO
            System.out.println("\n8. TRATAMENTO DE EXCECOES DE NEGOCIO:");
            System.out.println("Testando validacoes das regras de negocio:");
            testarExcecoes();
            
        } catch (NegocioException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void adicionarPublicacao(RepositorioHash<Publicacao> repo, 
                                          ArvoreAVL<String, Publicacao> arvore, 
                                          Publicacao pub) {
        repo.inserir(pub);
        arvore.inserir(pub.getTitulo(), pub);
        System.out.println("  Adicionado: " + pub);
    }
    
    private static void testarExcecoes() {
        try {
            System.out.println("  Testando titulo vazio...");
            new Livro("", "Autor", 2000, "123");
        } catch (NegocioException e) {
            System.out.println("Excecao capturada: " + e.getMessage());
        }
        
        try {
            System.out.println("  Testando ano invalido (1400)...");
            new Livro("Titulo", "Autor", 1400, "123");
        } catch (NegocioException e) {
            System.out.println("Excecao capturada: " + e.getMessage());
        }
        
        try {
            System.out.println("  Testando edicao invalida (-1)...");
            new Revista("Revista", "Autor", 2000, -1);
        } catch (NegocioException e) {
            System.out.println("Excecao capturada: " + e.getMessage());
        }
    }
}