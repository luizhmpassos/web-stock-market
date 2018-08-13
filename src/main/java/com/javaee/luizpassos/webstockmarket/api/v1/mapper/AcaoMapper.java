package com.javaee.luizpassos.webstockmarket.api.v1.mapper;

import java.util.Optional;

//import java.util.Optional;

import org.springframework.stereotype.Component;

import com.javaee.luizpassos.webstockmarket.api.v1.model.AcaoDTO;
import com.javaee.luizpassos.webstockmarket.domain.Acao;
import com.javaee.luizpassos.webstockmarket.domain.Empresa;
import com.javaee.luizpassos.webstockmarket.repositories.EmpresaRepository;
//import com.javaee.luizpassos.webstockmarket.repositories.CompradorRepository;
//import com.javaee.luizpassos.webstockmarket.repositories.EmpresaRepository;
//import com.javaee.luizpassos.webstockmarket.domain.Empresa;

@Component
public class AcaoMapper {
	
/*
	private EmpresaMapper empresaMapper;
	private CompradorMapper compradorMapper;
	*/	
	
	private EmpresaRepository empresaRepository;
	//private CompradorRepository compradorRepository;	
	
	public AcaoMapper(EmpresaRepository empresaRepository) {				
		this.empresaRepository = empresaRepository;
		//	this.compradorRepository = compradorRepository;		
	}
	
	
	public AcaoDTO acaoToAcaoDTO(Acao acao) {
		final AcaoDTO acaoDTO = new AcaoDTO();
		acaoDTO.setId(acao.getId());
		acaoDTO.setCodigo(acao.getCodigo());
		acaoDTO.setCompra(acao.getCompra());
		acaoDTO.setValor_inicial(acao.getValor_inicial());
		acaoDTO.setValor_atual(acao.getValor_atual());
		
		
		//acaoDTO.setComprador(acao.getComprador().getId());
		acaoDTO.setEmpresa(acao.getEmpresa().getId());
		return acaoDTO;
	}

	
	public Acao acaoDTOToAcao(AcaoDTO acaoDTO) {
		final Acao acao= new Acao();
				
		acao.setId(acaoDTO.getId());
		acao.setCodigo(acaoDTO.getCodigo());
		acao.setCompra(acaoDTO.getCompra());
		acao.setValor_inicial(acaoDTO.getValor_inicial());
		acao.setValor_atual(acaoDTO.getValor_atual());		
				
		acao.setEmpresa(idToEmpresa(acaoDTO.getEmpresa()));
		
		return acao;
    }

	
	private Empresa idToEmpresa(Long idEmpresa){		
		Optional<Empresa> empresaOptional = empresaRepository.findById(idEmpresa);
		if (!empresaOptional.isPresent()) {
            throw new IllegalArgumentException("Empresa não cadastrada: " + idEmpresa.toString() );
        }
        return empresaOptional.get();
	}
	
	

	/*
	private void fillVehicles(GarageDTO garageDTO, final Garage garage, GasStation detachedGasStation) {
		if (garageDTO.getVehicles() != null && garageDTO.getVehicles().size() > 0){
			Set<Vehicle> detachedVehicles = garageDTO.getVehicles().stream()
	            	.map(vehicleDTO -> vehicleMapper.vehicleDTOToVehicle(vehicleDTO))
	            	.collect(Collectors.toSet());
			
			detachedVehicles.forEach(vehicle -> {
				if(vehicle.getId() != null) {
					Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicle.getId());
					if(!vehicleOptional.isPresent()) {
						throw new IllegalArgumentException("Vehicle Not Found For ID value: " + detachedGasStation.getId());
					}
					Vehicle vehicleSaved = vehicleOptional.get();
					garage.addVehicle(vehicleSaved);
				} else {
					garage.addVehicle(vehicle);
				}
			});
        }
	}
	*/
	
}
