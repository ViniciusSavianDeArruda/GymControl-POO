package models;

// Representa um pagamento realizado por um aluno.
public class Pagamento {

    private Aluno aluno;
    private double valor;
    private String data;

    public Pagamento(Aluno aluno, double valor, String data) {
        this.aluno = aluno;
        this.valor = valor;
        this.data = data;
    }

    // Exibe as informações do pagamento.
    public void exibir() {
        System.out.println(aluno.getNome() + " | R$ " + valor + " | " + data);
    }

    public Aluno getAluno() {
        return aluno;
    }
}
