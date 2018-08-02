package com.javaee.luizpassos.webstockmarket.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Acao {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String codigo;
	private Date compra;
	private Double valor_inicial;
	private Double valor_atual;
		
	//private Integer year;
	
	//@Enumerated(value = EnumType.STRING)
	//private FuelType fuelType;

	//@ManyToOne
    //private Garage garage;
	
}
