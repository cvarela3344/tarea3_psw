// src/main/java/inf331/jUnit/model/Compra.java

package inf331.jUnit.model;

import java.time.LocalDate;

public class Compra {
    private final String idCompra;
    private final String idCliente;
    private final int monto;
    private final LocalDate fecha;
    private final int puntosOtorgados;

    public Compra(String idCompra, String idCliente, int monto, LocalDate fecha, int puntosOtorgados) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.monto = monto;
        this.fecha = fecha;
        this.puntosOtorgados = puntosOtorgados;
    }

    public String getIdCompra() { return idCompra; }
    public String getIdCliente() { return idCliente; }
    public int getMonto() { return monto; }
    public LocalDate getFecha() { return fecha; }
    public int getPuntosOtorgados() { return puntosOtorgados; }
}
