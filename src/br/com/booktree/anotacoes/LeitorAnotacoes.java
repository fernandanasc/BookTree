package br.com.booktree.anotacoes;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaria para ler anotacoes @InfoAutor usando reflection
 */
public class LeitorAnotacoes {

    /**
     * Busca e imprime as informacoes de @InfoAutor de uma lista de classes
     */
    public static void imprimirInfoAutores(List<Class<?>> classes) {
        System.out.println("=== INFORMACOES DOS AUTORES (via Reflection) ===");
        
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(InfoAutor.class)) {
                InfoAutor info = clazz.getAnnotation(InfoAutor.class);
                System.out.println("Classe: " + clazz.getSimpleName());
                System.out.println("  Autor: " + info.nome());
                System.out.println("  Data: " + info.data());
                System.out.println();
            }
        }
    }

    /**
     * Busca e retorna as classes anotadas com @InfoAutor de uma lista
     */
    public static List<Class<?>> buscarClassesAnotadas(List<Class<?>> classes) {
        List<Class<?>> classesAnotadas = new ArrayList<>();
        
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(InfoAutor.class)) {
                classesAnotadas.add(clazz);
            }
        }
        
        return classesAnotadas;
    }

    /**
     * Busca automaticamente todas as classes anotadas no pacote do projeto
     */
    public static void imprimirTodasInfoAutores() {
        List<Class<?>> classes = new ArrayList<>();
        
        // Adicionar manualmente as classes que sabemos que estão anotadas
        // (Em um projeto real, poderíamos usar bibliotecas como Reflections para busca automática)
        try {
            classes.add(Class.forName("br.com.booktree.dominio.Livro"));
            classes.add(Class.forName("br.com.booktree.repositorio.RepositorioHash"));
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar classes: " + e.getMessage());
        }
        
        imprimirInfoAutores(classes);
    }
}