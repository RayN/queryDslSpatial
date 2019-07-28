package com.spatial.funcionario;

import java.util.List;

import org.geolatte.geom.Geometry;

public interface FuncionarioRepositoryCustom {

	Geometry getUnionLocation();

	List<Geometry> getLocalizacaoEmpresaInterseption();

	List<Funcionario> readFuncionariosMoramPertoDaEmpresa();

}
