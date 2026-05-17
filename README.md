# JUnit + TDD

Projeto com 3 exercícios utilizando **Java**, **JUnit 5** e **JaCoCo** para prática de **Test-Driven Development (TDD)** e análise de cobertura de testes.

---

# Pré-requisitos

* Java 17 ou superior
* Maven 3.6+

---

# Estrutura do Projeto

```text
src/
├── main/java/
│   ├── exercicio1/   -> Triangulo
│   ├── exercicio2/   -> Person, Email, PersonDAO
│   └── exercicio3/   -> Cargo, Funcionario, CalculadoraSalario
└── test/java/
    ├── exercicio1/   -> TrianguloTest
    ├── exercicio2/   -> PersonDAOTest
    └── exercicio3/   -> CalculadoraSalarioTest
```

---

# Como executar o projeto

## Compilar o projeto

```bash
mvn compile
```

## Executar os testes

```bash
mvn test
```

## Executar build + testes + cobertura

```bash
mvn clean test
```

---

# Evidências

## Execução dos testes

```text
[INFO] Tests run: XX, Failures: 0, Errors: 0, Skipped: 0
```

<img width="684" height="319" alt="image" src="https://github.com/user-attachments/assets/f11f642c-4e28-4849-a197-cc62d2ece0b4" />

---

## Relatório de cobertura JaCoCo

<img width="967" height="203" alt="image" src="https://github.com/user-attachments/assets/2cbc5b6f-c1ab-480e-8247-ffb5af976fb4" />

---

# Conceitos de TDD aplicados no projeto

## 1. Testes como especificação

Antes de implementar `Triangulo.classificar()`, os testes já definiam o comportamento esperado do método.

Exemplos:

* `(3,4,5)` → escaleno
* `(0,5,5)` → inválido
* `(7,3,4)` → não forma triângulo

Os testes funcionam como uma **documentação executável**: se todos passam, a especificação está sendo cumprida.

---

## 2. Boundary Value Analysis (Análise de Valor Limite)

Nos testes de `PersonDAO`, os valores de borda são validados separadamente:

### Casos válidos

* idade = `1`
* idade = `200`

### Casos inválidos

* idade = `0`
* idade = `201`

Em TDD, erros costumam aparecer nos extremos do domínio, não nos valores intermediários.

---

## 3. Particionamento de equivalência

No `TrianguloTest`, as três permutações do triângulo isósceles são testadas:

```text
(5,5,3)
(5,3,5)
(3,5,5)
```

Isso garante que a lógica funciona independentemente da posição dos lados iguais.

Sem esses testes, seria fácil implementar apenas:

```java
if (a == b)
```

e esquecer os outros cenários.

---

## 4. Testes de múltiplos erros acumulados

O teste:

```text
pessoaComVariosErros_retornaTodosOsErros()
```

garante que o método `isValidToInclude()`:

* não pare no primeiro erro encontrado
* retorne todos os problemas simultaneamente

Exemplo validado:

* nome inválido
* idade negativa
* ausência de email

Sem esse teste, uma implementação com `if/return` simples poderia passar despercebida.

---

## 5. Separação de responsabilidades

Os testes de `CalculadoraSalario` incentivaram uma separação clara entre:

* `Cargo` → regras de negócio
* `Funcionario` → entidade
* `CalculadoraSalario` → cálculo salarial

No TDD, o design tende a emergir naturalmente a partir da testabilidade do código.

Classes muito acopladas se tornam difíceis de testar isoladamente.

---

## 6. Teste de ausência de exceção

O teste:

```java
assertDoesNotThrow()
```

foi utilizado para validar que:

```java
save()
```

não lança exceções inesperadas.

Em TDD, ausência de falha também é um comportamento esperado e deve ser especificado explicitamente.

---

# Por que TDD tende a gerar código mais limpo?

Ao escrever os testes antes da implementação:

* a interface pública é pensada primeiro
* responsabilidades ficam menores e mais claras
* métodos tendem a ser mais coesos
* menos estado desnecessário é criado

Exemplo:

```java
CalculadoraSalario.calcularSalarioLiquido()
```

possui apenas poucas linhas porque os testes exigiam apenas a lógica essencial para o comportamento esperado.

---

# Tecnologias utilizadas

* Java 17
* Maven
* JUnit 5
* JaCoCo

---

# Objetivo do projeto

Este projeto foi desenvolvido com foco em:

* prática de TDD
* escrita de testes automatizados
* validação de regras de negócio
* cobertura de código com JaCoCo
* boas práticas de design orientado a testes
