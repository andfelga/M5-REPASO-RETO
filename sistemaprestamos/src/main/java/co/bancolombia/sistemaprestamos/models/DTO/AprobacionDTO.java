package co.bancolombia.sistemaprestamos.models.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AprobacionDTO {
    @NotNull(message = "Debe ingresar el id del prestamo!")
    @Positive(message = "El valor del id del prestamo debe ser mayor a cero!")
    private Long idPrestamo;


    public AprobacionDTO() {
    }

    public AprobacionDTO(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Long getIdPrestamo() {
        return idPrestamo;
    }
    public void setIdPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }


    

}
