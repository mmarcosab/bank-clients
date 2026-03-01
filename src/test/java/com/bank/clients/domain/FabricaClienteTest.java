package com.bank.clients.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class FabricaClienteTest {

	@Test
	void deveCriarClienteComCpfValido() {
		final Endereco endereco = new Endereco("Rua A", "123", "Apto 1", "Centro", "Sao Paulo", "SP");

		final Cliente cliente = FabricaCliente.criar(
				"Maria da Silva",
				"CLIENTE-001",
				LocalDate.of(1990, 5, 10),
				"529.982.247-25",
				endereco
		);

		assertEquals("Maria da Silva", cliente.nome());
		assertEquals("CLIENTE-001", cliente.identificadorCliente());
		assertEquals(TipoDocumento.CPF, cliente.documento().tipo());
		assertEquals("52998224725", cliente.documento().valor());
	}

	@Test
	void deveCriarClienteComCnpjValido() {
		final Endereco endereco = new Endereco("Rua B", "100", "Sala 5", "Comercial", "Rio de Janeiro", "RJ");

		final Cliente cliente = FabricaCliente.criar(
				"Empresa XPTO",
				"CLIENTE-002",
				LocalDate.of(2001, 1, 1),
				"04.252.011/0001-10",
				endereco
		);

		assertEquals(TipoDocumento.CNPJ, cliente.documento().tipo());
		assertEquals("04252011000110", cliente.documento().valor());
	}

	@Test
	void deveRejeitarNomeMaiorQueSessentaCaracteres() {
		final Endereco endereco = new Endereco("Rua C", "1", "Casa", "Bairro", "Cidade", "ST");
		final String nomeLongo = "Nome muito grande com mais de sessenta caracteres para teste final";

		assertThrows(IllegalArgumentException.class, () -> FabricaCliente.criar(
				nomeLongo,
				"CLIENTE-003",
				LocalDate.of(1985, 8, 20),
				"52998224725",
				endereco
		));
	}

	@Test
	void deveRejeitarCpfInvalido() {
		final Endereco endereco = new Endereco("Rua D", "10", "Fundos", "Centro", "Curitiba", "PR");

		assertThrows(IllegalArgumentException.class, () -> FabricaCliente.criar(
				"Joao",
				"CLIENTE-004",
				LocalDate.of(1995, 2, 15),
				"11111111111",
				endereco
		));
	}

	@Test
	void deveRejeitarCnpjInvalido() {
		final Endereco endereco = new Endereco("Rua E", "10", "Fundos", "Centro", "Curitiba", "PR");

		assertThrows(IllegalArgumentException.class, () -> FabricaCliente.criar(
				"Empresa Invalida",
				"CLIENTE-005",
				LocalDate.of(2000, 2, 15),
				"11.111.111/1111-11",
				endereco
		));
	}

	@Test
	void deveRejeitarCampoEmBrancoNoEndereco() {
		assertThrows(IllegalArgumentException.class, () -> new Endereco(
				"Rua F",
				"50",
				"",
				"Centro",
				"Florianopolis",
				"SC"
		));
	}
}
