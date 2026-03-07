package com.bank.clients.application.port.out;

import com.bank.clients.domain.ClienteEntity;

import java.util.List;
import java.util.Optional;

public interface ClientePersistencePort {
	boolean existsByDocumento(String documento);

	boolean existsByDocumentoAndIdNot(String documento, Long id);

	boolean existsById(Long id);

	ClienteEntity save(ClienteEntity clienteEntity);

	Optional<ClienteEntity> findById(Long id);

	List<ClienteEntity> findAll();

	void deleteById(Long id);
}
