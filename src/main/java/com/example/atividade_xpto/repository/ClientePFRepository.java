package com.example.atividade_xpto.repository;

import com.example.atividade_xpto.core.models.ClientePF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientePFRepository extends JpaRepository<ClientePF,Long> {
    Optional<ClientePF> findByCpf(String cpf);
    boolean existsByCpf(String cpf);


}
