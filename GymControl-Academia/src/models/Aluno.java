package models;

import interfaces.Exibivel;

// Representa um aluno da academia.
public class Aluno extends Pessoa implements Exibivel {

    // Plano associado ao aluno.
    private Plano plano;

    // Construtor sem plano (sobrecarga).
    public Aluno(String nome, String cpf, int idade) {
        super(nome, cpf, idade);
    }

    // Construtor com plano.
    public Aluno(String nome, String cpf, int idade, Plano plano) {
        super(nome, cpf, idade);
        this.plano = plano;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    // Sobrescrita do método abstrato da classe Pessoa.
    @Override
    public void exibirDados() {
        System.out.println("\nAluno: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Idade: " + getIdade());

        if (plano != null)
            System.out.println("Plano: " + plano.getNomePlano());
        else
            System.out.println("Sem plano");
    }

    // Implementação do método da interface Exibivel.
    @Override
    public void exibirInformacoes() {
        System.out.println(getNome() + " (Aluno)");
    }
}
