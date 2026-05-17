package exercicio1;

public class Triangulo {

    public enum Tipo {
        EQUILATERO, ISOSCELES, ESCALENO, NAO_E_TRIANGULO
    }

    public Tipo classificar(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return Tipo.NAO_E_TRIANGULO;
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            return Tipo.NAO_E_TRIANGULO;
        }
        if (a == b && b == c) {
            return Tipo.EQUILATERO;
        }
        if (a == b || b == c || a == c) {
            return Tipo.ISOSCELES;
        }
        return Tipo.ESCALENO;
    }
}
