package co.bancolombia.sistemaprestamos.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "historia_prestamo")
public class Transaccion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_historia")
    private Long idHistoria;

    @ManyToOne
    @JoinColumn(name ="prestamos_id")
    @JsonBackReference
    private Prestamo prestamo;

    @Column(name = "tipo_transaccion")
    private String tipoTransaccion;

    @Column(name ="monto_prestamo")
    private BigDecimal valor;

    @Column(name = "fecha_prestamo")
    private Timestamp fechaPrestamo;


    public Transaccion() {
    }

    public Transaccion(Prestamo prestamo, String tipoTransaccion, BigDecimal valor,
            Timestamp fechaPrestamo) {
        this.prestamo = prestamo;
        this.tipoTransaccion = tipoTransaccion;
        this.valor = valor;
        this.fechaPrestamo = fechaPrestamo;
    }


    public Long getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(Long idHistoria) {
        this.idHistoria = idHistoria;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Timestamp getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Timestamp fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    

}
