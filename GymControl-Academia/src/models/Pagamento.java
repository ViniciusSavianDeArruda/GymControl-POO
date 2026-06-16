package models;

/*
 * representa um pagamento realizado por um aluno.
 *
 * esta classe armazena as informações do pagamento,
 * incluindo o aluno responsável, o valor pago e a data.
 */
public class Pagamento {

    private Aluno aluno;
    private double valor;
    private String data;

    // cria um novo registro de pagamento
    public Pagamento(Aluno aluno, double valor, String data) {
        this.aluno = aluno;
        this.valor = valor;
        this.data = data;
    }

    /*
     * exibe os dados do pagamento no terminal.
     *
     * utilizada na funcionalidade de listagem
     * dos pagamentos registrados no sistema.
     */
    public void exibir() {
        System.out.println(aluno.getNome() + " | R$ " + valor + " | " + data);
    }

    // retorna o aluno associado ao pagamento
    public Aluno getAluno() {
        return aluno;
    }
}
