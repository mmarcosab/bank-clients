package com.bank.clients.application.service;

import com.bank.clients.adapter.out.persistence.entity.ClienteEntity;
import com.bank.clients.application.port.in.ClienteUseCase;
import com.bank.clients.application.port.out.ClienteRepositoryPort;
import com.bank.clients.domain.Cliente;
import com.bank.clients.domain.Endereco;
import com.bank.clients.domain.FabricaCliente;
import com.bank.clients.dto.ClienteRequest;
import com.bank.clients.dto.ClienteResponse;
import com.bank.clients.dto.EnderecoRequest;
import com.bank.clients.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteApplicationService implements ClienteUseCase {
	private final ClienteRepositoryPort clienteRepository;

	public ClienteApplicationService(final ClienteRepositoryPort clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	public ClienteResponse criar(final ClienteRequest request) {
		final Cliente cliente = construirClienteComFabrica(request);
		final String documentoNormalizado = cliente.documento().valor();
		if (clienteRepository.existsByDocumento(documentoNormalizado)) {
			throw new IllegalArgumentException("ja existe cliente com este documento");
		}
		final ClienteEntity entity = mapearParaEntity(cliente, null);
		return mapearParaResponse(clienteRepository.save(entity));
	}

	@Override
	public ClienteResponse atualizar(final Long id, final ClienteRequest request) {
		if (!clienteRepository.existsById(id)) {
			throw new ResourceNotFoundException("cliente nao encontrado");
		}
		final Cliente cliente = construirClienteComFabrica(request);
		final String documentoNormalizado = cliente.documento().valor();
		if (clienteRepository.existsByDocumentoAndIdNot(documentoNormalizado, id)) {
			throw new IllegalArgumentException("ja existe cliente com este documento");
		}
		final ClienteEntity entity = mapearParaEntity(cliente, id);
		return mapearParaResponse(clienteRepository.save(entity));
	}

	@Override
	public ClienteResponse buscarPorId(final Long id) {
		return clienteRepository.findById(id)
				.map(this::mapearParaResponse)
				.orElseThrow(() -> new ResourceNotFoundException("cliente nao encontrado"));
	}

	@Override
	public List<ClienteResponse> listar() {
		return clienteRepository.findAll().stream().map(this::mapearParaResponse).toList();
	}

	@Override
	public void excluir(final Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new ResourceNotFoundException("cliente nao encontrado");
		}
		clienteRepository.deleteById(id);
	}

	private Cliente construirClienteComFabrica(final ClienteRequest request) {
		return FabricaCliente.criar(
				request.nome(),
				request.identificadorCliente(),
				request.dataNascimento(),
				request.documento(),
				new Endereco(
						request.endereco().rua(),
						request.endereco().numero(),
						request.endereco().complemento(),
						request.endereco().bairro(),
						request.endereco().cidade(),
						request.endereco().estado()
				)
		);
	}

	private ClienteEntity mapearParaEntity(final Cliente cliente, final Long id) {
		final ClienteEntity entity = new ClienteEntity();
		entity.setId(id);
		entity.setNome(cliente.nome());
		entity.setIdentificadorCliente(cliente.identificadorCliente());
		entity.setDataNascimento(cliente.dataNascimento());
		entity.setTipoDocumento(cliente.documento().tipo());
		entity.setDocumento(cliente.documento().valor());
		entity.setRua(cliente.endereco().rua());
		entity.setNumero(cliente.endereco().numero());
		entity.setComplemento(cliente.endereco().complemento());
		entity.setBairro(cliente.endereco().bairro());
		entity.setCidade(cliente.endereco().cidade());
		entity.setEstado(cliente.endereco().estado());
		return entity;
	}

	private ClienteResponse mapearParaResponse(final ClienteEntity entity) {
		return new ClienteResponse(
				entity.getId(),
				entity.getNome(),
				entity.getIdentificadorCliente(),
				entity.getDataNascimento(),
				entity.getTipoDocumento().name(),
				entity.getDocumento(),
				new EnderecoRequest(
						entity.getRua(),
						entity.getNumero(),
						entity.getComplemento(),
						entity.getBairro(),
						entity.getCidade(),
						entity.getEstado()
				)
		);
	}
}
