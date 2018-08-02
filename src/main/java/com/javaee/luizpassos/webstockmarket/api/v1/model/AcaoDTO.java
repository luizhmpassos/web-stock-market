package com.javaee.luizpassos.webstockmarket.api.v1.model;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AcaoDTO {

	private Long id;
	
	@NotBlank
	@Size(min=3, max=255)
	private String codigo;
	
	
	private Date compra;

	@NotNull
	private Double valor_inicial;
	
	@NotNull
	private Double valor_atual;
	
	private CompradorDTO comprador;
	private EmpresaDTO empresa;	
	
}
