package com.javaee.luizpassos.webstockmarket.services;

import java.util.List;
import com.javaee.luizpassos.webstockmarket.api.v1.model.EmpresaDTO;


public interface EmpresaService {
	List<EmpresaDTO> getAll();

	EmpresaDTO getById(Long id);

	EmpresaDTO createNew(EmpresaDTO empresa);

	EmpresaDTO save(Long id, EmpresaDTO empresa);

	void deleteById(Long id);
}
