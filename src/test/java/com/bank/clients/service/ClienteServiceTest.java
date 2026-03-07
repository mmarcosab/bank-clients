package com.bank.clients.service;

import com.bank.clients.application.port.out.ClientePersistencePort;
import com.bank.clients.application.service.ClienteService;
import com.bank.clients.domain.ClienteEntity;
import com.bank.clients.domain.EstadoCivil;
import com.bank.clients.domain.TipoDocumento;
import com.bank.clients.dto.ClienteRequest;
import com.bank.clients.dto.ClienteResponse;
import com.bank.clients.dto.EnderecoRequest;
import com.bank.clients.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
	@Mock
	private ClientePersistencePort clientePersistencePort;

	@InjectMocks
	private ClienteService clienteService;

	@Test
	void deveCriarClienteComSucesso() {
		final ClienteRequest request = requestValido();
		when(clientePersistencePort.existsByDocumento("12345678909")).thenReturn(false);
		when(clientePersistencePort.save(any(ClienteEntity.class))).thenAnswer(invocation -> {
			final ClienteEntity entity = invocation.getArgument(0);
			entity.setId(1L);
			return entity;
		});

		final ClienteResponse response = clienteService.criar(request);

		assertThat(response.id()).isEqualTo(1L);
		assertThat(response.nomeMae()).isEqualTo("Mae Teste");
		assertThat(response.documento()).isEqualTo("12345678909");
	}

	@Test
	void deveAtualizarClienteComSucesso() {
		final ClienteRequest request = requestValido();
		when(clientePersistencePort.existsById(1L)).thenReturn(true);
		when(clientePersistencePort.existsByDocumentoAndIdNot("12345678909", 1L)).thenReturn(false);
		when(clientePersistencePort.save(any(ClienteEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

		final ClienteResponse response = clienteService.atualizar(1L, request);

		assertThat(response.id()).isEqualTo(1L);
		assertThat(response.estadoCivil()).isEqualTo("SOLTEIRO");
	}

	@Test
	void deveFalharAoAtualizarQuandoClienteNaoExiste() {
		when(clientePersistencePort.existsById(99L)).thenReturn(false);

		assertThatThrownBy(() -> clienteService.atualizar(99L, requestValido()))
				.isInstanceOf(ResourceNotFoundException.class)
				.hasMessageContaining("cliente nao encontrado");

		verify(clientePersistencePort, never()).save(any(ClienteEntity.class));
	}

	@Test
	void deveFalharQuandoDocumentoJaExiste() {
		final ClienteRequest request = requestValido();
		when(clientePersistencePort.existsByDocumento("12345678909")).thenReturn(true);

		assertThatThrownBy(() -> clienteService.criar(request))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("ja existe cliente");
	}

	@Test
	void deveFalharQuandoDocumentoInvalidoNaCriacao() {
		final ClienteRequest request = new ClienteRequest(
				"Cliente Teste",
				"Mae Teste",
				"ID-123",
				LocalDate.of(1990, 1, 1),
				"SOLTEIRO",
				"111.111.111-11",
				new EnderecoRequest("Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP")
		);

		assertThatThrownBy(() -> clienteService.criar(request))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("CPF invalido");
	}

	@Test
	void deveFalharQuandoEstadoCivilInvalidoNaCriacao() {
		final ClienteRequest request = new ClienteRequest(
				"Cliente Teste",
				"Mae Teste",
				"ID-123",
				LocalDate.of(1990, 1, 1),
				"QUALQUER",
				"123.456.789-09",
				new EnderecoRequest("Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP")
		);

		assertThatThrownBy(() -> clienteService.criar(request))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("estado civil invalido");
	}

	@Test
	void deveFalharQuandoNomeMaeEmBrancoNaCriacao() {
		final ClienteRequest request = new ClienteRequest(
				"Cliente Teste",
				"",
				"ID-123",
				LocalDate.of(1990, 1, 1),
				"SOLTEIRO",
				"123.456.789-09",
				new EnderecoRequest("Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP")
		);

		assertThatThrownBy(() -> clienteService.criar(request))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("nome da mae eh obrigatorio");
	}

	@Test
	void deveRetornarErroQuandoClienteNaoEncontradoNaBusca() {
		when(clientePersistencePort.findById(10L)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> clienteService.buscarPorId(10L))
				.isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	void deveExcluirClienteComSucesso() {
		when(clientePersistencePort.existsById(1L)).thenReturn(true);

		clienteService.excluir(1L);

		verify(clientePersistencePort).deleteById(1L);
	}

	@Test
	void deveFalharAoExcluirQuandoClienteNaoExiste() {
		when(clientePersistencePort.existsById(999L)).thenReturn(false);

		assertThatThrownBy(() -> clienteService.excluir(999L))
				.isInstanceOf(ResourceNotFoundException.class)
				.hasMessageContaining("cliente nao encontrado");
	}

	private ClienteRequest requestValido() {
		return new ClienteRequest(
				"Cliente Teste",
				"Mae Teste",
				"ID-123",
				LocalDate.of(1990, 1, 1),
				"SOLTEIRO",
				"123.456.789-09",
				new EnderecoRequest("Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP")
		);
	}
}
