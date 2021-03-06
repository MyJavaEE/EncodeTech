package br.com.encodetech.domain.complementos;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Detalhes:
 * 
 * MappedSuperclass Específica que a classe não é uma tabela, mas vai ser usada
 *                   por outras classes que são tabelas
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class GenericDomain implements Serializable {

	

	/**
	 * Detalhes:
	 * 
	 * Id: Define a chave primaria
	 * GeneratedValue: Gera uma chave primária de modo automatico no DB
	 * 
	 */
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "GenericDomain [codigo=" + codigo + ", getCodigo()=" + getCodigo() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	//Hash e Equals....
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericDomain other = (GenericDomain) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
