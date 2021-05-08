package br.com.projeto_pi.estatisticas_vagas_ti.dto;

import br.com.projeto_pi.estatisticas_vagas_ti.enumerate.Linguagem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ModeloGraficoDTO {

	private Linguagem linguagem;
	private Integer quantidade;
}
