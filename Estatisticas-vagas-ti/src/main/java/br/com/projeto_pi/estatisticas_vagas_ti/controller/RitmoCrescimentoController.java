package br.com.projeto_pi.estatisticas_vagas_ti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto_pi.estatisticas_vagas_ti.dto.RitmoCrescimentoDTO;
import br.com.projeto_pi.estatisticas_vagas_ti.service.RitmoCrescimentoService;

@RestController
@RequestMapping(path = "/ritmo-crescimento")
@CrossOrigin(origins = "*")
public class RitmoCrescimentoController {
	
	@Autowired
	private RitmoCrescimentoService ritmoService;
	
	@GetMapping
	public ResponseEntity<List<RitmoCrescimentoDTO>> obterRitmosCrescimento() {
		List<RitmoCrescimentoDTO> ritmos = ritmoService.obterRitmosDeCrescimento();
		return new ResponseEntity<List<RitmoCrescimentoDTO>>(ritmos, HttpStatus.OK);
	}
}
