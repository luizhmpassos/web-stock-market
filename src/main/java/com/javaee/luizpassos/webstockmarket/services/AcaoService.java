package com.javaee.luizpassos.webstockmarket.services;

import java.util.List;

import com.javaee.luizpassos.webstockmarket.api.v1.model.AcaoDTO;

public interface AcaoService {
	List<AcaoDTO> getAll();

	AcaoDTO getById(Long id);

	AcaoDTO createNew(AcaoDTO acao);

	AcaoDTO save(Long id, AcaoDTO acao);

	AcaoDTO transfer(Long id, AcaoDTO acao);
	//void deleteById(Long id);
}
