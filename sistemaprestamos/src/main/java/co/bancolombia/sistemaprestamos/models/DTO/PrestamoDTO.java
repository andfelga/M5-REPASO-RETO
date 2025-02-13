package co.bancolombia.sistemaprestamos.models.DTO;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PrestamoDTO {
    @NotNull(message = "Debe ingresar el número de identificación del cliente!")
    @Positive(message = "El valor debe ser mayor a cero!")
    @Digits(integer = 13, fraction = 2, message = "El monto ebe tener un máximo de 15 digitos con 2 decimales")
    private Long idCliente;

    @NotNull(message = "Debe ingresar el monto del prestamo!")
    @Positive(message = "El valor del prestamo debe ser mayor a cero!")
    private BigDecimal monto;

    @NotNull(message = "Debe ingresar plazo en meses!")
    @Positive(message = "El plazo debe ser mayor a cero!")
    @Range(min =1, max = 72, message = "El plazo no puede superar los 72 meses")
    private Integer plazo;
    
    public PrestamoDTO(Long idCliente, BigDecimal monto, Integer plazo) {
        this.idCliente = idCliente;
        this.monto = monto;
        this.plazo = plazo;
    }
    public Long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    public BigDecimal getMonto() {
        return monto;
    }
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    public Integer getPlazo() {
        return plazo;
    }
    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }
    

}
