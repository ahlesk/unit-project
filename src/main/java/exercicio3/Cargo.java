package exercicio3;

public enum Cargo {

    DESENVOLVEDOR(3000.0, 0.20, 0.10),
    DBA(2000.0, 0.25, 0.15),
    TESTADOR(2500.0, 0.15, 0.05);

    private final double limiteDesconto;
    private final double descontoAcima;
    private final double descontoAbaixo;

    Cargo(double limiteDesconto, double descontoAcima, double descontoAbaixo) {
        this.limiteDesconto = limiteDesconto;
        this.descontoAcima = descontoAcima;
        this.descontoAbaixo = descontoAbaixo;
    }

    public double getLimiteDesconto() {
        return limiteDesconto;
    }

    public double getDescontoAcima() {
        return descontoAcima;
    }

    public double getDescontoAbaixo() {
        return descontoAbaixo;
    }
}
