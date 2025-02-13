package co.bancolombia.sistemaprestamos.models.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ConsultaPorIdClienteDTO {

    @NotNull(message = "Debe ingresar el número de identificación del cliente!")
    @Positive(message = "El valor debe ser mayor a cero!")
    private Long idCliente;

    public ConsultaPorIdClienteDTO() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
