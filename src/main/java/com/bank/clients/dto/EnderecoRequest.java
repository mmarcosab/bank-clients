package com.bank.clients.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequest(
		@NotBlank String rua,
		@NotBlank String numero,
		@NotBlank String complemento,
		@NotBlank String bairro,
		@NotBlank String cidade,
		@NotBlank String estado
) {
}
