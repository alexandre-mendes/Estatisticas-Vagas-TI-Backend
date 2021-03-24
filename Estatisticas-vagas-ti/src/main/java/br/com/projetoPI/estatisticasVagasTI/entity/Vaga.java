package br.com.projetoPI.estatisticasVagasTI.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vagas")
public class Vaga implements Serializable {

	private static final long serialVersionUID = 1095751545050881629L;

	@Id
	@GeneratedValue(generator = "vagas_id_vag_seq", strategy = GenerationType.AUTO)
	@Column(name = "id_vag")
	private Long id;
	
	@Column(name = "car")
	private String cargo;
	
	@Column(name = "emp")
	private String empresa;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "dat")
	private LocalDate data;
	
	public Vaga() {}
	
	public Vaga(String cargo,String empresa,String url, LocalDate data) {
		this.cargo = cargo;
		this.empresa = empresa;
		this.url = url;
		this.data = data;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getcargo() {
		return cargo;
	}
	public void setcargo(String cargo) {
		this.cargo = cargo;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vaga other = (Vaga) obj;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	
}
