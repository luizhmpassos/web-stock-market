package com.javaee.luizpassos.webstockmarket.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javaee.luizpassos.webstockmarket.domain.Acao;
import com.javaee.luizpassos.webstockmarket.domain.Comprador;

public interface CompradorRepository extends CrudRepository<Comprador, Long>{
	List<Comprador> findByGarage(Acao acao);
}
