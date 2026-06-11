package org.example.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmprestimoTest {

  @Test
  @DisplayName("Deve calcular data prevista de devolução ao criar empréstimo")
  void deveCalcularDataPrevistaDeDevolucaoAoCriarEmprestimo() {
    Usuario usuario = new Usuario("Maria");
    Livro livro = new Livro("Clean Code", 1);
    LocalDate dataEmprestimo = LocalDate.of(2026, 6, 1);

    Emprestimo emprestimo = Emprestimo.criar(usuario, livro, dataEmprestimo);

    assertEquals(LocalDate.of(2026, 6, 15), emprestimo.getDataPrevistaDevolucao());
  }

  @Test
  @DisplayName("Deve devolver no prazo sem gerar multa")
  void deveDevolverNoPrazoSemGerarMulta() {
    Usuario usuario = new Usuario("Pedro");
    Livro livro = new Livro("Refactoring", 1);
    Emprestimo emprestimo = Emprestimo.criar(usuario, livro, LocalDate.of(2026, 6, 1));
    LocalDate dataDevolucao = LocalDate.of(2026, 6, 15);

    var multa = emprestimo.devolver(dataDevolucao);

    assertTrue(multa.isEmpty());
    assertTrue(emprestimo.isEncerrado());
    assertEquals(dataDevolucao, emprestimo.getDataDevolucao());
  }

  @Test
  @DisplayName("Deve gerar multa quando devolução está atrasada")
  void deveGerarMultaQuandoDevolucaoEstaAtrasada() {
    Usuario usuario = new Usuario("Ana");
    Livro livro = new Livro("Design Patterns", 1);
    Emprestimo emprestimo = Emprestimo.criar(usuario, livro, LocalDate.of(2026, 6, 1));

    var multa = emprestimo.devolver(LocalDate.of(2026, 6, 18));

    assertTrue(multa.isPresent());
    assertEquals(3, multa.get().getDiasAtraso());
    assertTrue(emprestimo.isEncerrado());
  }
}
