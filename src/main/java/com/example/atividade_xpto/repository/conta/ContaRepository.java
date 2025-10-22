package com.example.atividade_xpto.repository.conta;

import com.example.atividade_xpto.core.models.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
