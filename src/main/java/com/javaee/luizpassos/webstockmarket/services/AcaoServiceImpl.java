package com.javaee.luizpassos.webstockmarket.services;

import java.util.Date;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javaee.luizpassos.emailsender.EmailSender;
import com.javaee.luizpassos.webstockmarket.api.v1.mapper.AcaoMapper;
import com.javaee.luizpassos.webstockmarket.api.v1.model.AcaoDTO;
import com.javaee.luizpassos.webstockmarket.domain.Acao;
import com.javaee.luizpassos.webstockmarket.domain.Comprador;
import com.javaee.luizpassos.webstockmarket.repositories.AcaoRepository;


@Service
public class AcaoServiceImpl implements AcaoService {
		
	private AcaoRepository acaoRepository;	
	private final AcaoMapper acaoMapper;
	
	public AcaoServiceImpl( AcaoRepository acaoRepository,							
							 AcaoMapper acaoMapper) {		
		this.acaoMapper = acaoMapper;
		this.acaoRepository = acaoRepository;	
		
	}

	@Override
	public List<AcaoDTO> getAll() {
		return StreamSupport.stream(this.acaoRepository.findAll()
				.spliterator(), false)
				.map(acaoMapper::acaoToAcaoDTO)
				.collect(Collectors.toList());
	}

	@Override
	public AcaoDTO getById(Long id) {
		Acao acao = getAcaoById(id);
		return acaoMapper.acaoToAcaoDTO(acao);
	}
	
	private Acao getAcaoById(Long id) {
		Optional<Acao> acaoOptional = acaoRepository.findById(id);

        if (!acaoOptional.isPresent()) {
            throw new IllegalArgumentException("Acao não encontrada para o ID: " + id.toString() );
        }       
        
        return acaoOptional.get();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public AcaoDTO createNew(AcaoDTO acaoDTO) {
		Acao detachedAcao = acaoMapper.acaoDTOToAcao(acaoDTO);		
		Acao acaoSaved = acaoRepository.save(detachedAcao);
		
		return acaoMapper.acaoToAcaoDTO(acaoSaved);
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public AcaoDTO save(Long id, AcaoDTO acaoDTO) {
		Acao detachedAcao = acaoMapper.acaoDTOToAcao(acaoDTO);
		detachedAcao.setId(id);
		Acao acaoSaved = acaoRepository.save(detachedAcao);
		return acaoMapper.acaoToAcaoDTO(acaoSaved);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public AcaoDTO transfer(Long id, AcaoDTO acaoDTO){
		
		Acao acaoOLD = getAcaoById(id);
		Acao acaoNEW = acaoMapper.acaoDTOToAcao(acaoDTO);
				
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date utilDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());				
		
		acaoNEW.setId(id);
		acaoNEW.setCodigo(acaoOLD.getCodigo());
		acaoNEW.setCompra(sqlDate);
		acaoNEW.setValor_inicial(acaoOLD.getValor_atual());
		acaoNEW.setValor_atual(acaoOLD.getValor_atual());
		acaoNEW.setEmpresa(acaoOLD.getEmpresa());	
						
		System.out.println(sqlDate.toString());
				
		Comprador compradorOLD = acaoOLD.getComprador();
		Comprador compradorNEW = acaoNEW.getComprador(); 
		
		EmailSender email = new EmailSender();
		
		String body = "Transação Efetivada"; 
		body += "Ação: " + acaoNEW.getCodigo() + "\n";
		body += "Vendida por: " + compradorOLD.getNome() + "\n";
		body += "Comprada por: " + compradorNEW.getNome() + "\n";
		body += "Valor: " + acaoNEW.getValor_atual() + "\n";
		body += "Data da transação: " + utilDate.toString() + "\n";
		
		email.setSubject("Webstockmarket: Operação Realizada");
		email.setBody(body);
		
		
		String recipients = compradorNEW.getEmail() + "," + compradorOLD.getEmail();
		email.setToEmail(recipients);
		
		email.send();
		
		Acao acaoSaved = acaoRepository.save(acaoNEW);
		return acaoMapper.acaoToAcaoDTO(acaoSaved);		
	}
	

}
