package com.hemanth.connectorplatform.repository;

import com.hemanth.connectorplatform.entity.ConnectorConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConnectorConfigRepository extends JpaRepository<ConnectorConfigEntity, Long> {
    Optional<ConnectorConfigEntity> findByConnectorNameIgnoreCase(String connectorName);
    void deleteByConnectorNameIgnoreCase(String connectorName);
}
