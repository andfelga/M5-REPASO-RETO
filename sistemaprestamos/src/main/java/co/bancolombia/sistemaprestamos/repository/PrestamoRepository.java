package co.bancolombia.sistemaprestamos.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.bancolombia.sistemaprestamos.models.DatosPrestamo;
import co.bancolombia.sistemaprestamos.models.Prestamo;
import co.bancolombia.sistemaprestamos.models.PrestamosPorCliente;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{

    @Query(value = "SELECT e.descripcion FROM prestamo p JOIN estado e ON e.id_estado = p.id_estado WHERE p.id_prestamo =?1", 
    nativeQuery = true)
    String findDescripcionByIdPrestamo(Long idPrestamo);


    @Query(value = "SELECT p.id_prestamo, p.monto, p.tasa_interes, p.plazo, c.cliente_id, c.nombre, c.telefono, c.email, c.direccion, e.descripcion " +
    " FROM prestamo p JOIN cliente c ON c.cliente_id = p.id_cliente " +
    " JOIN estado e on e.id_estado = p.id_estado " +
    " WHERE p.id_prestamo =?1",
    nativeQuery = true)
    DatosPrestamo findPrestamoByidprestamo(Long idprestamo);

    @Query(value = "SELECT  p.id_prestamo, p.monto, p.tasa_interes, p.plazo, h.tipo_transaccion, h.fecha_prestamo " +
    " from historia_prestamo h " +
    " join prestamo p on h.prestamos_id = p.id_prestamo " +
    " join cliente  c on c.cliente_id = p.id_cliente " +
    " where p.id_prestamo in( select p.id_prestamo " + 
    " from prestamo p " + 
    " where p.id_cliente =?1" + 
    " order by p.id_prestamo desc limit 3) " + 
    " order by h.fecha_prestamo" , nativeQuery = true)
    List<PrestamosPorCliente> findPrestamoByidCliente(Long idCliente);


}
