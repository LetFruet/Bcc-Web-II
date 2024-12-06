package calc;

import java.util.Date;
import javax.jws.WebService;

@WebService(endpointInterface = "calc.CalculadoraServer")           //faz com que a classe atual, a SIB, ligue-se com a SEI especificada anteriormente
public class CalculadoraServerImpl implements CalculadoraServer {

  public float soma(float num1, float num2) {
    return num1 + num2;
  }

  public float subtracao(float num1, float num2) {
    return num1 - num2;
  }

  public float multiplicacao(float num1, float num2) {
    return num1 * num2;
  }

  public float divisao(float num1, float num2) {
    return num1 / num2;
  }

}