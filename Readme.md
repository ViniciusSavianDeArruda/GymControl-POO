<h1 align="center">🏋️ GymControl</h1>

<p align="center">
  <i>Sistema de gerenciamento de academia desenvolvido em Java aplicando os pilares da POO.</i>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Paradigma-POO-blue?style=flat" />
  <img src="https://img.shields.io/badge/UFN-Sistemas%20de%20Informação-darkgreen?style=flat" />
</p>

---

## 📌 Sobre o Projeto

O **GymControl** é um sistema em Java, executado via terminal, que simula o gerenciamento administrativo de uma academia. Permite cadastrar **alunos** (com ou sem plano) e **professores**, associar alunos a **planos** (mensal, trimestral e anual), registrar **pagamentos**, listar informações e remover registros através de um **menu interativo**.

O projeto foi desenvolvido como trabalho final da disciplina de Programação Orientada a Objetos e contempla, de forma funcional, os **7 requisitos obrigatórios**: classes e objetos, construtores com sobrecarga, encapsulamento, herança, polimorfismo, classes abstratas/interfaces e tratamento de exceções (incluindo uma exceção customizada e o uso completo de `try-catch-finally`).

---

## 🎥 Demonstração

📺 **Vídeo da demo no YouTube:** [Assistir aqui](https://youtu.be/YVQb0twPDzk)

---

## 🗂️ Estrutura do Projeto

```text
src/
├── Principal/Main.java
├── models/     → Pessoa, Aluno, Professor, Plano, PlanoMensal,
│                 PlanoTrimestral, PlanoAnual, Pagamento, Academia
├── interfaces/ → Exibivel
└── exceptions/ → IdadeInvalidaException
```

- **`Principal`** — contém a [`Main`](src/Principal/Main.java), ponto de entrada da aplicação, responsável pelo menu e pelo controle de fluxo.
- **`models`** — contém as entidades do domínio e a classe central [`Academia`](src/models/Academia.java), responsável pelas regras de negócio (cadastros, remoções e buscas).
- **`interfaces`** — define o contrato [`Exibivel`](src/interfaces/Exibivel.java), padronizando como as entidades exibem seus dados.
- **`exceptions`** — armazena a exceção customizada [`IdadeInvalidaException`](src/exceptions/IdadeInvalidaException.java).

---

## 📋 Funcionalidades do Menu

| Opção | Ação |
|---|---|
| 1 | Cadastrar aluno (com plano) |
| 2 | Cadastrar professor |
| 3 | Listar pessoas |
| 4 | Listar planos |
| 5 | Registrar pagamento |
| 6 | Listar pagamentos |
| 7 | Remover aluno |
| 8 | Remover professor |
| 9 | Cadastrar aluno (sem plano) |
| 0 | Sair |

---

## ✅ Mapeamento dos Requisitos de POO

### 1. Classes, Objetos e Atributos
Cada entidade do domínio foi modelada como uma classe coesa no pacote [`models`](src/models). Os objetos são instanciados em tempo de execução pela [`Main.java`](src/Principal/Main.java), que aciona a [`Academia`](src/models/Academia.java) para realizar as operações.

> **Classes:** [`Pessoa`](src/models/Pessoa.java) · [`Aluno`](src/models/Aluno.java) · [`Professor`](src/models/Professor.java) · [`Plano`](src/models/Plano.java) · [`PlanoMensal`](src/models/PlanoMensal.java) · [`PlanoTrimestral`](src/models/PlanoTrimestral.java) · [`PlanoAnual`](src/models/PlanoAnual.java) · [`Pagamento`](src/models/Pagamento.java) · [`Academia`](src/models/Academia.java)

### 2. Construtores
Construtores parametrizados foram utilizados em todas as classes de modelo para inicializar os objetos em estados válidos. A **sobrecarga de construtores** foi aplicada na classe [`Aluno`](src/models/Aluno.java), permitindo dois modos de criação — com e sem plano associado. Os dois construtores são realmente utilizados no sistema (opções 1 e 9 do menu):

```java
public Aluno(String nome, String cpf, int idade)
public Aluno(String nome, String cpf, int idade, Plano plano)
```

### 3. Encapsulamento
Todos os atributos das classes do pacote [`models`](src/models) são `private` e o acesso é feito por meio de **getters/setters validados**, impedindo que o estado dos objetos seja modificado de forma indevida. As validações incluem: CPF com 11 dígitos numéricos, nome obrigatório (não pode ser vazio nem conter apenas números), especialidade obrigatória e idade entre **16 e 80 anos**. Os getters de listas na [`Academia`](src/models/Academia.java) retornam cópias, protegendo as coleções internas.

### 4. Herança
Foram criadas duas hierarquias no pacote [`models`](src/models), ambas respeitando a relação **"É UM"**:

```text
Pessoa (abstrata)              Plano (abstrata)
├── Aluno                      ├── PlanoMensal
└── Professor                  ├── PlanoTrimestral
                               └── PlanoAnual
```

> [`Aluno`](src/models/Aluno.java) é uma [`Pessoa`](src/models/Pessoa.java); [`PlanoMensal`](src/models/PlanoMensal.java) é um [`Plano`](src/models/Plano.java).

### 5. Polimorfismo
A **sobrescrita** foi aplicada em dois métodos abstratos: `exibirDados()` em [`Aluno`](src/models/Aluno.java) e [`Professor`](src/models/Professor.java); e `calcularValor()` em [`PlanoMensal`](src/models/PlanoMensal.java), [`PlanoTrimestral`](src/models/PlanoTrimestral.java) e [`PlanoAnual`](src/models/PlanoAnual.java).
O **polimorfismo de inclusão** ocorre em [`Academia`](src/models/Academia.java), que utiliza `ArrayList<Pessoa>` e `ArrayList<Plano>` para operar sobre objetos das subclasses por meio do tipo da superclasse.

### 6. Classes Abstratas e Interfaces
- **Classes abstratas:** [`Pessoa`](src/models/Pessoa.java) e [`Plano`](src/models/Plano.java), cada uma contendo pelo menos um método abstrato.
- **Interface:** [`Exibivel`](src/interfaces/Exibivel.java), implementada por [`Aluno`](src/models/Aluno.java), [`Professor`](src/models/Professor.java) e [`Plano`](src/models/Plano.java), padronizando o contrato de exibição.

### 7. Tratamento de Exceções
A exceção customizada [`IdadeInvalidaException`](src/exceptions/IdadeInvalidaException.java) protege a regra de negócio que impede o cadastro de pessoas fora da faixa etária permitida (16 a 80 anos). Blocos `try-catch` na [`Main.java`](src/Principal/Main.java) tratam ainda:
- `IllegalArgumentException` — dados inválidos (CPF, nome, especialidade);
- `InputMismatchException` — entradas mal-formadas no menu (letra onde se espera número);
- `NoSuchElementException` — encerramento seguro caso a entrada seja fechada.

O método `registrarPagamento` utiliza o bloco `try-catch-finally` completo, garantindo que a mensagem de conclusão da operação seja sempre exibida.

---

## 🎯 Problemas Resolvidos

- **Cadastros inválidos** — CPFs duplicados ou mal-formados, nomes vazios e idades fora da faixa são bloqueados na origem.
- **Controle de pagamentos** — registro e consulta vinculados ao aluno correspondente, com valor calculado automaticamente a partir do plano.
- **Cálculo de planos** — cada plano calcula seu próprio valor via polimorfismo, permitindo adicionar novos tipos sem modificar o código existente.
- **Acesso sem plano** — o sistema identifica alunos sem plano contratado e bloqueia o registro de pagamento.

---

## 🚀 Como Executar

```bash
# Compilar
javac -d bin src/interfaces/*.java src/models/*.java src/exceptions/*.java src/Principal/*.java

# Executar
java -cp bin Principal.Main
```

---

<p align="center">
  <sub>Trabalho final de <b>Programação Orientada a Objetos</b> — Sistemas de Informação · UFN</sub>
</p>
