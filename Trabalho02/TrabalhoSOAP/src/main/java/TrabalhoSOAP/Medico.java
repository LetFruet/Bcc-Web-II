package TrabalhoSOAP;
import java.util.ArrayList;
import java.util.List;
public class Medico {

	private int crm;
	private String nome, especialidade;
	private List<Paciente> listaPacientes = new ArrayList<>();

	public Medico(String nome, int crm, String especialidade) {
		setNome(nome);
		setCrm(crm);
		setEspecialidade(especialidade);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome não pode estar vazio");
		}
		this.nome = nome;
	}

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		String crmTemp = String.valueOf(crm);

		if (crmTemp.length() != 6) {
			throw new IllegalArgumentException("CRM inválido. Deve ter 6 dígitos");
		}
		this.crm = crm;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		if (especialidade.trim().isEmpty()) {
			throw new IllegalArgumentException("A especialidade não pode estar vazia");
		}

		this.especialidade = especialidade;
	}

	public void adicionarPaciente(Paciente novoPaciente) {
		if (novoPaciente == null) {
			throw new IllegalArgumentException("Paciente inválido");
		}

		if (buscarPaciente(novoPaciente.getCpf()) == null) {
			listaPacientes.add(novoPaciente);
		} else {
			throw new IllegalArgumentException("Paciente já está na lista");
		}
	}

	public void removerPaciente(Paciente umPaciente) {
		if (buscarPaciente(umPaciente.getCpf()) != null) {
			listaPacientes.remove(umPaciente);
		} else {
			throw new IllegalArgumentException("Paciente não encontrado na lista");
		}
	}

	public void atualizarMedico(String nome, String especialidade) {
		if (nome != null && !nome.isEmpty()) {
			setNome(nome);
		}		
		if (especialidade != null) {
			setEspecialidade(especialidade);
		}
	}

	public Paciente buscarPaciente(String cpf) {
		for (Paciente p : listaPacientes) {
			if (p.getCpf().equalsIgnoreCase(cpf)) {
				return p;
			}
		}
		return null;
	}

	public String imprimirLista() {
		String s = "";
		for (Paciente p : listaPacientes) {
			s += "\nNome: " + p.getNome() + "\nCPF: " + p.getCpf() + "\nData de nascimento: " + p.getDataNasc() + "\n";
		}
		return s;
	}

	public String toString() {
		return "Medico [nome = " + nome + ", crm = " + crm + ", especialidade = " + especialidade + "]";
	}
}