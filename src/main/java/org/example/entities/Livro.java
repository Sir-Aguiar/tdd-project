package org.example.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.enums.StatusLivro;
import org.example.exceptions.LivroIndisponivelException;

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
        if (copiasDisponiveis <= 0) {
            throw new LivroIndisponivelException("Não há cópias disponíveis para empréstimo");
        }

        copiasDisponiveis--;
        atualizarStatus();
    }

    public void devolver() {
        copiasDisponiveis++;
        atualizarStatus();
    }

    private void atualizarStatus() {
        status = copiasDisponiveis > 0 ? StatusLivro.DISPONIVEL : StatusLivro.INDISPONIVEL;
    }
}
