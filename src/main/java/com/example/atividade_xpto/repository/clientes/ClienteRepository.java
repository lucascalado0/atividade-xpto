package com.example.atividade_xpto.repository.clientes;

import com.example.atividade_xpto.core.models.clientes.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
