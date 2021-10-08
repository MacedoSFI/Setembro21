package com.felipe.setembro21.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.setembro21.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	
	  @Query(value="select u from Usuario u where upper(trim(u.nome)) like %?1%") List<Usuario>
	  buscarPorNome(String nome);
	  
	  @Query("select u from Usuario u where u.login = ?1")
	  Usuario findUserByLogin(String email);
	 
}
