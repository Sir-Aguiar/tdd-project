package org.example.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.enums.StatusLivro;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Livro {

    final String titulo;
    int copiasDisponiveis;
    StatusLivro status;

    public Livro(String titulo, int copiasDisponiveis) {
        this(titulo, copiasDisponiveis, StatusLivro.DISPONIVEL);
    }

    public Livro(String titulo, int copiasDisponiveis, StatusLivro status) {
        this.titulo = titulo;
        this.copiasDisponiveis = copiasDisponiveis;
        this.status = status;
    }

    public void emprestar() {
    }

    public void devolver() {
    }
}
