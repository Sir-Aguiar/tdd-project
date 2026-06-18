package org.example.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.example.enums.StatusMulta;
import org.example.enums.StatusUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MultaTest {

    @Test
    @DisplayName("Deve calcular valor total da multa")
    void deveCalcularValorTotalDaMulta() {
        Multa multa = new Multa(5);

        assertEquals(new BigDecimal("10.00"), multa.getValorTotal());
    }

    @Test
    @DisplayName("Deve pagar multa integralmente e alterar status")
    void devePagarMultaIntegralmenteEAlterarStatus() {
        Multa multa = new Multa(5);

        multa.pagar(new BigDecimal("10.00"));

        assertEquals(StatusMulta.PAGA, multa.getStatus());
        assertEquals(BigDecimal.ZERO, multa.getSaldoDevedor());
    }

    @Test
    @DisplayName("Deve registrar multa pendente e tornar usuário inadimplente")
    void deveRegistrarMultaPendenteETornarUsuarioInadimplente() {
        Usuario usuario = new Usuario("Maria");
        Multa multa = new Multa(5);

        usuario.registrarMulta(multa);

        assertEquals(StatusMulta.PENDENTE, multa.getStatus());
        assertTrue(usuario.getMultasPendentes().contains(multa));
        assertEquals(StatusUsuario.INADIMPLENTE, usuario.getStatus());
    }
}
