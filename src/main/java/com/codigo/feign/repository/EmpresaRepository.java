package com.codigo.feign.repository;

import com.codigo.feign.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {
    Optional<EmpresaEntity> findByNumDocu(String numDocu);
}
