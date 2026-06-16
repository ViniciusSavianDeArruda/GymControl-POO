package models;

import exceptions.IdadeInvalidaException;

// Classe abstrata que representa uma pessoa da academia.
public abstract class Pessoa {

    private String nome;
    private String cpf;
    private int idade;

    // Construtor padrão.
    public Pessoa() {
    }

    // Construtor parametrizado.
    public Pessoa(String nome, String cpf, int idade) {
        setNome(nome);
        setCpf(cpf);
        setIdade(idade);
    }

    // Método abstrato implementado pelas subclasses.Toda classe que herdar de Pessoa será obrigada a criar seu próprio método exibirDados()
    public abstract void exibirDados();

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    // Validação do nome.
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome nao pode ser vazio");

        if (nome.trim().length() < 2)
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");

        if (nome.trim().matches("\\d+"))
            throw new IllegalArgumentException("Nome nao pode ser apenas numeros");

        this.nome = nome.trim();
    }

    // Validação do CPF.
    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty())
            throw new IllegalArgumentException("CPF nao pode ser vazio");

        String cpfLimpo = cpf.trim();

        if (cpfLimpo.length() != 11)
            throw new IllegalArgumentException("CPF deve ter 11 digitos");

        if (!cpfLimpo.matches("\\d+"))
            throw new IllegalArgumentException("CPF deve conter apenas numeros");

        this.cpf = cpfLimpo;
    }

    // Regra de negócio protegida por exceção customizada.
    public void setIdade(int idade) {
        if (idade < 16 || idade > 80)
            throw new IdadeInvalidaException(
                "Idade invalida: " + idade + ". Deve estar entre 16 e 80 anos."
            );

        this.idade = idade;
    }
}
