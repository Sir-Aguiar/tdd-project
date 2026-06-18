package org.example.entities;

import java.math.BigDecimal;

import org.example.enums.StatusMulta;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Multa {

    public static final BigDecimal TAXA_POR_DIA = new BigDecimal("2.00");

    final int diasAtraso;
    BigDecimal saldoDevedor;
    StatusMulta status;

    public Multa(int diasAtraso) {
        this.diasAtraso = diasAtraso;
        this.saldoDevedor = calcularValorPorDias(diasAtraso);
        this.status = StatusMulta.PENDENTE;
    }

    public BigDecimal getValorTotal() {
        return calcularValorPorDias(diasAtraso);
    }

    private BigDecimal calcularValorPorDias(int dias) {
        return TAXA_POR_DIA.multiply(BigDecimal.valueOf(dias));
    }

    public void pagar(BigDecimal valor) {
        saldoDevedor = saldoDevedor.subtract(valor);

        if (saldoDevedor.compareTo(BigDecimal.ZERO) <= 0) {
            saldoDevedor = BigDecimal.ZERO;
            status = StatusMulta.PAGA;
        }
    }
}