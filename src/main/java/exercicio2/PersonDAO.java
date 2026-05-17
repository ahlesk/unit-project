package exercicio2;

import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private final List<Person> pessoas = new ArrayList<>();

    public List<String> isValidToInclude(Person person) {
        List<String> erros = new ArrayList<>();

        validarNome(person.getNome(), erros);
        validarIdade(person.getIdade(), erros);
        validarEmails(person.getEmails(), erros);

        return erros;
    }

    public void save(Person person) {
        pessoas.add(person);
    }

    private void validarNome(String nome, List<String> erros) {
        if (nome == null || nome.isBlank()) {
            erros.add("O nome não pode ser vazio");
            return;
        }
        String[] partes = nome.trim().split("\\s+");
        if (partes.length < 2) {
            erros.add("O nome deve ter pelo menos duas partes");
        }
        for (String parte : partes) {
            if (!parte.matches("[\\p{L}]+")) {
                erros.add("O nome deve conter apenas letras");
                return;
            }
        }
    }

    private void validarIdade(int idade, List<String> erros) {
        if (idade < 1 || idade > 200) {
            erros.add("A idade deve estar entre 1 e 200");
        }
    }

    private void validarEmails(List<Email> emails, List<String> erros) {
        if (emails == null || emails.isEmpty()) {
            erros.add("Deve haver pelo menos um email");
            return;
        }
        for (Email email : emails) {
            if (!isFormatoEmailValido(email.getEndereco())) {
                erros.add("Email inválido: " + email.getEndereco());
            }
        }
    }

    private boolean isFormatoEmailValido(String endereco) {
        if (endereco == null || !endereco.contains("@")) {
            return false;
        }
        String[] partes = endereco.split("@", -1);
        if (partes.length != 2 || partes[0].isEmpty()) {
            return false;
        }
        String dominio = partes[1];
        int indicePonto = dominio.lastIndexOf('.');
        if (indicePonto < 0) {
            return false;
        }
        String nomeDominio = dominio.substring(0, indicePonto);
        String tld = dominio.substring(indicePonto + 1);
        return !nomeDominio.isEmpty() && !tld.isEmpty();
    }
}
