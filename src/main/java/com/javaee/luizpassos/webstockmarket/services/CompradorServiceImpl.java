package com.javaee.luizpassos.webstockmarket.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javaee.luizpassos.webstockmarket.api.v1.mapper.CompradorMapper;
import com.javaee.luizpassos.webstockmarket.api.v1.model.CompradorDTO;
import com.javaee.luizpassos.webstockmarket.domain.Acao;
import com.javaee.luizpassos.webstockmarket.domain.Comprador;
import com.javaee.luizpassos.webstockmarket.repositories.AcaoRepository;
import com.javaee.luizpassos.webstockmarket.repositories.CompradorRepository;

@Service
public class CompradorServiceImpl implements CompradorService{

	private CompradorRepository compradorRepository;	
	private AcaoRepository acaoRepository;
	
	private final CompradorMapper compradorMapper;
	
	public CompradorServiceImpl(CompradorRepository compradorRepository,
							 AcaoRepository acaoRepository,							 
							 CompradorMapper compradorMapper) {
		this.compradorRepository = compradorRepository;
		this.compradorMapper = compradorMapper;
		this.acaoRepository = acaoRepository;		
	}

	@Override
	public List<CompradorDTO> getAll() {
		return StreamSupport.stream(this.compradorRepository.findAll()
				.spliterator(), false)
				.map(compradorMapper::compradorToCompradorDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CompradorDTO getById(Long id) {
		Comprador comprador = getCompradorById(id);
		return compradorMapper.compradorToCompradorDTO(comprador);
	}
	
	private Comprador getCompradorById(Long id) {
		Optional<Comprador> compradorOptional = compradorRepository.findById(id);

        if (!compradorOptional.isPresent()) {
            throw new IllegalArgumentException("Comprador não encontrado para o ID: " + id.toString() );
        }
        return compradorOptional.get();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CompradorDTO createNew(CompradorDTO compradorDTO) {
		Comprador detachedComprador = compradorMapper.compradorDTOToComprador(compradorDTO);
		Comprador compradorSaved = compradorRepository.save(detachedComprador);
		return compradorMapper.compradorToCompradorDTO(compradorSaved);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CompradorDTO save(Long id, CompradorDTO compradorDTO) {
		Comprador detachedComprador = compradorMapper.compradorDTOToComprador(compradorDTO);
		detachedComprador.setId(id);
		Comprador comprador = getCompradorById(id);	
		
		//adjustAcoes(detachedComprador, comprador);
		
		Comprador compradorSaved = compradorRepository.save(detachedComprador);
		return compradorMapper.compradorToCompradorDTO(compradorSaved);
	}

	private void adjustAcoes(Comprador detachedComprador, Comprador comprador) {
		List<Acao> acoes = acaoRepository.findByComprador(comprador);
		List<Acao> acoesToDelete = acoes.stream()
				.filter(acao -> !detachedComprador.getAcoes().contains(acao))
				.collect(Collectors.toList());
		
		// Deletar relação na tabela de Ações
		acaoRepository.deleteAll(acoesToDelete);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(Long id) {
		compradorRepository.deleteById(id);
		
		// Deletar relação na tabela de Ações
	}
	
	
}
