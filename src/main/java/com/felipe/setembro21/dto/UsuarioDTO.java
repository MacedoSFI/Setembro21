package com.felipe.setembro21.dto;

import java.io.Serializable;

import com.felipe.setembro21.model.Usuario;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomeUserDTO;
	private String emailUserDTO;
	private String numCelularUserDTO;
	private Double saldoContaUserDTO;
	
	public UsuarioDTO(Usuario usuario) {
		this.nomeUserDTO = usuario.getNome();
		this.emailUserDTO = usuario.getEmail();
		this.numCelularUserDTO = usuario.getCelular().getNumeroComDDD();
		this.saldoContaUserDTO = usuario.getConta().getSaldo();
	}
	public String getNomeUserDTO() {
		return nomeUserDTO;
	}
	public void setNomeUserDTO(String nomeUserDTO) {
		this.nomeUserDTO = nomeUserDTO;
	}
	public String getEmailUserDTO() {
		return emailUserDTO;
	}
	public void setEmailUserDTO(String emailUserDTO) {
		this.emailUserDTO = emailUserDTO;
	}
	public String getNumCelularUserDTO() {
		return numCelularUserDTO;
	}
	public void setNumCelularUserDTO(String numCelularUserDTO) {
		this.numCelularUserDTO = numCelularUserDTO;
	}
	public Double getSaldoContaUserDTO() {
		return saldoContaUserDTO;
	}
	public void setSaldoContaUserDTO(Double saldoContaUserDTO) {
		this.saldoContaUserDTO = saldoContaUserDTO;
	}	

}
