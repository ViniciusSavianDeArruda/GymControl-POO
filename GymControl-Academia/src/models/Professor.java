package models;

import interfaces.Exibivel;

// Representa um professor da academia
public class Professor extends Pessoa implements Exibivel {

    private String especialidade;

    public Professor(String nome, String cpf, int idade, String especialidade) {
        super(nome, cpf, idade);
        setEspecialidade(especialidade);
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        // valida se a especialidade foi informada
        if (especialidade == null || especialidade.trim().isEmpty())
            throw new IllegalArgumentException("Especialidade nao pode ser vazia");

        this.especialidade = especialidade.trim();
    }

    @Override
    public void exibirDados() {
        // sobrescrita do metodo abstrato da classe Pessoa
        System.out.println("\nProfessor: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Idade: " + getIdade());
        System.out.println("Especialidade: " + especialidade);
    }

    @Override
    public void exibirInformacoes() {
        // implementacao do metodo definido pela interface Exibivel
        System.out.println(getNome() + " (Professor)");
    }
}
