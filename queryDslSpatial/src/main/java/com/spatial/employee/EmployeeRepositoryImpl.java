package com.spatial.employee;

import static com.querydsl.spatial.GeometryExpressions.geometryOperation;
import static com.querydsl.spatial.SpatialOps.UNION;

import java.util.List;

import javax.transaction.Transactional;

import org.geolatte.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.sql.SQLQuery;
import com.spatial.company.QCompany;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom{
	
	
	@Autowired
	SQLQuery<Employee> sqlQuery;
	
	QEmployee qEmployee = QEmployee.employee;
	QCompany qCompany = QCompany.company;
	
	@Override
	public List<Employee> listEmployeesWhoLiveCloseToTheCompany(){
		
		return sqlQuery
					.from(qEmployee)
					.innerJoin(qCompany)
						.on(qEmployee.id.eq(qCompany.id))
					.where(qEmployee.location.intersects(qCompany.location))
					.select(QEmployee.create(qEmployee.id, qEmployee.name, qEmployee.location))
					.fetch();
	
	}
	
	@Override
	public List<Geometry> listCompanyLocationIntersection(){
		
		return sqlQuery
				.from(qEmployee)
				.innerJoin(qCompany)
					.on(qEmployee.id.eq(qCompany.id))
				.select(qEmployee.location.intersection(qCompany.location))
				.fetch();
		
		
	}
	
	@Override
	public Geometry getAllEmployeeLocations(){
		
		return sqlQuery
				.from(qEmployee)
				.where(nameEqualsChefe())
				.select(geometryOperation(UNION, qEmployee.location))
				.fetchOne();
		
		
	}

	private BooleanExpression nameEqualsChefe() {
		return qEmployee.name.eq("chefe");
	}
	
}
