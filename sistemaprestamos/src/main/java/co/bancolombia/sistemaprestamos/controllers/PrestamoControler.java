package co.bancolombia.sistemaprestamos.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.bancolombia.sistemaprestamos.models.DatosPrestamo;
import co.bancolombia.sistemaprestamos.models.PlanPagos;
import co.bancolombia.sistemaprestamos.models.Prestamo;
import co.bancolombia.sistemaprestamos.models.PrestamosPorCliente;
import co.bancolombia.sistemaprestamos.models.DTO.AprobacionDTO;
import co.bancolombia.sistemaprestamos.models.DTO.ConsultaPorIdClienteDTO;
import co.bancolombia.sistemaprestamos.models.DTO.PrestamoDTO;
import co.bancolombia.sistemaprestamos.services.TransaccionesPrestamoService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/prestamo")
public class PrestamoControler {

    private final TransaccionesPrestamoService prestamosService;

    public PrestamoControler(TransaccionesPrestamoService prestamosService) {
        this.prestamosService = prestamosService;
    }
    
    @PostMapping("/crear")
    public String CrearPrestamo(@Valid @RequestBody PrestamoDTO prestamoDTO) throws Exception {
       Prestamo nuevoPrestamo = prestamosService.CrearSolicitudPrestamo(prestamoDTO);
      return "Solicitud creada exitosamente\n" + nuevoPrestamo.toString();
        
    }

    @PostMapping("/aprobarprestamo")
    public String AprobarPrestamo(@Valid @RequestBody AprobacionDTO aprobacion) throws Exception {
        Prestamo prestamoaprobado = prestamosService.AprobarPrestamo(aprobacion);
        return "Solicitud de prestamo aprobado\n" + prestamoaprobado.toString();
    }

    @PostMapping("/rechazarprestamo")
    public String rechazarPrestamo(@Valid @RequestBody AprobacionDTO aprobacion) throws Exception {
        Prestamo prestamorechazado = prestamosService.RechazarPrestamo(aprobacion);
        return "Solicitud de prestamo rechazado\n" + prestamorechazado.toString();
    }
    
    @GetMapping("/consultarestado")
     public String postMethodName(@Valid @RequestBody AprobacionDTO aprobacion) throws Exception {
        String estado = prestamosService.ConsultarEstado(aprobacion.getIdPrestamo());
        return "Id del prestamo:" + aprobacion.getIdPrestamo() + "\nEstado del prestamo:" + estado;
    }

    @GetMapping("/consultarplanpagos")
    public ResponseEntity<List<PlanPagos>> consultarPlanPagos(@Valid @RequestBody AprobacionDTO aprobacion) throws Exception {
        List<PlanPagos> planamortizacion = prestamosService.simularPalnPagos(aprobacion);
        return new ResponseEntity<>(planamortizacion, null, 200);
    }

    @GetMapping("/consultarprestamoporidprestamo")
    public ResponseEntity<DatosPrestamo> consultarPrestamo(@Valid @RequestBody AprobacionDTO aprobacion) throws Exception {
        DatosPrestamo prestamos = prestamosService.consultaprestamo(aprobacion.getIdPrestamo());
        return new ResponseEntity<>(prestamos, null, 200);

    }

    @GetMapping("/consultarprestamosporidcliente")
    public ResponseEntity<List<PrestamosPorCliente>> consultarPrestamosCliente(@Valid @RequestBody ConsultaPorIdClienteDTO cliente) throws Exception {
        List<PrestamosPorCliente> prestamos = prestamosService.consultaprestamosCliente(cliente.getIdCliente());
        return new ResponseEntity<>(prestamos, null, 200);
    }   

    
}
