package com.codigo.feign.repository;

import com.codigo.feign.entity.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Long> {
    Optional<TipoDocumentoEntity> findByCodTipo(String codTipo);
}
