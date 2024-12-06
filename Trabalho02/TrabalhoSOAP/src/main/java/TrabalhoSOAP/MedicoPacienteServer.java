package TrabalhoSOAP;
import javax.jws.WebService;
import java.time.LocalDate;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
@WebService
@SOAPBinding(style = Style.RPC)
public interface MedicoPacienteServer {
	@WebMethod String createMedico(String nome, int crm, String especialidade);
	@WebMethod String createPaciente(String nome, String cpf, String dataNasc);
	@WebMethod String readMedico(int crm);
	@WebMethod String readPaciente(String cpf);
	@WebMethod String updateMedico(String nome, int crm, String especialidade);
	@WebMethod String updatePaciente(String nome, String cpf, String dataNasc);
	@WebMethod String deletePaciente(int crm, String cpf);
	@WebMethod String printList(int crm);
	@WebMethod String addPaciente(int crm, String cpf);
}