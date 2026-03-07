package com.bank.clients.adapter.out.persistence;

import com.bank.clients.domain.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
	boolean existsByDocumento(String documento);

	boolean existsByDocumentoAndIdNot(String documento, Long id);
}
