package com.felipe.setembro21.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id") //www.baeldung.com/jpa-one-to-one
	private Long id;
	
	private String nome;
	
	private String email;//estou considerando o e-mail como username do UserDetails
	
	private Date dtNascimento;
	
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta", referencedColumnName = "id")
	private Conta conta;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn //assim o obj celular não vai aparecer no res. da busca de usuario com json
    private CelularNumero celular;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuarios_role", uniqueConstraints = @UniqueConstraint(
			columnNames = {"usuario_id", "role_id"}, name="unique_role_user"),
	joinColumns = @JoinColumn(name="usuario_id", referencedColumnName = "id", table="usuario", unique = false,
	foreignKey = @ForeignKey(name="usuario_fk", value= ConstraintMode.CONSTRAINT)),
			inverseJoinColumns = @JoinColumn(name="role_id", unique=false,
			referencedColumnName = "id", updatable=false,
			table="role", foreignKey=@ForeignKey(name="role_fk", value=ConstraintMode.CONSTRAINT)))
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/* Alex passou tudo para true*/
	@Override
	public int hashCode() {
		return Objects.hash(conta, dtNascimento, email, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(conta, other.conta) && Objects.equals(dtNascimento, other.dtNascimento)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}

	@Override  // classe Role implementa GrantedAuthority
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
