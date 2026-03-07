package com.bank.clients.application.port.in;

import com.bank.clients.dto.ClienteRequest;
import com.bank.clients.dto.ClienteResponse;

import java.util.List;

public interface ClienteUseCase {
	ClienteResponse criar(ClienteRequest request);

	ClienteResponse atualizar(Long id, ClienteRequest request);

	ClienteResponse buscarPorId(Long id);

	List<ClienteResponse> listar();

	void excluir(Long id);
}
