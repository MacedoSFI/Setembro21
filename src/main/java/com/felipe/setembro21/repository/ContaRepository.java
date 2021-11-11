package com.felipe.setembro21.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.setembro21.model.Conta;

@Repository
@Transactional
public interface ContaRepository extends CrudRepository<Conta, Long> {

}
