package com.javaee.luizpassos.webstockmarket.api.v1.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompradorDTO {

	private Long id;
	
	@NotBlank
	@Size(min=2, max=200)
	private String nome;
	
	@NotNull
	@Size(max=255)
	private String email;
		
	@NotEmpty
	private Set<AcaoDTO> acoes = new HashSet<>();	
	
}
