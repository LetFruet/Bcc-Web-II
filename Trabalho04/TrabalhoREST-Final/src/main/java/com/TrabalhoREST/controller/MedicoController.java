package com.TrabalhoREST.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
import com.TrabalhoREST.DTOs.DadosCadastroMedico;
import com.TrabalhoREST.model.Especialidade;
import com.TrabalhoREST.model.Medico;
import com.TrabalhoREST.repository.EspecialidadeRepository;
import com.TrabalhoREST.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	@Lazy
	private MedicoRepository medicoRepository;

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody DadosCadastroMedico dados) {
		Optional<Especialidade> especialidade = especialidadeRepository.findById(dados.idEspecialidade());

		if (especialidade.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Especialidade não encontrada com o id: " + dados.idEspecialidade());
		}

		try {
			var medico = new Medico(dados, especialidade.get());
			medicoRepository.save(medico);

			return ResponseEntity.status(HttpStatus.CREATED).body(medico);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao adicionar médico: " + e.getMessage());
		}
	}

	@GetMapping
	public List<Medico> listar() {
		return medicoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Optional<Medico> medicoOptional = medicoRepository.findById(id);

		if (medicoOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado com o id: " + id);
		}

		return ResponseEntity.ok(medicoOptional.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarMedico(@PathVariable Long id, @RequestBody DadosCadastroMedico dadosAtualizados) {

		Optional<Medico> medicoOptional = medicoRepository.findById(id);

		if (medicoOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado com o id: " + id);
		}

		Medico medico = medicoOptional.get();

		if (dadosAtualizados.nomeMedico() != null && !dadosAtualizados.nomeMedico().isBlank()) {
			medico.setNomeMedico(dadosAtualizados.nomeMedico());
		}

		if (dadosAtualizados.crm() != null && !dadosAtualizados.crm().isBlank()) {
			medico.setCrm(dadosAtualizados.crm());
		}

		if (dadosAtualizados.idEspecialidade() != null && dadosAtualizados.idEspecialidade() != 0) {
			Optional<Especialidade> especialidadeOptional = especialidadeRepository
					.findById(dadosAtualizados.idEspecialidade());
			if (especialidadeOptional.isPresent()) {
			    medico.setEspecialidade(especialidadeOptional.get());
			} else {
			    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			        .body("Erro ao encontrar a especialidade com o id: " + dadosAtualizados.idEspecialidade());
			}

		}

		Medico medicoAtualizado = medicoRepository.save(medico);

		return ResponseEntity.ok(medicoAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Medico> medico = medicoRepository.findById(id);

		if (medico.isPresent()) {
			medicoRepository.delete(medico.get());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado com o id: " + id);
		}
	}

	@GetMapping("/especialidade/{idEspecialidade}")
	public ResponseEntity<?> buscarPorEspecialidade(@PathVariable Long idEspecialidade) {

		Optional<Especialidade> especialidadeOptional = especialidadeRepository.findById(idEspecialidade);

		if (especialidadeOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Especialidade não encontrada com o id: " + idEspecialidade);
		}

		Especialidade especialidade = especialidadeOptional.get();
		List<Medico> medicos = medicoRepository.findByEspecialidade(especialidade);

		if (medicos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Nenhum médico encontrado com a especialidade: " + idEspecialidade);
		}

		return ResponseEntity.ok(medicos);
	}
	
	@GetMapping("/nome/{nomeMedico}")
	public ResponseEntity<?> buscarPorNome(@PathVariable String nomeMedico) {
	    List<Medico> medicos = medicoRepository.findByNomeMedico(nomeMedico);

	    if (medicos.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Nenhum médico encontrado com o nome: " + nomeMedico);
	    }

	    return ResponseEntity.ok(medicos);
	}
}