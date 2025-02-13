package co.bancolombia.sistemaprestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.bancolombia.sistemaprestamos.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}
