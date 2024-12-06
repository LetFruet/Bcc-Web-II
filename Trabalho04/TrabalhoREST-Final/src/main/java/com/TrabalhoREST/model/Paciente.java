package com.TrabalhoREST.model;
import java.sql.Date;
import java.util.Objects;
import com.TrabalhoREST.DTOs.DadosCadastroPaciente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPaciente;

	@Column(nullable = false)
	private String nomePaciente;

	@Column(nullable = false, length = 14, unique = true)
	private String cpf;

	@Column(nullable = false)
	private Date dataNasc;

	@ManyToOne
	@JoinColumn(name = "idMedico")
	private Medico medico;
	
	public Paciente(DadosCadastroPaciente dados, Medico medico) {
		this.nomePaciente = dados.nomePaciente();
		this.cpf = dados.cpf();
		this.dataNasc = dados.dataNasc();
		this.medico = medico;
	}
	
	public Paciente() {
	}
	
	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		
		if (cpf.length() == 14) {
			this.cpf = cpf;
		}
		//colocar erro
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPaciente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(idPaciente, other.idPaciente);
	}
}