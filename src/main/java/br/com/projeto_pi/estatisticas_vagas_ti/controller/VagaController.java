package br.com.projeto_pi.estatisticas_vagas_ti.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto_pi.estatisticas_vagas_ti.dto.VagaDTO;
import br.com.projeto_pi.estatisticas_vagas_ti.service.VagaService;



@RestController
@RequestMapping(path = "/vagas")
@CrossOrigin(origins = "*")
public class VagaController {
	
	@Autowired
	private VagaService vagaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<VagaDTO>> obterVagasDaBase(){
		List<VagaDTO> vagasDTO = (List<VagaDTO>) vagaService.buscarVagasDaBase().stream().map(v -> modelMapper.map(v, VagaDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<List<VagaDTO>>(vagasDTO, HttpStatus.OK);
	}
	
}
