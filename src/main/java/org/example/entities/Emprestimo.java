package org.example.entities;

import java.time.LocalDate;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Emprestimo {

    public static final int PRAZO_DIAS = 14;

    final Usuario usuario;
    final Livro livro;
    final LocalDate dataEmprestimo;
    LocalDate dataPrevistaDevolucao;
    LocalDate dataDevolucao;
    boolean encerrado;

    private Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
    }

    public static Emprestimo criar(Usuario usuario, Livro livro, LocalDate dataEmprestimo) {
        return new Emprestimo(usuario, livro, dataEmprestimo);
    }

    public Optional<Multa> devolver(LocalDate dataDevolucao) {
        return Optional.empty();
    }
}
