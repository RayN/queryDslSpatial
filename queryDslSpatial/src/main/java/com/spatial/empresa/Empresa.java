package com.spatial.empresa;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.geolatte.geom.Geometry;

import lombok.Data;

@Entity
@Data
public class Empresa {
	
	@Id
	private Long id;
	
	private Geometry localizacao;

}
