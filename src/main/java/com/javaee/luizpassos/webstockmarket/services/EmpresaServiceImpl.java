package com.javaee.luizpassos.webstockmarket.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javaee.luizpassos.webstockmarket.api.v1.mapper.EmpresaMapper;
import com.javaee.luizpassos.webstockmarket.api.v1.model.EmpresaDTO;
import com.javaee.luizpassos.webstockmarket.domain.Acao;
import com.javaee.luizpassos.webstockmarket.domain.Empresa;
import com.javaee.luizpassos.webstockmarket.repositories.AcaoRepository;
import com.javaee.luizpassos.webstockmarket.repositories.EmpresaRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private EmpresaRepository empresaRepository;	
	private AcaoRepository acaoRepository;
	
	private final EmpresaMapper empresaMapper;
	
	public EmpresaServiceImpl(EmpresaRepository empresaRepository,
							 AcaoRepository acaoRepository,							 
							 EmpresaMapper empresaMapper) {
		this.empresaRepository = empresaRepository;
		this.empresaMapper = empresaMapper;
		this.acaoRepository = acaoRepository;		
	}

	@Override
	public List<EmpresaDTO> getAll() {
		return StreamSupport.stream(this.empresaRepository.findAll()
				.spliterator(), false)
				.map(empresaMapper::empresaToEmpresaDTO)
				.collect(Collectors.toList());
	}

	@Override
	public EmpresaDTO getById(Long id) {
		Empresa empresa = getEmpresaById(id);
		return empresaMapper.empresaToEmpresaDTO(empresa);
	}
	
	private Empresa getEmpresaById(Long id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);

        if (!empresaOptional.isPresent()) {
            throw new IllegalArgumentException("Empresa não encontrada para o ID: " + id.toString() );
        }
        return empresaOptional.get();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public EmpresaDTO createNew(EmpresaDTO empresaDTO) {
		Empresa detachedEmpresa = empresaMapper.empresaDTOToEmpresa(empresaDTO);
		Empresa empresaSaved = empresaRepository.save(detachedEmpresa);
		return empresaMapper.empresaToEmpresaDTO(empresaSaved);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public EmpresaDTO save(Long id, EmpresaDTO empresaDTO) {
		Empresa detachedEmpresa = empresaMapper.empresaDTOToEmpresa(empresaDTO);
		detachedEmpresa.setId(id);
		Empresa empresa = getEmpresaById(id);	
		
		adjustAcoes(detachedEmpresa, empresa);
		
		Empresa empresaSaved = empresaRepository.save(detachedEmpresa);
		return empresaMapper.empresaToEmpresaDTO(empresaSaved);
	}

	private void adjustAcoes(Empresa detachedEmpresa, Empresa empresa) {
		List<Acao> acoes = acaoRepository.findByEmpresa(empresa);
		List<Acao> acoesToDelete = acoes.stream()
				.filter(acao -> !detachedEmpresa.getAcoes().contains(acao))
				.collect(Collectors.toList());
		acaoRepository.deleteAll(acoesToDelete);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(Long id) {
		empresaRepository.deleteById(id);
	}
	
	
}
