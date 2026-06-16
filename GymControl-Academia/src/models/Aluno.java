package models;

import interfaces.Exibivel;

/*
 * representa um aluno da academia.
 *
 * esta classe herda os atributos e comportamentos da classe Pessoa
 * e adiciona a informação do plano contratado pelo aluno.
 */
public class Aluno extends Pessoa implements Exibivel {

    // plano associado ao aluno
    private Plano plano;

    // construtor sem plano
    // utilizado quando o aluno é cadastrado sem contratar um plano
    public Aluno(String nome, String cpf, int idade) {
        super(nome, cpf, idade);
    }

    // construtor com plano
    // utilizado quando o aluno já é cadastrado com um plano definido
    public Aluno(String nome, String cpf, int idade, Plano plano) {
        super(nome, cpf, idade);
        this.plano = plano;
    }

    public Plano getPlano() {
        return plano;
    }

    // permite alterar ou definir o plano do aluno
    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    /*
     * implementação obrigatória do método abstrato da classe Pessoa.
     *
     * exemplo de polimorfismo por sobrescrita:
     * cada subclasse de Pessoa possui sua própria versão
     * do método exibirDados().
     */
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

    /*
     * implementação do método definido pela interface Exibivel.
     *
     * garante que todas as classes que implementam a interface
     * possuam uma forma padronizada de exibir informações.
     */
    @Override
    public void exibirInformacoes() {
        System.out.println(getNome() + " (Aluno)");
    }
}
