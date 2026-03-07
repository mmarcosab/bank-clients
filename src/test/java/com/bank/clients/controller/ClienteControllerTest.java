package com.bank.clients.controller;

import com.bank.clients.dto.ClienteRequest;
import com.bank.clients.dto.ClienteResponse;
import com.bank.clients.dto.EnderecoRequest;
import com.bank.clients.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ClienteService clienteService;

	@Test
	void deveCriarCliente() throws Exception {
		final ClienteRequest request = requestValido();
		final ClienteResponse response = responseValido();
		when(clienteService.criar(request)).thenReturn(response);

		mockMvc.perform(post("/clientes")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.estadoCivil").value("SOLTEIRO"));
	}

	@Test
	void deveValidarCamposObrigatoriosNaCriacao() throws Exception {
		final String payloadInvalido = """
				{
				  \"nome\": \"\",
				  \"identificadorCliente\": \"\",
				  \"documento\": \"\",
				  \"endereco\": {
				    \"rua\": \"\",
				    \"numero\": \"\",
				    \"complemento\": \"\",
				    \"bairro\": \"\",
				    \"cidade\": \"\",
				    \"estado\": \"\"
				  }
				}
				""";

		mockMvc.perform(post("/clientes")
						.contentType(MediaType.APPLICATION_JSON)
						.content(payloadInvalido))
				.andExpect(status().isBadRequest());
	}

	@Test
	void deveListarClientes() throws Exception {
		when(clienteService.listar()).thenReturn(List.of(responseValido()));

		mockMvc.perform(get("/clientes"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].documento").value("12345678909"));
	}

	private ClienteRequest requestValido() {
		return new ClienteRequest(
				"Cliente Teste",
				"ID-123",
				LocalDate.of(1990, 1, 1),
				"SOLTEIRO",
				"123.456.789-09",
				new EnderecoRequest("Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP")
		);
	}

	private ClienteResponse responseValido() {
		return new ClienteResponse(
				1L,
				"Cliente Teste",
				"ID-123",
				LocalDate.of(1990, 1, 1),
				"SOLTEIRO",
				"CPF",
				"12345678909",
				new EnderecoRequest("Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP")
		);
	}
}
