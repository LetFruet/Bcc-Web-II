package TrabalhoSOAP;
public class Paciente {

	private String nome, cpf, dataNasc;

	public Paciente(String nome, String cpf, String dataNasc) {
		setNome(nome);
		setCpf(cpf);
		setDataNasc(dataNasc);
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf.length() != 11) {
			throw new IllegalArgumentException("CPF inválido. Deve ter 11 dígitos");
		}

		this.cpf = cpf;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		/*LocalDate dataAtual = LocalDate.now();

		if (dataNasc.isAfter(dataAtual)) {
			throw new IllegalArgumentException("Data de nascimento inválida");
		}*/
		this.dataNasc = dataNasc;
	}

	public void atualizarPaciente(String nome, String dataNasc) {
		if (nome != null && !nome.isEmpty()) {
			setNome(nome);
		}
		if (dataNasc != null) {
			setDataNasc(dataNasc);
		}
	}

	public String toString() {
		return "Paciente [nome = " + nome + ", cpf = " + cpf + ", dataNasc = " + dataNasc + "]";
	}
}