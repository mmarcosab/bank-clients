package com.bank.clients.dto;

import java.time.LocalDate;

public record ClienteResponse(
		Long id,
		String nome,
		String identificadorCliente,
		LocalDate dataNascimento,
		String tipoDocumento,
		String documento,
		EnderecoRequest endereco
) {
}
