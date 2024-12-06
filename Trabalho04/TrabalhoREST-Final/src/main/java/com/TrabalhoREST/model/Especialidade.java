//classe model tem o objeto e suas informações
//essas informações serão sincronizadas com o bd

package com.TrabalhoREST.model;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Especialidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEspecialidade;

	@Column(nullable = false, unique = true)
	private String nomeEspecialidade;

	public Long getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(Long idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}

	public String getNomeEspecialidade() {
		return nomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEspecialidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especialidade other = (Especialidade) obj;
		return Objects.equals(idEspecialidade, other.idEspecialidade);
	}
}