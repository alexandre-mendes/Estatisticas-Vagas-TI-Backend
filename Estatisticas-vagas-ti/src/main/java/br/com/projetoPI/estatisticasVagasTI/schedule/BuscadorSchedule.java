package br.com.projetoPI.estatisticasVagasTI.schedule;

import java.time.LocalDateTime;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.projetoPI.estatisticasVagasTI.entity.Vaga;
import br.com.projetoPI.estatisticasVagasTI.service.VagaService;

@Component 
@EnableScheduling 
public class BuscadorSchedule {
	
	@Autowired
	private VagaService vagaService;

	@Scheduled(fixedDelay = 3600000)
	public void buscarVagas() {
		try {
			System.out.println("Iniciando busca de vagas " + LocalDateTime.now());
			Iterator<Vaga> vagas = vagaService.buscarVagas().getBody().iterator();
			while (vagas.hasNext()) {
				System.out.println(vagas.next().getUrl());
			}
			System.out.println("Busca finalizada sem erros " + LocalDateTime.now());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro durante a busca de vagas. " + e.getMessage());
		}
	}
}
