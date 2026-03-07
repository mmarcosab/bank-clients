package com.bank.clients.domain;

import java.time.LocalDate;
import java.util.Objects;

public record Cliente(
		String nome,
		String nomeMae,
		String identificadorCliente,
		LocalDate dataNascimento,
		EstadoCivil estadoCivil,
		Documento documento,
		Endereco endereco
) {
	public Cliente {
		if (Objects.isNull(nome) || nome.isBlank()) {
			throw new IllegalArgumentException("nome eh obrigatorio");
		}
		if (nome.length() > 60) {
			throw new IllegalArgumentException("nome deve ter no maximo 60 caracteres");
		}
		if (Objects.isNull(nomeMae) || nomeMae.isBlank()) {
			throw new IllegalArgumentException("nome da mae eh obrigatorio");
		}
		if (nomeMae.length() > 60) {
			throw new IllegalArgumentException("nome da mae deve ter no maximo 60 caracteres");
		}
		if (Objects.isNull(identificadorCliente) || identificadorCliente.isBlank()) {
			throw new IllegalArgumentException("identificador do cliente eh obrigatorio");
		}
		if (Objects.isNull(dataNascimento)) {
			throw new IllegalArgumentException("data de nascimento eh obrigatoria");
		}
		if (Objects.isNull(estadoCivil)) {
			throw new IllegalArgumentException("estado civil eh obrigatorio");
		}
		if (Objects.isNull(documento)) {
			throw new IllegalArgumentException("documento eh obrigatorio");
		}
		if (Objects.isNull(endereco)) {
			throw new IllegalArgumentException("endereco eh obrigatorio");
		}
	}
}
