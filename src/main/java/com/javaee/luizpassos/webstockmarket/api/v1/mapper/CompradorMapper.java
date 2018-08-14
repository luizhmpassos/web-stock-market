package com.javaee.luizpassos.webstockmarket.api.v1.mapper;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.javaee.luizpassos.webstockmarket.api.v1.model.CompradorDTO;
import com.javaee.luizpassos.webstockmarket.domain.Acao;
import com.javaee.luizpassos.webstockmarket.domain.Comprador;
import com.javaee.luizpassos.webstockmarket.repositories.AcaoRepository;

@Component
public class CompradorMapper {
	
	private AcaoMapper acaoMapper;	
	private AcaoRepository acaoRepository;
	
	public CompradorMapper(AcaoMapper acaoMapper, AcaoRepository acaoRepository) {
		this.acaoMapper = acaoMapper;		
		this.acaoRepository = acaoRepository;		
	}
	
	public CompradorDTO compradorToCompradorDTO(Comprador comprador) {
		final CompradorDTO compradorDTO = new CompradorDTO();
		compradorDTO.setId(comprador.getId());
		compradorDTO.setNome(comprador.getNome());
		compradorDTO.setEmail(comprador.getEmail());
		

		if (comprador.getAcoes() != null && comprador.getAcoes().size() > 0){
			comprador.getAcoes()
                    .forEach(acao -> compradorDTO.getAcoes().add(acaoMapper.acaoToAcaoDTO(acao)));
        }		
		
		return compradorDTO;
	}

    public Comprador compradorDTOToComprador(CompradorDTO compradorDTO) {
    	final Comprador comprador = new Comprador();
		comprador.setId(compradorDTO.getId());
		comprador.setNome(compradorDTO.getNome());
		comprador.setEmail(compradorDTO.getEmail());	
		
		fillAcoes(compradorDTO, comprador);	
		
		return comprador;
    }
	
    
    private void fillAcoes(CompradorDTO compradorDTO, final Comprador comprador) {
		if (compradorDTO.getAcoes() != null && compradorDTO.getAcoes().size() > 0){
            Set<Acao> detachedAcoes = compradorDTO.getAcoes().stream()
            	.map(acaoDTO -> acaoMapper.acaoDTOToAcao(acaoDTO))
            	.collect(Collectors.toSet());
            
            detachedAcoes.forEach(acao -> {
            	Optional<Acao> acaoOptional = acaoRepository.findById(acao.getId());
            	if(acaoOptional.isPresent()) {
            		Acao acaoSaved = acaoOptional.get();
            		comprador.addAcao(acaoSaved);
            	}
            });
        }
	}
	
	
}
