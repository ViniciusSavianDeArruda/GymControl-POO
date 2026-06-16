package models;

import interfaces.Exibivel;

/*
 * representa um professor da academia.
 *
 * esta classe herda de Pessoa e adiciona
 * a informação de especialidade.
 *
 * também implementa a interface Exibivel,
 * permitindo exibir informações de forma padronizada.
 */
public class Professor extends Pessoa implements Exibivel {
    private String especialidade;

    /*
     * construtor responsável por criar
     * um professor com seus dados básicos
     * e sua especialidade.
     */
    public Professor(String nome, String cpf, int idade, String especialidade) {
        super(nome, cpf, idade);
        setEspecialidade(especialidade);
    }

    public String getEspecialidade() {
        return especialidade;
    }

    /*
     * valida a especialidade informada.
     *
     * não permite que o campo fique vazio,
     * garantindo a consistência dos dados.
     */
    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.trim().isEmpty())
            throw new IllegalArgumentException("Especialidade nao pode ser vazia");

        this.especialidade = especialidade.trim();
    }

    /*
     * implementação do método abstrato
     * definido na classe Pessoa.
     *
     * exibe todas as informações do professor.
     *
     * este é um exemplo de polimorfismo
     * por sobrescrita de método.
     */
    @Override
    public void exibirDados() {
        System.out.println("\nProfessor: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Idade: " + getIdade());
        System.out.println("Especialidade: " + especialidade);
    }

    /*
     * implementação do método definido
     * pela interface Exibivel.
     *
     * permite exibir uma versão resumida
     * das informações do professor.
     */
    @Override
    public void exibirInformacoes() {
        System.out.println(getNome() + " (Professor)");
    }
}
