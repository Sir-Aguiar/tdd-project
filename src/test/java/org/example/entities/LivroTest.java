package org.example.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.enums.StatusLivro;
import org.example.exceptions.LivroIndisponivelException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LivroTest {

    @Test
    @DisplayName("Deve emprestar livro disponível com cópias")
    void deveEmprestarLivroDisponivelComCopias() {
        Livro livro = new Livro("Clean Code", 2, StatusLivro.DISPONIVEL);

        livro.emprestar();

        assertEquals(1, livro.getCopiasDisponiveis());
        assertEquals(StatusLivro.DISPONIVEL, livro.getStatus());
    }

    @Test
    @DisplayName("Deve lançar exceção ao emprestar livro sem cópias")
    void deveLancarExcecaoAoEmprestarLivroSemCopias() {
        Livro livro = new Livro("Refactoring", 0);

        assertThrows(LivroIndisponivelException.class, livro::emprestar);
    }

    @Test
    @DisplayName("Deve marcar livro como indisponível quando última cópia é emprestada")
    void deveMarcarLivroComoIndisponivelQuandoUltimaCopiaEmprestada() {
        Livro livro = new Livro("Design Patterns", 1, StatusLivro.DISPONIVEL);

        livro.emprestar();

        assertEquals(0, livro.getCopiasDisponiveis());
        assertEquals(StatusLivro.INDISPONIVEL, livro.getStatus());
    }

    @Test
    @DisplayName("Deve devolver livro e incrementar cópias disponíveis")
    void deveDevolverLivroEIncrementarCopiasDisponiveis() {
        Livro livro = new Livro("Effective Java", 0, StatusLivro.INDISPONIVEL);

        livro.devolver();

        assertEquals(1, livro.getCopiasDisponiveis());
        assertEquals(StatusLivro.DISPONIVEL, livro.getStatus());
    }
}
