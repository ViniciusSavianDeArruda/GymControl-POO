package models;

import exceptions.IdadeInvalidaException;

/*
 * representa uma pessoa da academia.
 *
 * esta é uma classe abstrata que serve como base
 * para alunos e professores.
 *
 * ela reúne os atributos e comportamentos
 * que são comuns a todas as pessoas do sistema.
 */
public abstract class Pessoa {

    private String nome;
    private String cpf;
    private int idade;

    // construtor padrão
    public Pessoa() {
    }

    /*
     * construtor parametrizado.
     *
     * utiliza os métodos setters para garantir
     * que os dados sejam validados durante
     * a criação do objeto.
     */
    public Pessoa(String nome, String cpf, int idade) {
        setNome(nome);
        setCpf(cpf);
        setIdade(idade);
    }

    /*
     * método abstrato que deve ser implementado
     * pelas classes filhas.
     *
     * cada tipo de pessoa exibe seus dados
     * de maneira específica.
     */
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

    /*
     * valida o nome informado pelo usuário.
     *
     * não permite nomes vazios, muito curtos
     * ou compostos apenas por números.
     */
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome nao pode ser vazio");

        if (nome.trim().length() < 2)
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");

        if (nome.trim().matches("\\d+"))
            throw new IllegalArgumentException("Nome nao pode ser apenas numeros");

        this.nome = nome.trim();
    }

    /*
     * valida o cpf informado.
     *
     * o cpf deve possuir exatamente
     * 11 dígitos numéricos.
     */
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

    /*
     * valida a idade informada.
     *
     * caso a idade esteja fora da faixa permitida,
     * o sistema lança a exceção personalizada
     * IdadeInvalidaException.
     *
     * essa validação garante que apenas pessoas
     * com idade entre 16 e 80 anos sejam cadastradas.
     */
    public void setIdade(int idade) {
        if (idade < 16 || idade > 80)
            throw new IdadeInvalidaException(
                "Idade invalida: " + idade + ". Deve estar entre 16 e 80 anos."
            );

        this.idade = idade;
    }
}
