package br.com.projeto_pi.estatisticas_vagas_ti.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto_pi.estatisticas_vagas_ti.dto.ModeloGraficoDTO;
import br.com.projeto_pi.estatisticas_vagas_ti.entity.Vaga;
import br.com.projeto_pi.estatisticas_vagas_ti.enumerate.Linguagem;

@Service
public class ModeloGraficoServiceImpl implements ModeloGraficoService {

	private VagaService vagaService;
	
	@Autowired
	public ModeloGraficoServiceImpl(VagaService vagaService) {
		this.vagaService = vagaService;
	}
	
	public List<ModeloGraficoDTO> obterModelosParaGrafico() {
		Map<Linguagem, List<Vaga>> vagasAgrupadasPorLinguagem = new HashMap<>();
		List<ModeloGraficoDTO> modelos = new ArrayList<>();
		List<Vaga> vagas = vagaService.buscarVagasDaBase();
		
		vagasAgrupadasPorLinguagem = vagas.stream()
				.collect(Collectors.groupingBy(Vaga::getLinguagem));
		
		for(Linguagem linguagem : Linguagem.values()) {
			Integer quantidade;
			
			try {
				quantidade = vagasAgrupadasPorLinguagem.get(linguagem).size();
			} catch (NullPointerException e) {
				quantidade = 0;
			}
			
			ModeloGraficoDTO modelo = new ModeloGraficoDTO();
			modelo.setLinguagem(linguagem);
			modelo.setQuantidade(quantidade);
			
			modelos.add(modelo);
		}
		
		return modelos;
	}
}
