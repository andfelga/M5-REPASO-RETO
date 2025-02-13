package co.bancolombia.sistemaprestamos.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PrestamosPorCliente {
    private long idPrestamo;
    private BigDecimal monto;
    private BigDecimal tasaInteres;
    private Integer plazo;
    private String tipoTransaccion;
    private Timestamp fechatransaccion;


    public PrestamosPorCliente() {
    }

  

    public PrestamosPorCliente(long idPrestamo, BigDecimal monto, BigDecimal tasaInteres, Integer plazo,
            String tipoTransaccion, Timestamp fechatransaccion) {
        this.idPrestamo = idPrestamo;
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.plazo = plazo;
        this.tipoTransaccion = tipoTransaccion;
        this.fechatransaccion = fechatransaccion;
    }


    public long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }


    public Timestamp getFechatransaccion() {
        return fechatransaccion;
    }


    public void setFechatransaccion(Timestamp fechatransaccion) {
        this.fechatransaccion = fechatransaccion;
    }


}
