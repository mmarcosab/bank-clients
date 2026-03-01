package com.bank.clients.adapter.out.persistence.repository;

import com.bank.clients.adapter.out.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataClienteRepository extends JpaRepository<ClienteEntity, Long> {
	boolean existsByDocumento(final String documento);

	boolean existsByDocumentoAndIdNot(final String documento, final Long id);
}
