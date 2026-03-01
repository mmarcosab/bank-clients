package com.bank.clients.adapter.out.persistence.entity;

import com.bank.clients.domain.TipoDocumento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
public class ClienteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 60)
	private String nome;

	@Column(nullable = false)
	private String identificadorCliente;

	@Column(nullable = false)
	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 4)
	private TipoDocumento tipoDocumento;

	@Column(nullable = false, unique = true, length = 14)
	private String documento;

	@Column(nullable = false)
	private String rua;

	@Column(nullable = false)
	private String numero;

	@Column(nullable = false)
	private String complemento;

	@Column(nullable = false)
	private String bairro;

	@Column(nullable = false)
	private String cidade;

	@Column(nullable = false)
	private String estado;

	public Long getId() { return id; }
	public void setId(final Long id) { this.id = id; }
	public String getNome() { return nome; }
	public void setNome(final String nome) { this.nome = nome; }
	public String getIdentificadorCliente() { return identificadorCliente; }
	public void setIdentificadorCliente(final String identificadorCliente) { this.identificadorCliente = identificadorCliente; }
	public LocalDate getDataNascimento() { return dataNascimento; }
	public void setDataNascimento(final LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
	public TipoDocumento getTipoDocumento() { return tipoDocumento; }
	public void setTipoDocumento(final TipoDocumento tipoDocumento) { this.tipoDocumento = tipoDocumento; }
	public String getDocumento() { return documento; }
	public void setDocumento(final String documento) { this.documento = documento; }
	public String getRua() { return rua; }
	public void setRua(final String rua) { this.rua = rua; }
	public String getNumero() { return numero; }
	public void setNumero(final String numero) { this.numero = numero; }
	public String getComplemento() { return complemento; }
	public void setComplemento(final String complemento) { this.complemento = complemento; }
	public String getBairro() { return bairro; }
	public void setBairro(final String bairro) { this.bairro = bairro; }
	public String getCidade() { return cidade; }
	public void setCidade(final String cidade) { this.cidade = cidade; }
	public String getEstado() { return estado; }
	public void setEstado(final String estado) { this.estado = estado; }
}
