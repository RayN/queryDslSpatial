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
import com.spatial.funcionario.FuncionarioRepositoryCustom;

@SpringBootApplication
public class SpatialTestsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpatialTestsApplication.class, args);
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	public SQLQuery sQLQueryFactory(DataSource dataSource) {
		return new SQLQueryFactory(new Configuration(PostGISTemplates.DEFAULT) , dataSource, false).query();
	}

	@Autowired
	private FuncionarioRepositoryCustom funcionarioRepositoryCustom;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(this.funcionarioRepositoryCustom.getUnionLocation());
		
		this.funcionarioRepositoryCustom.getLocalizacaoEmpresaInterseption()
											.forEach(System.out::println);
		
		this.funcionarioRepositoryCustom.readFuncionariosMoramPertoDaEmpresa()
											.forEach(System.out::println);
	}

}
