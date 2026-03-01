package com.bank.clients.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteRequest(
		@NotBlank String nome,
		@NotBlank String identificadorCliente,
		@NotNull LocalDate dataNascimento,
		@NotBlank String documento,
		@NotNull @Valid EnderecoRequest endereco
) {
}
