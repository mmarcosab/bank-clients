package com.bank.clients.application.port.in;

import com.bank.clients.dto.ClienteRequest;
import com.bank.clients.dto.ClienteResponse;

import java.util.List;

public interface ClienteUseCase {
	ClienteResponse criar(final ClienteRequest request);

	ClienteResponse atualizar(final Long id, final ClienteRequest request);

	ClienteResponse buscarPorId(final Long id);

	List<ClienteResponse> listar();

	void excluir(final Long id);
}
