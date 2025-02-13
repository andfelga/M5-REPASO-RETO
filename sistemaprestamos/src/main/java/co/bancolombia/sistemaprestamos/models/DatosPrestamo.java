package co.bancolombia.sistemaprestamos.models;

import java.math.BigDecimal;

public class DatosPrestamo {
    private long idPrestamo;
    private BigDecimal monto;
    private BigDecimal tasaInteres;
    private Integer plazo;
    private long idCliente;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    private String descripcion;

    
    public DatosPrestamo() {
    }

    
    public DatosPrestamo(long idPrestamo, BigDecimal monto, BigDecimal tasaInteres, Integer plazo, long idCliente, 
            String nombre, String telefono, String email, String direccion, String descripcion) {
        this.idPrestamo = idPrestamo;
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.plazo = plazo;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.descripcion = descripcion;
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
    public long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}
