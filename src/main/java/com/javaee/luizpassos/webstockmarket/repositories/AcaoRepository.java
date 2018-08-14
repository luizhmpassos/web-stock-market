package com.javaee.luizpassos.webstockmarket.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javaee.luizpassos.webstockmarket.domain.Acao;
import com.javaee.luizpassos.webstockmarket.domain.Comprador;
import com.javaee.luizpassos.webstockmarket.domain.Empresa;

public interface AcaoRepository extends CrudRepository<Acao, Long>{

	List<Acao> findByEmpresa(Empresa empresa);
	List<Acao> findByComprador(Comprador comprador);
	
}
