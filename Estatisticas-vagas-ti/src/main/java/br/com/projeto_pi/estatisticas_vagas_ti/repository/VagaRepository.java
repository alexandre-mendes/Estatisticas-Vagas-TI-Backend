package br.com.projeto_pi.estatisticas_vagas_ti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projeto_pi.estatisticas_vagas_ti.entity.Vaga;
import br.com.projeto_pi.estatisticas_vagas_ti.enumerate.Linguagem;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

	@Query(value = "select 				"
				+ "		v 				"
				+ "	from 				"
				+ "		Vaga v 			"
				+ "	where 				"
				+ "		v.url in (:urls)"
				+ "	and 				"
				+ "		v.linguagem = :linguagem")
	public List<Vaga> obterVagasPorUrls(@Param(value = "urls") List<String> urls,@Param(value = "linguagem") Linguagem linguagem); 
}
