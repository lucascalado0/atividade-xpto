package com.example.atividade_xpto.repository.clientes;

import com.example.atividade_xpto.core.models.clientes.ClientePF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientePFRepository extends JpaRepository<ClientePF,Long> {
    Optional<ClientePF> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
    @Modifying
    void deleteByCpf(String cpf);


}
