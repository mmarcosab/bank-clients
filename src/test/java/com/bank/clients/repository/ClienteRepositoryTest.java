package com.bank.clients.repository;

import com.bank.clients.domain.ClienteEntity;
import com.bank.clients.domain.EstadoCivil;
import com.bank.clients.domain.TipoDocumento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ClienteRepositoryTest {
	@Autowired
	private ClienteRepository clienteRepository;

	@Test
	void deveSalvarEConsultarPorDocumento() {
		final ClienteEntity entity = new ClienteEntity();
		entity.setNome("Cliente Repo");
		entity.setNomeMae("Mae Repo");
		entity.setIdentificadorCliente("ID-999");
		entity.setDataNascimento(LocalDate.of(1985, 5, 10));
		entity.setEstadoCivil(EstadoCivil.SOLTEIRO);
		entity.setTipoDocumento(TipoDocumento.CPF);
		entity.setDocumento("12345678909");
		entity.setRua("Rua B");
		entity.setNumero("20");
		entity.setComplemento("Casa");
		entity.setBairro("Centro");
		entity.setCidade("Curitiba");
		entity.setEstado("PR");

		clienteRepository.save(entity);

		assertThat(clienteRepository.existsByDocumento("12345678909")).isTrue();
	}
}
