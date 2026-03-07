package com.bank.clients.service;

import com.bank.clients.domain.Cliente;
import com.bank.clients.domain.ClienteEntity;
import com.bank.clients.domain.Endereco;
import com.bank.clients.domain.FabricaCliente;
import com.bank.clients.dto.ClienteRequest;
import com.bank.clients.dto.ClienteResponse;
import com.bank.clients.dto.EnderecoRequest;
import com.bank.clients.exception.ResourceNotFoundException;
import com.bank.clients.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
	private final ClienteRepository clienteRepository;

	public ClienteService(final ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public ClienteResponse criar(final ClienteRequest request) {
		final Cliente cliente = construirClienteComFabrica(request);
		final String documentoNormalizado = cliente.documento().valor();
		if (clienteRepository.existsByDocumento(documentoNormalizado)) {
			throw new IllegalArgumentException("ja existe cliente com este documento");
		}
		final ClienteEntity entity = mapearParaEntity(cliente, null);
		return mapearParaResponse(clienteRepository.save(entity));
	}

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

	public ClienteResponse buscarPorId(final Long id) {
		return clienteRepository.findById(id)
				.map(this::mapearParaResponse)
				.orElseThrow(() -> new ResourceNotFoundException("cliente nao encontrado"));
	}

	public List<ClienteResponse> listar() {
		return clienteRepository.findAll().stream().map(this::mapearParaResponse).toList();
	}

	public void excluir(final Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new ResourceNotFoundException("cliente nao encontrado");
		}
		clienteRepository.deleteById(id);
	}

	private Cliente construirClienteComFabrica(final ClienteRequest request) {
		return FabricaCliente.criar(
				request.nome(),
				request.nomeMae(),
				request.identificadorCliente(),
				request.dataNascimento(),
				request.estadoCivil(),
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
		entity.setNomeMae(cliente.nomeMae());
		entity.setIdentificadorCliente(cliente.identificadorCliente());
		entity.setDataNascimento(cliente.dataNascimento());
		entity.setEstadoCivil(cliente.estadoCivil());
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
				entity.getNomeMae(),
				entity.getIdentificadorCliente(),
				entity.getDataNascimento(),
				entity.getEstadoCivil().name(),
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
