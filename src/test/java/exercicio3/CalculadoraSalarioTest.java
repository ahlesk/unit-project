package exercicio3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraSalarioTest {

    private CalculadoraSalario calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraSalario();
    }

    // ===================== DESENVOLVEDOR (limite: R$ 3000 | acima: 20% | abaixo: 10%) =====================

    @Test
    void desenvolvedor_salarioAcimaDoLimite_aplicaDescontoDe20Porcento() {
        Funcionario f = new Funcionario("Ana", 4000.0, Cargo.DESENVOLVEDOR);
        assertEquals(3200.0, calculadora.calcularSalarioLiquido(f), 0.001);
    }

    @Test
    void desenvolvedor_salarioAbaixoDoLimite_aplicaDescontoDe10Porcento() {
        Funcionario f = new Funcionario("Ana", 2000.0, Cargo.DESENVOLVEDOR);
        assertEquals(1800.0, calculadora.calcularSalarioLiquido(f), 0.001);
    }

    @Test
    void desenvolvedor_salarioExatamenteNaFronteira_aplicaDescontoDe20Porcento() {
        Funcionario f = new Funcionario("Ana", 3000.0, Cargo.DESENVOLVEDOR);
        assertEquals(2400.0, calculadora.calcularSalarioLiquido(f), 0.001);
    }

    // ===================== DBA (limite: R$ 2000 | acima: 25% | abaixo: 15%) =====================

    @Test
    void dba_salarioAcimaDoLimite_aplicaDescontoDe25Porcento() {
        Funcionario f = new Funcionario("Bruno", 2500.0, Cargo.DBA);
        assertEquals(1875.0, calculadora.calcularSalarioLiquido(f), 0.001);
    }

    @Test
    void dba_salarioAbaixoDoLimite_aplicaDescontoDe15Porcento() {
        Funcionario f = new Funcionario("Bruno", 1500.0, Cargo.DBA);
        assertEquals(1275.0, calculadora.calcularSalarioLiquido(f), 0.001);
    }

    @Test
    void dba_salarioExatamenteNaFronteira_aplicaDescontoDe25Porcento() {
        Funcionario f = new Funcionario("Bruno", 2000.0, Cargo.DBA);
        assertEquals(1500.0, calculadora.calcularSalarioLiquido(f), 0.001);
    }

    // ===================== TESTADOR (limite: R$ 2500 | acima: 15% | abaixo: 5%) =====================

    @Test
    void testador_salarioAcimaDoLimite_aplicaDescontoDe15Porcento() {
        Funcionario f = new Funcionario("Carla", 3000.0, Cargo.TESTADOR);
        assertEquals(2550.0, calculadora.calcularSalarioLiquido(f), 0.001);
    }

    @Test
    void testador_salarioAbaixoDoLimite_aplicaDescontoDe5Porcento() {
        Funcionario f = new Funcionario("Carla", 2000.0, Cargo.TESTADOR);
        assertEquals(1900.0, calculadora.calcularSalarioLiquido(f), 0.001);
    }

    @Test
    void testador_salarioExatamenteNaFronteira_aplicaDescontoDe15Porcento() {
        Funcionario f = new Funcionario("Carla", 2500.0, Cargo.TESTADOR);
        assertEquals(2125.0, calculadora.calcularSalarioLiquido(f), 0.001);
    }

    // ===================== Getters do Funcionario =====================

    @Test
    void funcionario_getters_retornamValoresCorretos() {
        Funcionario f = new Funcionario("Diego", 3500.0, Cargo.DESENVOLVEDOR);
        assertEquals("Diego", f.getNome());
        assertEquals(3500.0, f.getSalario(), 0.001);
        assertEquals(Cargo.DESENVOLVEDOR, f.getCargo());
    }
}
