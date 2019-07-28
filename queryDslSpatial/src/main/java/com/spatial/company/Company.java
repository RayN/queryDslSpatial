package com.spatial.company;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.geolatte.geom.Geometry;

import com.spatial.employee.Employee;

import lombok.Data;

@Entity
@Data
public class Company {
	
	@Id
	private Long id;
	
	private Geometry location;
	
	@OneToMany
	private List<Employee> employees;

}
