package com.bank.clients.service;

import com.bank.clients.domain.ClienteEntity;
import com.bank.clients.dto.ClienteRequest;
import com.bank.clients.dto.ClienteResponse;
import com.bank.clients.dto.EnderecoRequest;
import com.bank.clients.exception.ResourceNotFoundException;
import com.bank.clients.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteService clienteService;

	@Test
	void deveCriarClienteComSucesso() {
		final ClienteRequest request = requestValido();
		when(clienteRepository.existsByDocumento("12345678909")).thenReturn(false);
		when(clienteRepository.save(org.mockito.ArgumentMatchers.any(ClienteEntity.class))).thenAnswer(invocation -> {
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
	void deveFalharQuandoDocumentoJaExiste() {
		final ClienteRequest request = requestValido();
		when(clienteRepository.existsByDocumento("12345678909")).thenReturn(true);

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
		when(clienteRepository.findById(10L)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> clienteService.buscarPorId(10L))
				.isInstanceOf(ResourceNotFoundException.class);
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
