package exercicio2;

import java.util.List;

public class Person {

    private final String nome;
    private final int idade;
    private final List<Email> emails;

    public Person(String nome, int idade, List<Email> emails) {
        this.nome = nome;
        this.idade = idade;
        this.emails = emails;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public List<Email> getEmails() {
        return emails;
    }
}
