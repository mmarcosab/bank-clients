package com.bank.clients.domain;

import java.util.Objects;

public record Endereco(
		String rua,
		String numero,
		String complemento,
		String bairro,
		String cidade,
		String estado
) {
	public Endereco {
		validarObrigatorio(rua, "rua");
		validarObrigatorio(numero, "numero");
		validarObrigatorio(complemento, "complemento");
		validarObrigatorio(bairro, "bairro");
		validarObrigatorio(cidade, "cidade");
		validarObrigatorio(estado, "estado");
	}

	private static void validarObrigatorio(final String valor, final String campo) {
		if (Objects.isNull(valor) || valor.isBlank()) {
			throw new IllegalArgumentException(campo + " eh obrigatorio");
		}
	}
}
