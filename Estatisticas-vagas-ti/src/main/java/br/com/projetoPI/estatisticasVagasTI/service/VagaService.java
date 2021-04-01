package br.com.projetoPI.estatisticasVagasTI.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projetoPI.estatisticasVagasTI.entity.Vaga;

@Service
public interface VagaService {

	ResponseEntity<Iterable<Vaga>> buscarVagas();
	
	ResponseEntity<Iterable<Vaga>> buscarVagasDaBase();
}
