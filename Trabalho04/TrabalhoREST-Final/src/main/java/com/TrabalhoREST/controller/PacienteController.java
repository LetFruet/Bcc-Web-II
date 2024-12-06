package com.TrabalhoREST.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.TrabalhoREST.DTOs.DadosCadastroPaciente;
import com.TrabalhoREST.model.Medico;
import com.TrabalhoREST.model.Paciente;
import com.TrabalhoREST.repository.MedicoRepository;
import com.TrabalhoREST.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody DadosCadastroPaciente dados) {

		Optional<Medico> medico = medicoRepository.findById(dados.idMedico());

		if (medico.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Médico não encontrado com o id: " + dados.idMedico());
		}

		try {
			var paciente = new Paciente(dados, medico.get());
			pacienteRepository.save(paciente);

			return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao adicionar paciente: " + e.getMessage());
		}
	}

	@GetMapping
	public List<Paciente> listar() {
		return pacienteRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

		Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

		if (pacienteOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado com o id: " + id);
		}

		return ResponseEntity.ok(pacienteOptional.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarPaciente(@PathVariable Long id,
			@RequestBody DadosCadastroPaciente dadosAtualizados) {

		Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

		if (pacienteOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado com o id: " + id);
		}

		Paciente paciente = pacienteOptional.get();

		if (dadosAtualizados.nomePaciente() != null && !dadosAtualizados.nomePaciente().isBlank()) {
			paciente.setNomePaciente(dadosAtualizados.nomePaciente());
		}

		if (dadosAtualizados.cpf() != null && !dadosAtualizados.cpf().isBlank()) {
			paciente.setCpf(dadosAtualizados.cpf());
		}

		if (dadosAtualizados.dataNasc() != null) {
			paciente.setDataNasc(dadosAtualizados.dataNasc());
		}

		if (dadosAtualizados.idMedico() != null && dadosAtualizados.idMedico() != 0) {
			Optional<Medico> medicoOptional = medicoRepository.findById(dadosAtualizados.idMedico());

			if (medicoOptional.isPresent()) {
				paciente.setMedico(medicoOptional.get());
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("MErro ao encontrar o médico com o id: " + dadosAtualizados.idMedico());
			}
		}

		Paciente pacienteAtualizado = pacienteRepository.save(paciente);

		return ResponseEntity.ok(pacienteAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Paciente> paciente = pacienteRepository.findById(id);

		if (paciente.isPresent()) {
			pacienteRepository.delete(paciente.get());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado com o id: " + id);
		}
	}

	@GetMapping("/medico/{idMedico}")
	public ResponseEntity<?> buscarPacientesPorMedico(@PathVariable Long idMedico) {

		Optional<Medico> medicoOptional = medicoRepository.findById(idMedico);

		if (medicoOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado com o id: " + idMedico);
		}

		Medico medico = medicoOptional.get();
		List<Paciente> pacientes = pacienteRepository.findByMedico(medico);

		return ResponseEntity.ok(pacientes);
	}
	
	@GetMapping("/ano/{ano}")
	public ResponseEntity<List<Paciente>> buscarPacientesPorAnoNasc(@PathVariable int ano) {
	    List<Paciente> pacientes = pacienteRepository.findByAnoNasc(ano); // Use o método correto de acordo com a implementação do repositório

	    if (pacientes.isEmpty()) {
	        return ResponseEntity.noContent().build(); // Retorna 204 No Content se não encontrar pacientes
	    }

	    return ResponseEntity.ok(pacientes);
	}
}