package TrabalhoSOAP;
import javax.xml.ws.Endpoint;
public class MedicoPacientePublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://127.0.0.1:9876/medicopaciente", new MedicoPacienteServerImpl());
	}
}