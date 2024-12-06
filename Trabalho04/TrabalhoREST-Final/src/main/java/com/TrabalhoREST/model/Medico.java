package com.TrabalhoREST.model;
import java.util.Objects;
import com.TrabalhoREST.DTOs.DadosCadastroMedico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMedico;

	@Column(nullable = false)
	private String nomeMedico;

	@Column(nullable = false, length = 12, unique = true)
	private String crm;

	@ManyToOne
	@JoinColumn(name = "idEspecialidade")
	private Especialidade especialidade;

//	@OneToMany
//	@JoinColumn(name = "idPaciente")
//	private List<Paciente> listaPacientes;

	public Medico(DadosCadastroMedico dados, Especialidade especialidade) {
		this.nomeMedico = dados.nomeMedico();
		this.crm = dados.crm();
		this.especialidade = especialidade;
	}
	
	public Medico() {
	}

	public Long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		
		if (crm.length() == 12) {
			this.crm = crm;
		}
		
		//adicionar erro
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMedico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		return Objects.equals(idMedico, other.idMedico);
	}
}
