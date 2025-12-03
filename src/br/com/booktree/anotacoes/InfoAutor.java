package br.com.booktree.anotacoes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Anotacao personalizada usada em varias classes do sistema
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoAutor {
    String nome();
    String data();
}
