package com.javaee.luizpassos.webstockmarket.api.v1.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaDTO {
	
	private Long id;

	@NotBlank
	@Size(max=30)
	private String inscricao;
	
	@NotBlank
	@Size(min=3, max=255)
	private String nome;
	
	
	@NotEmpty
	private Set<AcaoDTO> acoes = new HashSet<>();
	
}
