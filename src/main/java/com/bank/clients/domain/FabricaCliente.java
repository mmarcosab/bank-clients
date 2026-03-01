package com.bank.clients.domain;

import java.time.LocalDate;

public final class FabricaCliente {
	private FabricaCliente() {
	}

	public static Cliente criar(
			final String nome,
			final String identificadorCliente,
			final LocalDate dataNascimento,
			final String documentoBruto,
			final Endereco endereco
	) {
		final Documento documento = Documento.de(documentoBruto);
		return new Cliente(nome, identificadorCliente, dataNascimento, documento, endereco);
	}
}
