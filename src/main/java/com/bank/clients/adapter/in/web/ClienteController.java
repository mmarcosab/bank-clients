package com.bank.clients.adapter.in.web;

import com.bank.clients.application.port.in.ClienteUseCase;
import com.bank.clients.dto.ClienteRequest;
import com.bank.clients.dto.ClienteResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	private final ClienteUseCase clienteUseCase;

	public ClienteController(final ClienteUseCase clienteUseCase) {
		this.clienteUseCase = clienteUseCase;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteResponse criar(@RequestBody @Valid final ClienteRequest request) {
		return clienteUseCase.criar(request);
	}

	@PutMapping("/{id}")
	public ClienteResponse atualizar(@PathVariable final Long id, @RequestBody @Valid final ClienteRequest request) {
		return clienteUseCase.atualizar(id, request);
	}

	@GetMapping("/{id}")
	public ClienteResponse buscarPorId(@PathVariable final Long id) {
		return clienteUseCase.buscarPorId(id);
	}

	@GetMapping
	public List<ClienteResponse> listar() {
		return clienteUseCase.listar();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable final Long id) {
		clienteUseCase.excluir(id);
	}
}
