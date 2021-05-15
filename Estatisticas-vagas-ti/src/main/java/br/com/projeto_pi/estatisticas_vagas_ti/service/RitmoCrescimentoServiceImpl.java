package br.com.projeto_pi.estatisticas_vagas_ti.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto_pi.estatisticas_vagas_ti.dto.RitmoCrescimentoDTO;
import br.com.projeto_pi.estatisticas_vagas_ti.entity.Vaga;
import br.com.projeto_pi.estatisticas_vagas_ti.enumerate.Linguagem;
import br.com.projeto_pi.estatisticas_vagas_ti.repository.VagaRepository;

@Service
public class RitmoCrescimentoServiceImpl implements RitmoCrescimentoService {
	
	@Autowired
	private VagaRepository vagaRepository;

	public List<RitmoCrescimentoDTO> obterRitmosDeCrescimento() {
		List<RitmoCrescimentoDTO> ritmos = new ArrayList<RitmoCrescimentoDTO>();
		List<Vaga> vagas;
		Map<Integer, List<Vaga>> vagasAgrupadasPorMes;
		
		LocalDate dataAtual = LocalDate.now();
		LocalDate dataAnterior = LocalDate.of(dataAtual.getYear(), 
											  dataAtual.minusMonths(1).getMonthValue(), 
											  1);
		
		vagas = vagaRepository.findByDataBetween(dataAnterior, dataAtual);
		vagasAgrupadasPorMes = vagas.stream().collect(Collectors.groupingBy(vaga -> vaga.getData().getMonthValue()));
	
		for(Linguagem linguagem : Linguagem.values()) {
			ritmos.add(criarRitmoCrescimento(linguagem, 
								  dataAtual, 
								  dataAnterior, 
								  vagasAgrupadasPorMes));
		}
		
		return ritmos;
	}
	
	private RitmoCrescimentoDTO criarRitmoCrescimento(Linguagem linguagem, 
									 LocalDate dataAtual, 
									 LocalDate dataAnterior, 
									 Map<Integer, List<Vaga>> vagasAgrupadasPorMes) {
		
		BigDecimal quantidadeMesAtual = filtrarQuantidade(vagasAgrupadasPorMes.get(dataAtual.getMonthValue()), 
														  dataAtual.getDayOfMonth(), 
														  linguagem);
				
		BigDecimal quantidadeMesAnterior = filtrarQuantidade(vagasAgrupadasPorMes.get(dataAnterior.getMonthValue()), 
															 dataAtual.getDayOfMonth(), 
															 linguagem);
		
		BigDecimal percentual;
		
		try {
			percentual = quantidadeMesAtual.multiply(BigDecimal.valueOf(100))
											.divide(quantidadeMesAnterior, 2, RoundingMode.DOWN)
											.subtract(BigDecimal.valueOf(100));
		} catch (ArithmeticException e) {
			percentual = BigDecimal.ZERO;
		}
		
		return new RitmoCrescimentoDTO(linguagem, percentual);
	}
	
	private BigDecimal filtrarQuantidade(List<Vaga> vagasDoMes, Integer diaMaximo, Linguagem linguagem) {
		Long quantidade = vagasDoMes.stream()
				.filter(vaga -> vaga.getData().getDayOfMonth() <= diaMaximo 
								&& vaga.getLinguagem().equals(linguagem))
				.count();
		
		return BigDecimal.valueOf(quantidade);
	}
}
