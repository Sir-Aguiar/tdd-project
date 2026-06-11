package org.example.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.enums.StatusUsuario;

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
    }

    public void registrarEmprestimo(Emprestimo emprestimo) {
    }

    public void registrarDevolucao(Emprestimo emprestimo) {
    }

    public void registrarMulta(Multa multa) {
    }

    public void pagarMulta(Multa multa, BigDecimal valor) {
    }
}
