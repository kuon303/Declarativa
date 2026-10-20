package org.example;

import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Theory;

import java.io.InputStream;
import java.math.BigDecimal;

public class Connection {
    private final Prolog engine = new Prolog();

    public Connection() throws Exception {
        try (InputStream in = getClass().getResourceAsStream("/operaciones.pl")) {
            if (in == null) {
                throw new IllegalStateException("No se encontró operaciones.pl");
            }
            engine.setTheory(new Theory(in));
        }
    }

    // Java consulta el predicado; la suma o resta se ejecuta en Prolog.
    public String consultar(String predicado, BigDecimal numero1, BigDecimal numero2)
            throws Exception {
        if (!predicado.equals("suma") && !predicado.equals("resta")) {
            throw new IllegalArgumentException("Operación no válida");
        }

        String consulta = predicado + "(" + numero1.toPlainString() + ", "
                + numero2.toPlainString() + ", R).";
        SolveInfo info = engine.solve(consulta);
        return info.isSuccess() ? info.getTerm("R").toString()
                : "Sin solución para esa operación";
    }
}
