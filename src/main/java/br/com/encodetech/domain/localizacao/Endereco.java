package br.com.encodetech.domain.localizacao;

import javax.persistence.Column;

import br.com.encodetech.domain.complementos.GenericDomain;


/**
 * Detalhes:
 * 
 * 
 * 
 */

@SuppressWarnings("serial")
public class Endereco extends GenericDomain {

	@Column
	private Estado estado;

	@Column
	private Cidade cidade;

	@Column(length = 100, nullable = false)
	private String rua;

	@Column(nullable = false)
	private Short numero;

	@Column(length = 30, nullable = false)
	private String bairro;

	@Column(length = 10, nullable = false)
	private String cep;

	@Column(length = 10, nullable = false)
	private String complemento;

	@Column(length = 13, nullable = false)
	private String telefone;

	@Column(length = 14, nullable = false)
	private String celular;


	// ----------------------------------------------

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Short getNumero() {
		return numero;
	}

	public void setNumero(Short numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public String toString() {
		return "Endereco [estado=" + estado + ", cidade=" + cidade + ", rua=" + rua + ", numero=" + numero + ", bairro="
				+ bairro + ", cep=" + cep + ", complemento=" + complemento + ", telefone=" + telefone + ", celular="
				+ celular + "]";
	}

	
	
	

}
