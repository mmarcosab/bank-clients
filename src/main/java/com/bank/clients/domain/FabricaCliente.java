package com.bank.clients.domain;

import java.time.LocalDate;

public final class FabricaCliente {
	private FabricaCliente() {
	}

	public static Cliente criar(
			final String nome,
			final String identificadorCliente,
			final LocalDate dataNascimento,
			final String estadoCivil,
			final String documentoBruto,
			final Endereco endereco
	) {
		final Documento documento = Documento.de(documentoBruto);
		final EstadoCivil estadoCivilNormalizado = EstadoCivil.de(estadoCivil);
		return new Cliente(nome, identificadorCliente, dataNascimento, estadoCivilNormalizado, documento, endereco);
	}
}
