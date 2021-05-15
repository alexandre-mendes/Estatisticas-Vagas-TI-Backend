package br.com.projeto_pi.estatisticas_vagas_ti.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.projeto_pi.estatisticas_vagas_ti.dto.RitmoCrescimentoDTO;

@Service
public interface RitmoCrescimentoService {

	List<RitmoCrescimentoDTO> obterRitmosDeCrescimento();
}
