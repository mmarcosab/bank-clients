package com.bank.clients.adapter.out.persistence;

import com.bank.clients.application.port.out.ClientePersistencePort;
import com.bank.clients.domain.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientePersistenceAdapter implements ClientePersistencePort {
	private final ClienteJpaRepository clienteJpaRepository;

	public ClientePersistenceAdapter(final ClienteJpaRepository clienteJpaRepository) {
		this.clienteJpaRepository = clienteJpaRepository;
	}

	@Override
	public boolean existsByDocumento(final String documento) {
		return clienteJpaRepository.existsByDocumento(documento);
	}

	@Override
	public boolean existsByDocumentoAndIdNot(final String documento, final Long id) {
		return clienteJpaRepository.existsByDocumentoAndIdNot(documento, id);
	}

	@Override
	public boolean existsById(final Long id) {
		return clienteJpaRepository.existsById(id);
	}

	@Override
	public ClienteEntity save(final ClienteEntity clienteEntity) {
		return clienteJpaRepository.save(clienteEntity);
	}

	@Override
	public Optional<ClienteEntity> findById(final Long id) {
		return clienteJpaRepository.findById(id);
	}

	@Override
	public List<ClienteEntity> findAll() {
		return clienteJpaRepository.findAll();
	}

	@Override
	public void deleteById(final Long id) {
		clienteJpaRepository.deleteById(id);
	}
}
