package com.javaee.luizpassos.webstockmarket.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javaee.luizpassos.webstockmarket.domain.Acao;
import com.javaee.luizpassos.webstockmarket.domain.Empresa;



public interface EmpresaRepository extends CrudRepository<Empresa, Long>{
	List<Empresa> findByGarage(Acao acao);
}
