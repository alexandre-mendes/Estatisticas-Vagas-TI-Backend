package br.com.projeto_pi.estatisticas_vagas_ti.schedule;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.projeto_pi.estatisticas_vagas_ti.entity.Vaga;
import br.com.projeto_pi.estatisticas_vagas_ti.service.VagaService;

@Component 
@EnableScheduling 
public class BuscadorSchedule {
	
	@Autowired
	private VagaService vagaService;

	@Scheduled(fixedDelay = 3600000)
	public void buscarVagas() {
		try {
			System.out.println("Iniciando busca de vagas " + LocalDateTime.now());
			List<Vaga> vagas = vagaService.buscarVagas();
			for(Vaga vaga : vagas) {
				System.out.println(vaga.getUrl());
			}
			System.out.println("Busca finalizada sem erros " + LocalDateTime.now());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro durante a busca de vagas. " + e.getMessage());
		}
	}
}
