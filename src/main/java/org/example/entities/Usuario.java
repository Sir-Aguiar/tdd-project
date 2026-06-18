package org.example.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.enums.StatusUsuario;
import org.example.exceptions.LimiteEmprestimosExcedidoException;
import org.example.exceptions.UsuarioInadimplenteException;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Usuario {

    public static final int LIMITE_EMPRESTIMOS = 3;

    final String nome;
    StatusUsuario status;

    @Getter(AccessLevel.NONE)
    final List<Emprestimo> emprestimosAtivos;

    @Getter(AccessLevel.NONE)
    final List<Multa> multasPendentes;

    public Usuario(String nome) {
        this.nome = nome;
        this.status = StatusUsuario.ATIVO;
        this.emprestimosAtivos = new ArrayList<>();
        this.multasPendentes = new ArrayList<>();
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        return List.copyOf(emprestimosAtivos);
    }

    public List<Multa> getMultasPendentes() {
        return List.copyOf(multasPendentes);
    }

    public void validarElegibilidadeParaEmprestimo() {
        validarInadimplencia();
        validarLimiteEmprestimos();
    }

    private void validarInadimplencia() {
        if (status == StatusUsuario.INADIMPLENTE || !multasPendentes.isEmpty()) {
            throw new UsuarioInadimplenteException("Usuário inadimplente não pode realizar novos empréstimos");
        }
    }

    private void validarLimiteEmprestimos() {
        if (emprestimosAtivos.size() >= LIMITE_EMPRESTIMOS) {
            throw new LimiteEmprestimosExcedidoException(
                    "Usuário não pode ter mais de " + LIMITE_EMPRESTIMOS + " livros emprestados simultaneamente");
        }
    }

    public void registrarEmprestimo(Emprestimo emprestimo) {
        validarElegibilidadeParaEmprestimo();
        emprestimosAtivos.add(emprestimo);
    }

    public void registrarDevolucao(Emprestimo emprestimo) {
        emprestimosAtivos.remove(emprestimo);
    }

    public void registrarMulta(Multa multa) {
        multasPendentes.add(multa);
        status = StatusUsuario.INADIMPLENTE;
    }

    public void pagarMulta(Multa multa, BigDecimal valor) {
    }
}
