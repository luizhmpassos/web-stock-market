package com.javaee.luizpassos.webstockmarket.api.v1.mapper;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.javaee.luizpassos.webstockmarket.api.v1.model.EmpresaDTO;
import com.javaee.luizpassos.webstockmarket.domain.Empresa;
import com.javaee.luizpassos.webstockmarket.domain.Acao;
import com.javaee.luizpassos.webstockmarket.repositories.AcaoRepository;

@Component
public class EmpresaMapper {

	private AcaoMapper acaoMapper;
	
	private AcaoRepository acaoRepository;
	
	
	public EmpresaMapper(AcaoMapper acaoMapper, AcaoRepository acaoRepository) {
		this.acaoMapper = acaoMapper;		
		this.acaoRepository = acaoRepository;		
	}

	public EmpresaDTO empresaToEmpresaDTO(Empresa empresa) {
		final EmpresaDTO empresaDTO = new EmpresaDTO();
		
		empresaDTO.setId(empresa.getId());
		empresaDTO.setNome(empresa.getNome());
		empresaDTO.setInscricao(empresa.getInscricao());
		
		//OnetoOne
		//empresaDTO.setAcoes(acoesMapper.acoesToAcoesDTO(empresa.getGasStation()));
		
		if (empresa.getAcoes() != null && empresa.getAcoes().size() > 0){
			empresa.getAcoes()
                    .forEach(acao -> empresaDTO.getAcoes().add(acaoMapper.acaoToAcaoDTO(acao)));
        }		
				
		return empresaDTO;
	}

	public Empresa empresaDTOToEmpresa(EmpresaDTO empresaDTO) {
		final Empresa empresa = new Empresa();
		empresa.setId(empresaDTO.getId());
		empresa.setNome(empresaDTO.getNome());
		empresa.setInscricao(empresaDTO.getInscricao());
		
		//GasStation detachedGasStation = fillGasStation(empresaDTO, empresa);
		fillAcoes(empresaDTO, empresa);		
		return empresa;
    }
	
	private void fillAcoes(EmpresaDTO empresaDTO, final Empresa empresa) {
		if (empresaDTO.getAcoes() != null && empresaDTO.getAcoes().size() > 0){
            Set<Acao> detachedAcoes = empresaDTO.getAcoes().stream()
            	.map(acaoDTO -> acaoMapper.acaoDTOToAcao(acaoDTO))
            	.collect(Collectors.toSet());
            
            detachedAcoes.forEach(acao -> {
            	Optional<Acao> acaoOptional = acaoRepository.findById(acao.getId());
            	if(acaoOptional.isPresent()) {
            		Acao acaoSaved = acaoOptional.get();
            		empresa.addAcao(acaoSaved);
            	}
            });
        }
	}

		
}
