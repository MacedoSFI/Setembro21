package com.felipe.setembro21.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "celular")
public class CelularNumero implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@JsonIgnore
	@OneToOne(mappedBy = "conta") //optional = false não permite contas sem usuário (pai) fiz e deu erro 
	private Usuario usuario;
	
	private String codPais = "+55";
	private String numeroComDDD;

	public CelularNumero() {
		
	}
	public CelularNumero(String numeroComDDD) {
		super();
		this.numeroComDDD = numeroComDDD;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getNumeroComDDD() {
		return numeroComDDD;
	}
	public void setNumeroComDDD(String numeroComDDD) {
		this.numeroComDDD = numeroComDDD;
	}
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	public String getCodPais() {
		return codPais;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codPais, id, numeroComDDD, usuario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CelularNumero other = (CelularNumero) obj;
		return Objects.equals(codPais, other.codPais) && Objects.equals(id, other.id)
				&& Objects.equals(numeroComDDD, other.numeroComDDD) && Objects.equals(usuario, other.usuario);
	}
	
		
}
