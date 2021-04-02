package br.com.projetoPI.estatisticasVagasTI.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import enumerate.Linguagem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Table(name = "vagas")
public class Vaga implements Serializable {

	private static final long serialVersionUID = 1095751545050881629L;

	@Id
	@GeneratedValue(generator = "vagas_id_vag_seq", strategy = GenerationType.AUTO)
	@Column(name = "id_vag")
	@Getter 
	private Long id;
	
	@Column(name = "car")
	@Getter 
	@Setter 
	private String cargo;
	
	@Column(name = "emp")
	@Getter 
	@Setter private String empresa;
	
	@Column(name = "url")
	@Getter
	@Setter
	private String url;
	
	@Column(name = "dat")
	@Getter 
	@Setter private LocalDate data;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "lin")
	@Getter 
	@Setter 
	
	private Linguagem linguagem;
	
	public Vaga(String cargo,String empresa,String url, LocalDate data, Linguagem linguagem ) {
		this.cargo = cargo;
		this.empresa = empresa;
		this.url = url;
		this.data = data;
		this.linguagem = linguagem;
	}
	
	
}
	

