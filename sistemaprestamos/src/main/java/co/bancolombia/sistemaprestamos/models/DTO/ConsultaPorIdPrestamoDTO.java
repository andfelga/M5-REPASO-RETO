package co.bancolombia.sistemaprestamos.models.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ConsultaPorIdPrestamoDTO {

    @NotNull(message = "Debe ingresar el id del prestamo!")
    @Positive(message = "El valor debe ser mayor a cero!")
    private Long idPrestamo;
    

    public ConsultaPorIdPrestamoDTO() {
    }

    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
    
}
