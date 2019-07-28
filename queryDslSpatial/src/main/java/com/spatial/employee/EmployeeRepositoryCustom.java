package com.spatial.employee;

import java.util.List;

import org.geolatte.geom.Geometry;

public interface EmployeeRepositoryCustom {

	Geometry getAllEmployeeLocations();

	List<Geometry> listCompanyLocationIntersection();

	List<Employee> listEmployeesWhoLiveCloseToTheCompany();

}
