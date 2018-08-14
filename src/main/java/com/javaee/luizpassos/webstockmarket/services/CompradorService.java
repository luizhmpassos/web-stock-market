package com.javaee.luizpassos.webstockmarket.services;

import java.util.List;

import com.javaee.luizpassos.webstockmarket.api.v1.model.CompradorDTO;

public interface CompradorService {
	List<CompradorDTO> getAll();

	CompradorDTO getById(Long id);

	CompradorDTO createNew(CompradorDTO comprador);

	CompradorDTO save(Long id, CompradorDTO comprador);

	void deleteById(Long id);
}
