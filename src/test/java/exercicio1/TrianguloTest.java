package exercicio1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static exercicio1.Triangulo.Tipo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrianguloTest {

    private Triangulo triangulo;

    @BeforeEach
    void setUp() {
        triangulo = new Triangulo();
    }

    // --- Triângulos válidos ---

    @Test
    void escaleno_ladosDiferentes_retornaEscaleno() {
        assertEquals(ESCALENO, triangulo.classificar(3, 4, 5));
    }

    @Test
    void isosceles_doisLadosIguais_retornaIsosceles() {
        assertEquals(ISOSCELES, triangulo.classificar(10, 10, 7));
    }

    @Test
    void equilatero_tresLadosIguais_retornaEquilatero() {
        assertEquals(EQUILATERO, triangulo.classificar(5, 5, 5));
    }

    // --- 3 permutações de isósceles ---

    @Test
    void isosceles_permutacao1_AB_retornaIsosceles() {
        assertEquals(ISOSCELES, triangulo.classificar(5, 5, 3));
    }

    @Test
    void isosceles_permutacao2_AC_retornaIsosceles() {
        assertEquals(ISOSCELES, triangulo.classificar(5, 3, 5));
    }

    @Test
    void isosceles_permutacao3_BC_retornaIsosceles() {
        assertEquals(ISOSCELES, triangulo.classificar(3, 5, 5));
    }

    // --- Lados inválidos ---

    @Test
    void umLadoZero_retornaNaoETriangulo() {
        assertEquals(NAO_E_TRIANGULO, triangulo.classificar(0, 5, 5));
    }

    @Test
    void umLadoNegativo_retornaNaoETriangulo() {
        assertEquals(NAO_E_TRIANGULO, triangulo.classificar(-1, 5, 5));
    }

    @Test
    void tresLadosZero_retornaNaoETriangulo() {
        assertEquals(NAO_E_TRIANGULO, triangulo.classificar(0, 0, 0));
    }

    // --- Soma de 2 lados igual ao terceiro (3 permutações) ---

    @Test
    void somaDoisLadosIgualTerceiro_perm1_retornaNaoETriangulo() {
        // 3 + 4 = 7
        assertEquals(NAO_E_TRIANGULO, triangulo.classificar(3, 4, 7));
    }

    @Test
    void somaDoisLadosIgualTerceiro_perm2_retornaNaoETriangulo() {
        // 3 + 7 = 10 → lado c=10? não. 3+4=7, segunda permutação: c no meio
        // a + c <= b: 3 + 7 = 10 > 4 — não é esse caso
        // Vamos usar: a=3, b=7, c=4 onde a+c=b: 3+4=7
        assertEquals(NAO_E_TRIANGULO, triangulo.classificar(3, 7, 4));
    }

    @Test
    void somaDoisLadosIgualTerceiro_perm3_retornaNaoETriangulo() {
        // b+c=a: 3+4=7 → a=7, b=3, c=4
        assertEquals(NAO_E_TRIANGULO, triangulo.classificar(7, 3, 4));
    }

    // --- Soma de 2 lados menor que o terceiro (3 permutações) ---

    @Test
    void somaDoisLadosMenorTerceiro_perm1_retornaNaoETriangulo() {
        // 1 + 2 < 10
        assertEquals(NAO_E_TRIANGULO, triangulo.classificar(1, 2, 10));
    }

    @Test
    void somaDoisLadosMenorTerceiro_perm2_retornaNaoETriangulo() {
        // 1 + 10 = 11 > 2, porém a+c: 1+2=3 < 10 → a=1, b=10, c=2
        assertEquals(NAO_E_TRIANGULO, triangulo.classificar(1, 10, 2));
    }

    @Test
    void somaDoisLadosMenorTerceiro_perm3_retornaNaoETriangulo() {
        // b+c: 1+2=3 < 10 → a=10, b=1, c=2
        assertEquals(NAO_E_TRIANGULO, triangulo.classificar(10, 1, 2));
    }
}
