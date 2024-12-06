package com.TrabalhoREST.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TrabalhoREST.model.Especialidade;
import com.TrabalhoREST.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
	List<Medico> findByEspecialidade(Especialidade especialidade);
	List<Medico> findByNomeMedico(String nomeMedico);
}
