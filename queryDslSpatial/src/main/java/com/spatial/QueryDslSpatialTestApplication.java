package com.spatial;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.querydsl.sql.Configuration;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.spatial.PostGISTemplates;
import com.spatial.employee.EmployeeRepositoryCustom;

@SpringBootApplication
public class QueryDslSpatialTestApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(QueryDslSpatialTestApplication.class, args);
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	public SQLQuery sQLQueryFactory(DataSource dataSource) {
		return new SQLQueryFactory(new Configuration(PostGISTemplates.DEFAULT) , dataSource, false).query();
	}

	@Autowired
	private EmployeeRepositoryCustom employeeRepositoryCustom;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(this.employeeRepositoryCustom.getAllEmployeeLocations());
		
		this.employeeRepositoryCustom.listCompanyLocationIntersection()
											.forEach(System.out::println);
		
		this.employeeRepositoryCustom.listEmployeesWhoLiveCloseToTheCompany()
											.forEach(System.out::println);
	}

}
