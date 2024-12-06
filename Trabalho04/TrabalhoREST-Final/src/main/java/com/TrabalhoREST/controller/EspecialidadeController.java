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
import com.TrabalhoREST.model.Especialidade;
import com.TrabalhoREST.repository.EspecialidadeRepository;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Especialidade especialidade) {
		try {
			especialidadeRepository.save(especialidade);

			return ResponseEntity.status(HttpStatus.CREATED).body(especialidade);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao adicionar especialidade: " + e.getMessage());
		}
	}

	@GetMapping
	public List<Especialidade> listar() {
		return especialidadeRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

		Optional<Especialidade> especialidadeOptional = especialidadeRepository.findById(id);

		if (especialidadeOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade não encontrada com o id: " + id);
		}

		return ResponseEntity.ok(especialidadeOptional.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarEspecialidade(@PathVariable Long id, @RequestBody Especialidade dadoAtualizado) {

		Optional<Especialidade> especialidadeOptional = especialidadeRepository.findById(id);

		if (especialidadeOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade não encontrada com o id: " + id);
		}

		Especialidade especialidade = especialidadeOptional.get();
		especialidade.setNomeEspecialidade(dadoAtualizado.getNomeEspecialidade());

		Especialidade especialidadeAtualizada = especialidadeRepository.save(especialidade);

		return ResponseEntity.ok(especialidadeAtualizada);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Especialidade> especialidade = especialidadeRepository.findById(id);

		if (especialidade.isPresent()) {
			especialidadeRepository.delete(especialidade.get());
			// NAO ESTA APARECENDO A MENSAGEM
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade não encontrado com o id: " + id);
		}
	}
}