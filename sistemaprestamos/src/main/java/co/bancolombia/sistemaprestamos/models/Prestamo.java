package co.bancolombia.sistemaprestamos.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    private Long idPrestamo;
    private BigDecimal monto;
    @Column(name = "tasa_interes")
    private BigDecimal tasaInteres;
    @Column(name = "id_estado")
    private Integer Idestado;
    private Integer plazo;

    // se hace referencia al campo que hace relación al id cliente en la tabla de prestamos en DB
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    // mappedBy hace referencia al campo de la entidad transacción prestamo en java
    @OneToMany(mappedBy = "prestamo")
    private List<Transaccion> transaciones;
  

    public Prestamo() {
    }


    public Prestamo(BigDecimal monto, BigDecimal tasaInteres, Integer idestado, Integer plazo,
            Cliente cliente) {
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        Idestado = idestado;
        this.plazo = plazo;
        this.cliente = cliente;
    }


    public Long getIdPrestamo() {
        return idPrestamo;
    }
    public void setIdPrestamo(Long idPrestamo) {
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
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Integer getIdestado() {
        return Idestado;
    }
    public void setIdestado(Integer idestado) {
        Idestado = idestado;
    }


    @Override
    public String toString() {
        return "Prestamo [idPrestamo=" + idPrestamo + ", monto=" + monto + ", tasaInteres=" + tasaInteres
                + ", Idestado=" + Idestado + ", plazo=" + plazo + ", cliente=" + cliente + "]";
    }

    

    


}
