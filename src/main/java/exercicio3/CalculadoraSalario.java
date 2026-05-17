package exercicio3;

public class CalculadoraSalario {

    public double calcularSalarioLiquido(Funcionario funcionario) {
        double salario = funcionario.getSalario();
        Cargo cargo = funcionario.getCargo();
        double desconto = salario >= cargo.getLimiteDesconto()
                ? cargo.getDescontoAcima()
                : cargo.getDescontoAbaixo();
        return salario * (1 - desconto);
    }
}
