package br.com.projeto_pi.estatisticas_vagas_ti.dto;

import java.math.BigDecimal;

import br.com.projeto_pi.estatisticas_vagas_ti.enumerate.Linguagem;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class RitmoCrescimentoDTO {

	private Linguagem linguagem;
	private BigDecimal percentual;
}
