package com.example.atividade_xpto.repository;

import com.example.atividade_xpto.core.models.ClientePJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientePJRepository extends JpaRepository<ClientePJ, Long> {
    Optional<ClientePJ> findByCnpj(String cnpj);
    boolean existsByCnpj(String cnpj);
    @Modifying
    void deleteByCnpj(String cnpj);
}
