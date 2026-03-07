package com.bank.clients.domain;

import java.util.Arrays;

public enum EstadoCivil {
	SOLTEIRO,
	CASADO,
	DIVORCIADO,
	VIUVO;

	public static EstadoCivil de(final String valor) {
		if (valor == null || valor.isBlank()) {
			throw new IllegalArgumentException("estado civil eh obrigatorio");
		}

		return Arrays.stream(values())
				.filter(estadoCivil -> estadoCivil.name().equalsIgnoreCase(valor.trim()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("estado civil invalido"));
	}
}
