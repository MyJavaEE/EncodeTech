package br.com.encodetech.domain.usuarios;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.encodetech.domain.complementos.GenericDomain;


/**
 * [ Detalhes... ]
 * 
 * -Entity - Diz que a classe é uma entidade do hibernate
 * 
 * -Column(
 * | length = Tamanho do campo 
 * | name = define o nome no banco 
 * | nullable = Diz se o campo pode ou não ser nulo "True ou False");
 * | precision = Define quantidade de digitos
 * | scale= Define quantos digitos ficam após a virgula 
 * 
 * -JoinColumn - Permite personalizar colunas que são chaves estrangeiras
 * 
 *  -Temporal(TemporalType.param)
 *  |DATE=Data
 *  |Time=Hora
 *  |TIMESTAMP=Data e hora
 *  
 *  -MD5 - A senha está utilizando MD5 para criptografia.
 * 
 */

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(nullable = false)
	private Boolean status;

	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(length = 32 )
	private String senha;

	@Column(length = 14, nullable = false)
	private String cpf;

	@Column(length = 12, nullable = false)
	private String rg;

	@Column(length = 8, nullable = false)
	private String dataNascimento;



	@Column(length = 100, nullable = false)
	private String email;

	// -------------------------------------------------------

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}