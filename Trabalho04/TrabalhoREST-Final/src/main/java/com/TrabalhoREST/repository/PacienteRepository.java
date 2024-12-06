package com.TrabalhoREST.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.TrabalhoREST.model.Medico;
import com.TrabalhoREST.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	List<Paciente> findByMedico(Medico medico);
	
	@Query("SELECT p FROM Paciente p WHERE YEAR(p.dataNasc) = :ano")
    List<Paciente> findByAnoNasc(@Param("ano") int ano);
}