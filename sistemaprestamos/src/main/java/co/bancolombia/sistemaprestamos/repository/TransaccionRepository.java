package co.bancolombia.sistemaprestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.bancolombia.sistemaprestamos.models.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
