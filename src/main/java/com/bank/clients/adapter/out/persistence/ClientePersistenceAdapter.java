package com.bank.clients.adapter.out.persistence;

import com.bank.clients.adapter.out.persistence.entity.ClienteEntity;
import com.bank.clients.adapter.out.persistence.repository.SpringDataClienteRepository;
import com.bank.clients.application.port.out.ClienteRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientePersistenceAdapter implements ClienteRepositoryPort {
	private final SpringDataClienteRepository repository;

	public ClientePersistenceAdapter(final SpringDataClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean existsByDocumento(final String documento) {
		return repository.existsByDocumento(documento);
	}

	@Override
	public boolean existsByDocumentoAndIdNot(final String documento, final Long id) {
		return repository.existsByDocumentoAndIdNot(documento, id);
	}

	@Override
	public boolean existsById(final Long id) {
		return repository.existsById(id);
	}

	@Override
	public Optional<ClienteEntity> findById(final Long id) {
		return repository.findById(id);
	}

	@Override
	public List<ClienteEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public ClienteEntity save(final ClienteEntity entity) {
		return repository.save(entity);
	}

	@Override
	public void deleteById(final Long id) {
		repository.deleteById(id);
	}
}
