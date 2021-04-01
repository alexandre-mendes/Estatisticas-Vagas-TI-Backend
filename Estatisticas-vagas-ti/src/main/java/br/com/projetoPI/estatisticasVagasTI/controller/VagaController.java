package br.com.projetoPI.estatisticasVagasTI.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoPI.estatisticasVagasTI.entity.Vaga;
import br.com.projetoPI.estatisticasVagasTI.service.VagaService;



@RestController
@RequestMapping(path = "/vagas")
public class VagaController {
	
	@Autowired
	private VagaService vagaService;
	
	@GetMapping(path = "/salvasAgora")
	public ResponseEntity<Iterable<Vaga>> obterVagas() {
		return vagaService.buscarVagas();
	};
	
	@GetMapping(path = "/base")
	public ResponseEntity<Iterable<Vaga>> obterVagasDaBase(){
		return vagaService.buscarVagasDaBase();
	}
	
}
