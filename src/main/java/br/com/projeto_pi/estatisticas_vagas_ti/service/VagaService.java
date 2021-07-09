package br.com.projeto_pi.estatisticas_vagas_ti.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.projeto_pi.estatisticas_vagas_ti.entity.Vaga;

@Service
public interface VagaService {

	List<Vaga> buscarVagas();
	
	List<Vaga> buscarVagasDaBase();
}
