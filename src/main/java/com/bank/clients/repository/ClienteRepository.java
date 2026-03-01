package com.bank.clients.repository;

import com.bank.clients.domain.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
	boolean existsByDocumento(final String documento);

	boolean existsByDocumentoAndIdNot(final String documento, final Long id);
}
