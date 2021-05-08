package br.com.projeto_pi.estatisticas_vagas_ti.dto;

import java.time.LocalDate;
import br.com.projeto_pi.estatisticas_vagas_ti.enumerate.Linguagem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class VagaDTO {

	private Long id;
	private String cargo;
	private String empresa;
	private String detalhes;
	private String url;
	private LocalDate data;
	private Linguagem linguagem;
}
