package co.bancolombia.sistemaprestamos.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import co.bancolombia.sistemaprestamos.models.Cliente;
import co.bancolombia.sistemaprestamos.models.DatosPrestamo;
import co.bancolombia.sistemaprestamos.models.PlanPagos;
import co.bancolombia.sistemaprestamos.models.Prestamo;
import co.bancolombia.sistemaprestamos.models.PrestamosPorCliente;
import co.bancolombia.sistemaprestamos.models.Transaccion;
import co.bancolombia.sistemaprestamos.models.DTO.AprobacionDTO;
import co.bancolombia.sistemaprestamos.models.DTO.PrestamoDTO;
import co.bancolombia.sistemaprestamos.repository.ClienteRepository;
import co.bancolombia.sistemaprestamos.repository.PrestamoRepository;
import co.bancolombia.sistemaprestamos.repository.TransaccionRepository;

@Service
public class TransaccionesPrestamoService {
    private final TransaccionRepository transaccionRepository;
    private final ClienteRepository clienteRepository;
    private final PrestamoRepository prestamosRepository;

   

    public TransaccionesPrestamoService(TransaccionRepository transaccionRepository,
            ClienteRepository clienteRepository, PrestamoRepository prestamosRepository) {
        this.transaccionRepository = transaccionRepository;
        this.clienteRepository = clienteRepository;
        this.prestamosRepository = prestamosRepository;
    }



    @SuppressWarnings("unused")
    public Prestamo CrearSolicitudPrestamo(PrestamoDTO transaccionPrestamoDTO) throws Exception{
        // se consulta si el cliente existe
        Cliente cliente = clienteRepository.findById(transaccionPrestamoDTO.getIdCliente()).orElseThrow(() ->
                  new NoSuchElementException("Cliente no existe en el sistema"));
  
        Prestamo prestamo = new Prestamo();
        Transaccion transaccion = new Transaccion();

        // Se asigna la entidad Cliente
        prestamo.setCliente(cliente);
        // se asigna datos del prestamo
        prestamo.setIdestado(0);
        prestamo.setMonto(transaccionPrestamoDTO.getMonto());
        prestamo.setPlazo(transaccionPrestamoDTO.getPlazo());
        prestamo.setTasaInteres(new BigDecimal(2.5));
        // se graba en la tabla de prestamo
        Prestamo prestamoResult = prestamosRepository.save(prestamo);

        if (prestamoResult == null) {
            throw new Exception("Se produjo un error generando la solicitud de prestamo"); 
        }
            
        // Se asiga historia de la transacción
        transaccion.setPrestamo(prestamo);
        transaccion.setTipoTransaccion("Solicitud prestamo");
        transaccion.setFechaPrestamo(Timestamp.valueOf(LocalDateTime.now()));
        transaccion.setValor(transaccionPrestamoDTO.getMonto());
        // se graba historia de la transacción 
        Transaccion transaccionResult = transaccionRepository.save(transaccion);

        return prestamoResult;

    }
    @SuppressWarnings("unused")
    public Prestamo AprobarPrestamo(AprobacionDTO aprobacion) throws Exception{
        // consultamos si el prestamos existe
        Prestamo prestamo = prestamosRepository.findById(aprobacion.getIdPrestamo()).orElseThrow(() ->
                 new NoSuchElementException("Solicitud de prestamo no existe en el sistema"));
        
        if(!prestamo.getIdestado().equals(0)){
            throw new Exception("Imposible cambiar el estado del prestamo");
        }

        prestamo.setIdestado(1);
        // se actualiza prestamo
        Prestamo prestamoaprobado = prestamosRepository.save(prestamo);
        // se graba historia
        Transaccion transaccion = new Transaccion();
        transaccion.setPrestamo(prestamoaprobado);
        transaccion.setTipoTransaccion("Aprobación de prestamo");
        transaccion.setFechaPrestamo(Timestamp.valueOf(LocalDateTime.now()));
        transaccion.setValor(prestamoaprobado.getMonto());
        Transaccion transaccionResult = transaccionRepository.save(transaccion);
        
        return prestamoaprobado;

    }

    @SuppressWarnings("unused")
    public Prestamo RechazarPrestamo(AprobacionDTO aprobacion) throws Exception{
        // consultamos si el prestamos existe
        Prestamo prestamo = prestamosRepository.findById(aprobacion.getIdPrestamo()).orElseThrow(() ->
                 new NoSuchElementException("Solicitud de prestamo no existe en el sistema"));
        
        if(!prestamo.getIdestado().equals(0)){
            throw new Exception("Imposible cambiar el estado del prestamo");
        }

        prestamo.setIdestado(2);
        // se actualiza prestamo
        Prestamo prestamorechazado = prestamosRepository.save(prestamo);
        // se graba historia
        Transaccion transaccion = new Transaccion();
        transaccion.setPrestamo(prestamorechazado);
        transaccion.setTipoTransaccion("Rechazo de prestamo");
        transaccion.setFechaPrestamo(Timestamp.valueOf(LocalDateTime.now()));
        transaccion.setValor(prestamorechazado.getMonto());
        Transaccion transaccionResult = transaccionRepository.save(transaccion);
        
        return prestamorechazado;

    }

    @SuppressWarnings("unused")
    public String ConsultarEstado(Long prestamoId) {
        Prestamo prestamo = prestamosRepository.findById(prestamoId).orElseThrow(() ->
                 new NoSuchElementException("Solicitud de prestamo no existe en el sistema"));
        return prestamosRepository.findDescripcionByIdPrestamo(prestamoId);
    }

    public List<PlanPagos> simularPalnPagos(AprobacionDTO datoPrestamo){
        Prestamo prestamo = prestamosRepository.findById(datoPrestamo.getIdPrestamo()).orElseThrow(() ->
                 new NoSuchElementException("Solicitud de prestamo no existe en el sistema"));
         
        //Se crea el plan de pagos
        List<PlanPagos> planPagos = new ArrayList<>();
        BigDecimal capital = prestamo.getMonto().divide(BigDecimal.valueOf(prestamo.getPlazo()), 2, RoundingMode.HALF_UP);
        BigDecimal intereses;
        BigDecimal valorCuota;
        BigDecimal saldo = prestamo.getMonto();
        BigDecimal ajusteCapital;
        for (int i = 1; i <= prestamo.getPlazo(); i++) {
            intereses = saldo.multiply(prestamo.getTasaInteres().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
            valorCuota = capital.add(intereses);
            saldo = saldo.subtract(capital);
            if (i==prestamo.getPlazo()-1) {
                ajusteCapital = saldo.subtract(capital);
                saldo = saldo.subtract(ajusteCapital);
            }
            PlanPagos plan = new PlanPagos(i, capital, intereses, valorCuota, saldo);
            planPagos.add(plan);
        }
        return planPagos;

    }

    @SuppressWarnings("unused")
    public DatosPrestamo consultaprestamo(Long idprestamo) {
        Prestamo prestamo = prestamosRepository.findById(idprestamo).orElseThrow(() ->
                 new NoSuchElementException("Solicitud de prestamo no existe en el sistema"));
        return prestamosRepository.findPrestamoByidprestamo(idprestamo);

    }

    @SuppressWarnings("unused")
    public List<PrestamosPorCliente> consultaprestamosCliente(Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() ->
                 new NoSuchElementException("Cliente no existe en el sistema"));
        return prestamosRepository.findPrestamoByidCliente(idCliente);

    }
 

}
