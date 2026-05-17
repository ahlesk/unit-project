package exercicio2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    private PersonDAO dao;

    @BeforeEach
    void setUp() {
        dao = new PersonDAO();
    }

    // ===================== Pessoa válida =====================

    @Test
    void pessoaValida_retornaListaVazia() {
        Person pessoa = new Person("Maria Silva", 30, List.of(new Email("maria@email.com")));
        assertTrue(dao.isValidToInclude(pessoa).isEmpty());
    }

    // ===================== Validação de nome =====================

    @Test
    void nomeComUmaParte_retornaErro() {
        Person pessoa = new Person("Maria", 30, List.of(new Email("maria@email.com")));
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("duas partes"));
    }

    @Test
    void nomeComNumero_retornaErro() {
        Person pessoa = new Person("Maria2 Silva", 30, List.of(new Email("maria@email.com")));
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("apenas letras"));
    }

    @Test
    void nomeComCaractereEspecial_retornaErro() {
        Person pessoa = new Person("Maria@ Silva", 30, List.of(new Email("maria@email.com")));
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("apenas letras"));
    }

    @Test
    void nomeVazio_retornaErro() {
        Person pessoa = new Person("", 30, List.of(new Email("maria@email.com")));
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("vazio"));
    }

    @Test
    void nomeComAcento_pessoaValida_retornaListaVazia() {
        Person pessoa = new Person("José Gonçalves", 30, List.of(new Email("jose@email.com")));
        assertTrue(dao.isValidToInclude(pessoa).isEmpty());
    }

    // ===================== Validação de idade =====================

    @Test
    void idadeZero_retornaErro() {
        Person pessoa = new Person("Maria Silva", 0, List.of(new Email("maria@email.com")));
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("1 e 200"));
    }

    @Test
    void idadeNegativa_retornaErro() {
        Person pessoa = new Person("Maria Silva", -5, List.of(new Email("maria@email.com")));
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("1 e 200"));
    }

    @Test
    void idadeMaiorQue200_retornaErro() {
        Person pessoa = new Person("Maria Silva", 201, List.of(new Email("maria@email.com")));
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("1 e 200"));
    }

    @Test
    void idadeBordaInferior_idade1_retornaListaVazia() {
        Person pessoa = new Person("Maria Silva", 1, List.of(new Email("maria@email.com")));
        assertTrue(dao.isValidToInclude(pessoa).isEmpty());
    }

    @Test
    void idadeBordaSuperior_idade200_retornaListaVazia() {
        Person pessoa = new Person("Maria Silva", 200, List.of(new Email("maria@email.com")));
        assertTrue(dao.isValidToInclude(pessoa).isEmpty());
    }

    // ===================== Validação de email =====================

    @Test
    void semEmail_retornaErro() {
        Person pessoa = new Person("Maria Silva", 30, List.of());
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("pelo menos um email"));
    }

    @Test
    void emailSemArroba_retornaErro() {
        Person pessoa = new Person("Maria Silva", 30, List.of(new Email("mariaemail.com")));
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("Email inválido"));
    }

    @Test
    void emailSemPonto_retornaErro() {
        Person pessoa = new Person("Maria Silva", 30, List.of(new Email("maria@emailcom")));
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("Email inválido"));
    }

    @Test
    void emailSemParteAntesDoArroba_retornaErro() {
        Person pessoa = new Person("Maria Silva", 30, List.of(new Email("@email.com")));
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("Email inválido"));
    }

    @Test
    void emailSemDominio_retornaErro() {
        Person pessoa = new Person("Maria Silva", 30, List.of(new Email("maria@.com")));
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("Email inválido"));
    }

    @Test
    void emailSemTLD_retornaErro() {
        Person pessoa = new Person("Maria Silva", 30, List.of(new Email("maria@email.")));
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("Email inválido"));
    }

    @Test
    void multiplosEmails_todosValidos_retornaListaVazia() {
        Person pessoa = new Person("Maria Silva", 30, List.of(
                new Email("maria@email.com"),
                new Email("maria@trabalho.org")
        ));
        assertTrue(dao.isValidToInclude(pessoa).isEmpty());
    }

    // ===================== Múltiplos erros =====================

    @Test
    void pessoaComVariosErros_retornaTodosOsErros() {
        Person pessoa = new Person("Maria", -1, List.of());
        List<String> erros = dao.isValidToInclude(pessoa);
        assertEquals(3, erros.size());
    }

    // ===================== save() =====================

    @Test
    void save_pessoaValida_naoLancaExcecao() {
        Person pessoa = new Person("Maria Silva", 30, List.of(new Email("maria@email.com")));
        assertDoesNotThrow(() -> dao.save(pessoa));
    }
}
