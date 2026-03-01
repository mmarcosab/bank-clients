package com.bank.clients.controller;

import com.bank.clients.dto.ClienteRequest;
import com.bank.clients.dto.ClienteResponse;
import com.bank.clients.service.ClienteService;
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
	private final ClienteService clienteService;

	public ClienteController(final ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteResponse criar(@RequestBody @Valid final ClienteRequest request) {
		return clienteService.criar(request);
	}

	@PutMapping("/{id}")
	public ClienteResponse atualizar(@PathVariable final Long id, @RequestBody @Valid final ClienteRequest request) {
		return clienteService.atualizar(id, request);
	}

	@GetMapping("/{id}")
	public ClienteResponse buscarPorId(@PathVariable final Long id) {
		return clienteService.buscarPorId(id);
	}

	@GetMapping
	public List<ClienteResponse> listar() {
		return clienteService.listar();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable final Long id) {
		clienteService.excluir(id);
	}
}
