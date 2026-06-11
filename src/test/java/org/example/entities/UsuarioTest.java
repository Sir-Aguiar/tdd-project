package org.example.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.example.enums.StatusUsuario;
import org.example.exceptions.LimiteEmprestimosExcedidoException;
import org.example.exceptions.UsuarioInadimplenteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsuarioTest {

    @Test
    @DisplayName("Deve permitir empréstimo para usuário ativo sem multas")
    void devePermitirEmprestimoParaUsuarioAtivoSemMultas() {
        Usuario usuario = new Usuario("Maria");

        assertEquals(StatusUsuario.ATIVO, usuario.getStatus());
        assertTrue(usuario.getEmprestimosAtivos().isEmpty());
        assertTrue(usuario.getMultasPendentes().isEmpty());
        assertDoesNotThrow(usuario::validarElegibilidadeParaEmprestimo);
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário está inadimplente")
    void deveLancarExcecaoQuandoUsuarioEstaInadimplente() {
        Usuario usuario = new Usuario("João");
        usuario.registrarMulta(new Multa(3));

        assertThrows(UsuarioInadimplenteException.class, usuario::validarElegibilidadeParaEmprestimo);
    }

    @Test
    @DisplayName("Deve lançar exceção ao exceder limite de empréstimos")
    void deveLancarExcecaoAoExcederLimiteDeEmprestimos() {
        Usuario usuario = new Usuario("Pedro");
        LocalDate hoje = LocalDate.of(2026, 6, 1);

        usuario.registrarEmprestimo(Emprestimo.criar(usuario, new Livro("Livro 1", 1), hoje));
        usuario.registrarEmprestimo(Emprestimo.criar(usuario, new Livro("Livro 2", 1), hoje));
        usuario.registrarEmprestimo(Emprestimo.criar(usuario, new Livro("Livro 3", 1), hoje));

        assertThrows(LimiteEmprestimosExcedidoException.class, usuario::validarElegibilidadeParaEmprestimo);
    }

    @Test
    @DisplayName("Deve registrar empréstimo na lista de empréstimos ativos")
    void deveRegistrarEmprestimoNaListaDeEmprestimosAtivos() {
        Usuario usuario = new Usuario("Ana");
        Emprestimo emprestimo = Emprestimo.criar(usuario, new Livro("Clean Code", 1), LocalDate.of(2026, 6, 1));

        usuario.registrarEmprestimo(emprestimo);

        assertEquals(1, usuario.getEmprestimosAtivos().size());
        assertTrue(usuario.getEmprestimosAtivos().contains(emprestimo));
    }

    @Test
    @DisplayName("Deve remover empréstimo da lista ao registrar devolução")
    void deveRemoverEmprestimoDaListaAoRegistrarDevolucao() {
        Usuario usuario = new Usuario("Carlos");
        Emprestimo emprestimo = Emprestimo.criar(usuario, new Livro("Refactoring", 1), LocalDate.of(2026, 6, 1));
        usuario.registrarEmprestimo(emprestimo);

        usuario.registrarDevolucao(emprestimo);

        assertTrue(usuario.getEmprestimosAtivos().isEmpty());
    }
}
