package com.spatial.funcionario;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.geolatte.geom.Geometry;

import com.querydsl.core.annotations.QueryProjection;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor 
public class Funcionario {
	
	@Id
	private Long id;
	
	private String nome;
	
	private Geometry localizacao;

	@QueryProjection
	public Funcionario(Long id, String nome, Geometry localizacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.localizacao = localizacao;
	}

	

}
