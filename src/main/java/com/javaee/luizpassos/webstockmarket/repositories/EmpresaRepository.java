package com.javaee.luizpassos.webstockmarket.repositories;



import org.springframework.data.repository.CrudRepository;


import com.javaee.luizpassos.webstockmarket.domain.Empresa;



public interface EmpresaRepository extends CrudRepository<Empresa, Long>{
	
}
