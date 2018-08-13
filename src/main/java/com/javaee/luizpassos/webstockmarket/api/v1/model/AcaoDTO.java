package com.javaee.luizpassos.webstockmarket.api.v1.model;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
	
	private Long comprador;
	
	@NotNull
	private Long empresa;	
	
}
