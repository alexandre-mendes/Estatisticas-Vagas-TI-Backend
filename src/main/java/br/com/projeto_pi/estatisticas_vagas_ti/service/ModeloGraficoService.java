package br.com.projeto_pi.estatisticas_vagas_ti.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.projeto_pi.estatisticas_vagas_ti.dto.ModeloGraficoDTO;

@Service
public interface ModeloGraficoService {

	public List<ModeloGraficoDTO> obterModelosParaGrafico();
}
