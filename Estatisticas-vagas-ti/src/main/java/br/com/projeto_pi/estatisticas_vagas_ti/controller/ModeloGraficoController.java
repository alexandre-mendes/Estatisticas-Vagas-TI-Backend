package br.com.projeto_pi.estatisticas_vagas_ti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto_pi.estatisticas_vagas_ti.dto.ModeloGraficoDTO;
import br.com.projeto_pi.estatisticas_vagas_ti.service.ModeloGraficoService;

@RestController
@RequestMapping(path = "/modelo-grafico")
@CrossOrigin(origins = "*")
public class ModeloGraficoController {
	
	@Autowired
	private ModeloGraficoService modeloService;

	@GetMapping
	public ResponseEntity<List<ModeloGraficoDTO>> obterModelosParaGrafico() {
		List<ModeloGraficoDTO> modelos = modeloService.obterModelosParaGrafico();
		return new ResponseEntity<List<ModeloGraficoDTO>>(modelos, HttpStatus.OK);
	}
}
