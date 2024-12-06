package TrabalhoSOAP;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
@WebService(endpointInterface = "TrabalhoSOAP.MedicoPacienteServer")
public class MedicoPacienteServerImpl implements MedicoPacienteServer {

	private Medico umMedico;
	private List<Paciente> listaPacientes = new ArrayList<>();
	private List<Medico> listaMedicos = new ArrayList<>();

	@Override
	public String createMedico(String nome, int crm, String especialidade) {
		umMedico = new Medico(nome, crm, especialidade);
		listaMedicos.add(umMedico);
		return "Cadastro do médico criado com sucesso";
	}

	@Override
	public String createPaciente(String nome, String cpf, String dataNasc) {
		Paciente umPaciente = new Paciente(nome, cpf, dataNasc);
		listaPacientes.add(umPaciente);
		return "Cadastro do paciente criado com sucesso";
	}

	@Override
	public String readMedico(int crm) {
		for (Medico umMedico : listaMedicos) {
			if (umMedico.getCrm() == crm) {
				return umMedico.toString();
			}
		}
		return "Médico não encontrado";
	}

	@Override
	public String readPaciente(String cpf) {
		for (Paciente umPaciente : listaPacientes) {
			if (umPaciente.getCpf().equals(cpf)) {
				return umPaciente.toString();
			}
		}
		return "Paciente não encontrado";
	}

	@Override
	public String updateMedico(String nome, int crm, String especialidade) {
		for (Medico umMedico : listaMedicos) {
			if (umMedico.getCrm() == crm) {
				umMedico.atualizarMedico(nome, especialidade);
				return "Cadastro do médico " + umMedico.getNome() + " atualizado com sucesso";
			}
		}
		return "Médico não encontrado";
	}

	@Override
	public String updatePaciente(String nome, String cpf, String dataNasc) {
		for (Paciente umPaciente : listaPacientes) {
			if (umPaciente.getCpf().equals(cpf)) {
				umPaciente.atualizarPaciente(nome, dataNasc);
				return "Cadastro do paciente " + umPaciente.getNome() + " atualizado com sucesso";
			}
		}
		return "Paciente não encontrado";
	}

	@Override
	public String deletePaciente(int crm, String cpf) {
		Medico umMedico = null;
		for (Medico medico : listaMedicos) {
			if (medico.getCrm() == crm) {
				umMedico = medico;
				break;
			}
		}

		if (umMedico == null) {
			return "Médico não encontrado";
		}

		Paciente umPaciente = null;
		for (Paciente paciente : listaPacientes) {
			if (paciente.getCpf().equals(cpf)) {
				umPaciente = paciente;
				break;
			}
		}

		if (umPaciente == null) {
			return "Paciente não encontrado";
		}

		listaPacientes.remove(umPaciente);
		umMedico.removerPaciente(umPaciente);

		return "Cadastro do paciente " + umPaciente.getNome() + " deletado com sucesso";
	}

	@Override
	public String printList(int crm) {
		Medico umMedico = null;
		for (Medico medico : listaMedicos) {
			if (medico.getCrm() == crm) {
				umMedico = medico;
				break;
			}
		}

		if (umMedico == null) {
			return "Médico não encontrado";
		}

		String s = "Lista de pacientes do médico " + umMedico.getNome() + "\n";
		s +=  umMedico.imprimirLista();
		
		return s.toString();
	}

	@Override
	public String addPaciente(int crm, String cpf) {
		Medico umMedico = null;
		for (Medico medico : listaMedicos) {
			if (medico.getCrm() == crm) {
				umMedico = medico;
				break;
			}
		}

		if (umMedico == null) {
			return "Médico não encontrado";
		}

		Paciente umPaciente = null;
		for (Paciente paciente : listaPacientes) {
			if (paciente.getCpf().equals(cpf)) {
				umPaciente = paciente;
				break;
			}
		}

		if (umPaciente == null) {
			return "Paciente não encontrado";
		}

		umMedico.adicionarPaciente(umPaciente);
		return "Paciente " + umPaciente.getNome() + " adicionado ao médico " + umMedico.getNome() + " com sucesso";
	}
}