package com.felipe.setembro21.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.setembro21.model.CelularNumero;

@Repository
@Transactional
public interface CelularNumeroRepository extends CrudRepository<CelularNumero, Long>{

	/*exemplo
	 * @Query("select t from Telefone t where t.pessoa.id = ?1")	
   public List<Telefone> getTelefones(Long pessoaid);
   */
	
}
