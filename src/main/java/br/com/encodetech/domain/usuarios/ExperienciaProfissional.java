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
 * 
 */

@SuppressWarnings("serial")
@Entity
public class ExperienciaProfissional extends GenericDomain {

	@Column(length = 35)
	private String nomeEmpresa;

	@Column(length = 35)
	private String cargo;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date entrada;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date saida;

	@Column(nullable = false)
	private String principaisAtividades;

	// ----------------------------------------------------------------

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

	public String getPrincipaisAtividades() {
		return principaisAtividades;
	}

	public void setPrincipaisAtividades(String principaisAtividades) {
		this.principaisAtividades = principaisAtividades;
	}

}