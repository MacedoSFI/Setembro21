package com.felipe.setembro21.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "celular")
public class CelularNumero {
	
	@Id
    @Column(name = "usuario_id")
    private Long id;

	@OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
	
	private String codPais = "+55";
	private String numeroComDDD;

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
		
}
