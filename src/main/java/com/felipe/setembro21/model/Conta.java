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
@Table(name = "conta")
public class Conta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Long id;
	private String tipoPix;
	private String chavePix;
	private Double saldo;
	
	@JsonIgnore
	@OneToOne(mappedBy = "conta")
	private Usuario usuario;

	public Conta() {
		
	}
	public Conta(String tipoPix, String chavePix) {
		
		this.tipoPix = tipoPix;
		this.chavePix = chavePix;
		this.saldo = 0.0;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	public String getTipoPix() {
		return tipoPix;
	}
	public void setTipoPix(String tipoPix) {
		this.tipoPix = tipoPix;
	}
	public String getChavePix() {
		return chavePix;
	}
	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void deposita(Double valor, String codDep) {
		
		//se valor entrou na conta do sistema então this.saldo += valor;
		
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public int hashCode() {
		return Objects.hash(chavePix, id, saldo, tipoPix, usuario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(chavePix, other.chavePix) && Objects.equals(id, other.id)
				&& Objects.equals(saldo, other.saldo)
				&& Objects.equals(tipoPix, other.tipoPix) && Objects.equals(usuario, other.usuario);
	}
	

}
