package com.sunbelt.backclient.repository;

import com.sunbelt.backclient.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findByNumeroDocumentoAndTipoIdentificacion(String numeroDocumento, String tipoIdentificacion);

}