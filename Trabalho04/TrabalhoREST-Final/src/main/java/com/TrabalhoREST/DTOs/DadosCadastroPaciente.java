package com.TrabalhoREST.DTOs;

import java.sql.Date;

public record DadosCadastroPaciente(
		String nomePaciente,
		String cpf, 
		Date dataNasc,
		Long idMedico) {
}