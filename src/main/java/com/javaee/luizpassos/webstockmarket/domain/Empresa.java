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
public class Empresa {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String inscricao;
	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
	private Set<Acao> acoes = new HashSet<>();
	
	public void addAcao(Acao acao) {
		acao.setEmpresa(this);
		this.acoes.add(acao);
	}

}
