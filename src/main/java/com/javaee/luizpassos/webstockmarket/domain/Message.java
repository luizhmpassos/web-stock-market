package com.javaee.luizpassos.webstockmarket.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long acaoId;
	private Long compradorId;
	
	private String subject;
	private String body;
	
}