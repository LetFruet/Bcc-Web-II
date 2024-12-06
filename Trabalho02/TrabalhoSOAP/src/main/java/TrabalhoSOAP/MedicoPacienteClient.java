package TrabalhoSOAP;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
class MedicoPacienteClient {

	public static void main(String args[]) throws Exception {
		URL url = new URL("http://127.0.0.1:9876/medicopaciente?wsdl");
		QName qname = new QName("http://TrabalhoSOAP/", "MedicoPacienteServerImplService");
		Service ws = Service.create(url, qname);
		MedicoPacienteServer mps = ws.getPort(MedicoPacienteServer.class);

		System.out.println("Criação do médico: " + mps.createMedico("João", 536987, "Pediatra"));
		System.out.println("Criação do médico: " + mps.createMedico("Arthur", 987456, "Oftalmologista"));
		System.out.println("-------------------------------------------------------------------");

		System.out.println("Criação do paciente: " + mps.createPaciente("Ana", "11111111111", "2003"));
		System.out.println("Criação do paciente: " + mps.createPaciente("Emanuelle", "22222222222", "2004"));
		System.out.println("Criação do paciente: " + mps.createPaciente("Lucas", "33333333333", "2010"));

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Dados do médico: " + mps.readMedico(536987));
		System.out.println("Dados do médico: " + mps.readMedico(987456));

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Dados do paciente: " + mps.readPaciente("11111111111"));
		System.out.println("Dados do paciente: " + mps.readPaciente("22222222222"));
		System.out.println("Dados do paciente: " + mps.readPaciente("33333333333"));

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Atualização do médico: " + mps.updateMedico("João Carlos", 536987, "Pediatra"));
		System.out.println("Dados do médico após atualização: " + mps.readMedico(536987));

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Atualização do paciente: " + mps.updatePaciente("Ana Jullia", "11111111111", "2004"));
		System.out.println("Dados do paciente após atualização: " + mps.readPaciente("11111111111"));

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Adição paciente na lista do médico: " + mps.addPaciente(536987, "11111111111"));
		System.out.println("Adição paciente na lista do médico: " + mps.addPaciente(536987, "33333333333"));

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Adição paciente na lista do médico: " + mps.addPaciente(987456, "22222222222"));

		System.out.println("-------------------------------------------------------------------");
		System.out.println(mps.printList(536987));
		System.out.println(mps.printList(987456));

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Atualização do paciente: " + mps.updatePaciente("Lucas Enzo", "33333333333", "2010"));
		System.out.println("Dados do paciente após atualização: " + mps.readPaciente("33333333333"));

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Exclusão do paciente: " + mps.deletePaciente(536987, "11111111111")); // Alana: criar um deleteMedico tambem??
		System.out.println("Dados do paciente: " + mps.readPaciente("11111111111"));
		System.out.println(mps.printList(536987));
	}
}