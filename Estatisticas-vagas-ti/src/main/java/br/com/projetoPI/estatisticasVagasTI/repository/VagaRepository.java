package br.com.projetoPI.estatisticasVagasTI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.projetoPI.estatisticasVagasTI.entity.Vaga;

public interface VagaRepository extends CrudRepository<Vaga, Long> {

	@Query(value = "select 				"
				+ "		v 				"
				+ "	from 				"
				+ "		Vaga v 			"
				+ "	where 				"
				+ "		v.url in (:urls)")
	public List<Vaga> obterVagasPorUrls(@Param(value = "urls") List<String> urls);
}
