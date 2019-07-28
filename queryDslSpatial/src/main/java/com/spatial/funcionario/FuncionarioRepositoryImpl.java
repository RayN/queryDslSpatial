package com.spatial.funcionario;

import static com.querydsl.spatial.GeometryExpressions.geometryOperation;
import static com.querydsl.spatial.SpatialOps.UNION;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.geolatte.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.sql.SQLQuery;
import com.spatial.empresa.QEmpresa;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class FuncionarioRepositoryImpl implements FuncionarioRepositoryCustom{
	
	@PersistenceContext
    EntityManager entityManager;
	
	@Autowired
	SQLQuery<Funcionario> sqlQuery;
	
	QFuncionario qFuncionario = QFuncionario.funcionario;
	QEmpresa qEmpresa = QEmpresa.empresa;
	
	@Override
	public List<Funcionario> readFuncionariosMoramPertoDaEmpresa(){
		
		return sqlQuery
					.from(qFuncionario)
					.innerJoin(qEmpresa)
						.on(qFuncionario.id.eq(qEmpresa.id))
					.where(qFuncionario.localizacao.intersects(qEmpresa.localizacao))
					.select(QFuncionario.create(qFuncionario.id, qFuncionario.nome, qFuncionario.localizacao))
					.fetch();
	
	}
	
	@Override
	public List<Geometry> getLocalizacaoEmpresaInterseption(){
		
		return sqlQuery
				.from(qFuncionario)
				.innerJoin(qEmpresa)
					.on(qFuncionario.id.eq(qEmpresa.id))
				.select(qFuncionario.localizacao.intersection(qEmpresa.localizacao))
				.fetch();
		
		
	}
	
	@Override
	public Geometry getUnionLocation(){
		
		return sqlQuery
				.from(qFuncionario)
				.where(nomeIgualAChefe())
				.select(geometryOperation(UNION, qFuncionario.localizacao))
				.fetchOne();
		
		
	}

	private BooleanExpression nomeIgualAChefe() {
		return qFuncionario.nome.eq("chefe");
	}
	
}
