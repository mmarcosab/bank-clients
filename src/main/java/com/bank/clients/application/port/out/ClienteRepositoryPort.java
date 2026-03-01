package com.bank.clients.application.port.out;

import com.bank.clients.adapter.out.persistence.entity.ClienteEntity;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {
	boolean existsByDocumento(final String documento);

	boolean existsByDocumentoAndIdNot(final String documento, final Long id);

	boolean existsById(final Long id);

	Optional<ClienteEntity> findById(final Long id);

	List<ClienteEntity> findAll();

	ClienteEntity save(final ClienteEntity entity);

	void deleteById(final Long id);
}
