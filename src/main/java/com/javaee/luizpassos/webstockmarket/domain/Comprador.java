package com.javaee.luizpassos.webstockmarket.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comprador {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nome;
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "comprador")
	private Set<Acao> acoes = new HashSet<>();
	
	public void addAcao(Acao acao) {
		acao.setComprador(this);
		this.acoes.add(acao);
	}
	
}
