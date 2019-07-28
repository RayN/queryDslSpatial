package com.spatial.employee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.geolatte.geom.Geometry;

import com.querydsl.core.annotations.QueryProjection;
import com.spatial.company.Company;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor 
public class Employee {
	
	@Id
	private Long id;
	
	private String name;
	
	private Geometry location;
	
	@ManyToOne
	private Company company;

	@QueryProjection
	public Employee(Long id, String name, Geometry location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
	}

	

}
