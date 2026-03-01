package com.bank.clients.domain;

public record Documento(TipoDocumento tipo, String valor) {
	public Documento {
		if (tipo == null) {
			throw new IllegalArgumentException("tipo de documento eh obrigatorio");
		}
		if (valor == null || valor.isBlank()) {
			throw new IllegalArgumentException("valor do documento eh obrigatorio");
		}

		valor = normalizar(valor);
		validar(tipo, valor);
	}

	public static Documento de(final String valorBruto) {
		final String valorNormalizado = normalizar(valorBruto);
		if (valorNormalizado.length() == 11) {
			return new Documento(TipoDocumento.CPF, valorNormalizado);
		}
		if (valorNormalizado.length() == 14) {
			return new Documento(TipoDocumento.CNPJ, valorNormalizado);
		}
		throw new IllegalArgumentException("documento deve ser CPF ou CNPJ");
	}

	private static String normalizar(final String valorBruto) {
		if (valorBruto == null) {
			return "";
		}
		return valorBruto.replaceAll("\\D", "");
	}

	private static void validar(final TipoDocumento tipo, final String valorNormalizado) {
		final boolean valido = tipo == TipoDocumento.CPF
				? cpfValido(valorNormalizado)
				: cnpjValido(valorNormalizado);
		if (!valido) {
			throw new IllegalArgumentException(tipo + " invalido");
		}
	}

	private static boolean cpfValido(final String cpf) {
		if (cpf.length() != 11 || cpf.chars().distinct().count() == 1) {
			return false;
		}
		final int primeiroDigito = calcularDigitoCpf(cpf, 9, 10);
		final int segundoDigito = calcularDigitoCpf(cpf, 10, 11);
		return primeiroDigito == Character.getNumericValue(cpf.charAt(9))
				&& segundoDigito == Character.getNumericValue(cpf.charAt(10));
	}

	private static int calcularDigitoCpf(final String cpf, final int tamanho, final int pesoInicial) {
		int soma = 0;
		for (int indice = 0; indice < tamanho; indice++) {
			soma += Character.getNumericValue(cpf.charAt(indice)) * (pesoInicial - indice);
		}
		final int resto = soma % 11;
		return resto < 2 ? 0 : 11 - resto;
	}

	private static boolean cnpjValido(final String cnpj) {
		if (cnpj.length() != 14 || cnpj.chars().distinct().count() == 1) {
			return false;
		}

		final int primeiroDigito = calcularDigitoCnpj(cnpj, 12);
		final int segundoDigito = calcularDigitoCnpj(cnpj, 13);
		return primeiroDigito == Character.getNumericValue(cnpj.charAt(12))
				&& segundoDigito == Character.getNumericValue(cnpj.charAt(13));
	}

	private static int calcularDigitoCnpj(final String cnpj, final int tamanho) {
		final int[] pesos = tamanho == 12
				? new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}
				: new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

		int soma = 0;
		for (int indice = 0; indice < tamanho; indice++) {
			soma += Character.getNumericValue(cnpj.charAt(indice)) * pesos[indice];
		}
		final int resto = soma % 11;
		return resto < 2 ? 0 : 11 - resto;
	}
}
