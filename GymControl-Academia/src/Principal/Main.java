package Principal;

import models.*;
import exceptions.IdadeInvalidaException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Academia academia = new Academia();

    public static void main(String[] args) {

        // adiciona os planos padroes ao iniciar o sistema
        academia.adicionarPlano(new PlanoMensal());
        academia.adicionarPlano(new PlanoTrimestral());
        academia.adicionarPlano(new PlanoAnual());

        int op = -1;

        // loop principal do menu
        do {
            menu();
            op = lerInteiro("Opcao: ");

            switch (op) {
                case 1: cadastrarAluno(); break;
                case 2: cadastrarProfessor(); break;
                case 3: academia.listarPessoas(); break;
                case 4: listarPlanos(); break;
                case 5: registrarPagamento(); break;
                case 6: listarPagamentos(); break;
                case 7: removerAluno(); break;
                case 8: removerProfessor(); break;
                case 9: cadastrarAlunoSemPlano(); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opcao invalida. Escolha um numero do menu.");
            }

        } while (op != 0);

        sc.close();
    }

    static void menu() {
        System.out.println("\nGymControl");
        System.out.println("1 - Cadastrar aluno (com plano)");
        System.out.println("2 - Cadastrar professor");
        System.out.println("3 - Listar pessoas");
        System.out.println("4 - Listar planos");
        System.out.println("5 - Registrar pagamento");
        System.out.println("6 - Listar pagamentos");
        System.out.println("7 - Remover aluno");
        System.out.println("8 - Remover professor");
        System.out.println("9 - Cadastrar aluno (sem plano)");
        System.out.println("0 - Sair");
    }

    // metodos auxiliares de entrada com retry automatico

    // le um numero inteiro, pede de novo se vier letra
    static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                // usuario digitou algo que nao e numero (ex: letra)
                System.out.println("Valor invalido. Digite apenas numeros inteiros.");
                sc.nextLine();
            } catch (NoSuchElementException e) {
                // a entrada foi encerrada (fim do input / EOF) - encerra com seguranca
                System.out.println("\nEntrada encerrada. Finalizando o programa.");
                sc.close();
                System.exit(0);
            }
        }
    }

    // le um texto, pede de novo se vier vazio
    static String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = sc.nextLine();
            if (texto != null && !texto.trim().isEmpty()) {
                return texto.trim();
            }
            System.out.println("O valor nao pode ser vazio. Tente novamente.");
        }
    }

    // aluno

    // cadastra um aluno JA com plano (usa o construtor de 4 parametros)
    static void cadastrarAluno() {
        System.out.println("\nCadastro de aluno (com plano)");

        String nome = lerTexto("Nome: ");
        String cpf = lerCpfValido();
        int idade = lerInteiro("Idade: ");
        Plano plano = lerPlanoValido();

        // a validacao da idade acontece dentro da Pessoa (setIdade);
        // aqui capturamos a excecao para o programa nao quebrar
        try {
            academia.adicionarAluno(new Aluno(nome, cpf, idade, plano));
            System.out.println("Aluno cadastrado com sucesso.");
        } catch (IdadeInvalidaException | IllegalArgumentException e) {
            System.out.println("Falha no cadastro: " + e.getMessage());
        }
    }

    // cadastra um aluno SEM plano (usa o construtor de 3 parametros - sobrecarga)
    static void cadastrarAlunoSemPlano() {
        System.out.println("\nCadastro de aluno (sem plano)");

        String nome = lerTexto("Nome: ");
        String cpf = lerCpfValido();
        int idade = lerInteiro("Idade: ");

        // aqui usamos o construtor sobrecarregado de 3 parametros (sem plano)
        try {
            academia.adicionarAluno(new Aluno(nome, cpf, idade));
            System.out.println("Aluno cadastrado sem plano. Use o cadastro com plano para definir depois.");
        } catch (IdadeInvalidaException | IllegalArgumentException e) {
            System.out.println("Falha no cadastro: " + e.getMessage());
        }
    }

    static void removerAluno() {
        System.out.println("\nRemover aluno");
        String cpf = lerTexto("CPF do aluno: ");

        if (academia.removerAluno(cpf)) {
            System.out.println("Aluno removido com sucesso.");
        } else {
            System.out.println("Aluno nao encontrado.");
        }
    }

    // professor

    static void cadastrarProfessor() {
        System.out.println("\nCadastro de professor");

        String nome = lerTexto("Nome: ");
        String cpf = lerCpfValido();
        int idade = lerInteiro("Idade: ");
        String especialidade = lerTexto("Especialidade: ");

        // mesma logica do aluno: a Pessoa valida, o Main captura
        try {
            academia.adicionarProfessor(new Professor(nome, cpf, idade, especialidade));
            System.out.println("Professor cadastrado com sucesso.");
        } catch (IdadeInvalidaException | IllegalArgumentException e) {
            System.out.println("Falha no cadastro: " + e.getMessage());
        }
    }

    static void removerProfessor() {
        System.out.println("\nRemover professor");
        String cpf = lerTexto("CPF do professor: ");

        if (academia.removerProfessor(cpf)) {
            System.out.println("Professor removido com sucesso.");
        } else {
            System.out.println("Professor nao encontrado.");
        }
    }

    // validadores que ficam pedindo ate o valor ser valido

    // pede o CPF ate ter 11 digitos numericos
    static String lerCpfValido() {
        while (true) {
            String cpf = lerTexto("CPF (11 digitos): ");

            if (cpf.length() != 11) {
                System.out.println("CPF deve ter exatamente 11 digitos. Tente novamente.");
                continue;
            }

            if (!cpf.matches("\\d+")) {
                System.out.println("CPF deve conter apenas numeros. Tente novamente.");
                continue;
            }

            return cpf;
        }
    }

    // pede ate escolher um plano valido (0, 1 ou 2)
    static Plano lerPlanoValido() {
        while (true) {
            System.out.println("Selecione um plano: 0-Mensal 1-Trimestral 2-Anual");
            int opcao = lerInteiro("Opcao do plano: ");

            Plano plano = academia.getPlano(opcao);

            if (plano == null) {
                System.out.println("Plano invalido. Escolha entre 0, 1 ou 2.");
                continue;
            }

            return plano;
        }
    }

    // planos

    static void listarPlanos() {
        System.out.println("\nPlanos disponiveis");
        for (Plano p : academia.getPlanos()) {
            p.exibirInformacoes();
        }
    }

    // pagamentos

    static void registrarPagamento() {
        System.out.println("\nRegistrar pagamento");
        String cpf = lerTexto("CPF do aluno: ");

        Aluno a = academia.buscarAlunoCpf(cpf);

        if (a == null) {
            System.out.println("Aluno nao encontrado.");
            return;
        }

        if (a.getPlano() == null) {
            System.out.println("Este aluno nao possui plano contratado.");
            return;
        }

        // exemplo de try-catch-finally completo
        try {
            academia.adicionarPagamento(
                new Pagamento(a, a.getPlano().calcularValor(), "hoje")
            );
            System.out.println("Pagamento registrado com sucesso.");
        } catch (Exception e) {
            System.out.println("Falha ao registrar pagamento: " + e.getMessage());
        } finally {
            System.out.println("Operacao de pagamento finalizada.");
        }
    }

    static void listarPagamentos() {
        if (academia.getPagamentos().isEmpty()) {
            System.out.println("Nenhum pagamento registrado ainda.");
            return;
        }

        System.out.println("\nPagamentos registrados");
        for (Pagamento p : academia.getPagamentos()) {
            p.exibir();
        }
    }
}
