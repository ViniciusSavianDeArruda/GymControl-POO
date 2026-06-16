package models;

import java.util.ArrayList;
import java.util.List;

/*
 * Classe responsável por centralizar as regras de negócio do sistema.
 * Gerencia os cadastros de alunos e professores, os planos disponíveis,
 * os pagamentos realizados e as operações de busca, remoção e listagem.
 *
 * Dessa forma, a classe Main fica responsável apenas pela interação
 * com o usuário, enquanto a Academia concentra a lógica do sistema.
 */
public class Academia {

    // Coleções que armazenam os dados do sistema em memória
    private List<Aluno> alunos = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();
    private List<Plano> planos = new ArrayList<>();
    private List<Pagamento> pagamentos = new ArrayList<>();

    // ALUNOS
    public void adicionarAluno(Aluno a) {

        // Regra de negócio: não permitir CPFs duplicados
        if (cpfJaExiste(a.getCpf()))
            throw new IllegalArgumentException("Ja existe uma pessoa com este CPF");

        alunos.add(a);
    }

    public boolean removerAluno(String cpf) {
        Aluno a = buscarAlunoCpf(cpf);

        if (a != null) {
            alunos.remove(a);
            return true;
        }

        return false;
    }

    public Aluno buscarAlunoCpf(String cpf) {

        // Busca sequencial pelo CPF informado
        for (Aluno a : alunos) {
            if (a.getCpf().equals(cpf))
                return a;
        }

        return null;
    }

    // PROFESSORES
    public void adicionarProfessor(Professor p) {

        // Regra de negócio: CPF deve ser único no sistema
        if (cpfJaExiste(p.getCpf()))
            throw new IllegalArgumentException("Ja existe uma pessoa com este CPF");

        professores.add(p);
    }

    public boolean removerProfessor(String cpf) {
        Professor p = buscarProfessorCpf(cpf);

        if (p != null) {
            professores.remove(p);
            return true;
        }

        return false;
    }

    public Professor buscarProfessorCpf(String cpf) {

        // Busca professor pelo CPF
        for (Professor p : professores) {
            if (p.getCpf().equals(cpf))
                return p;
        }

        return null;
    }

    // PLANOS

    public void adicionarPlano(Plano p) {
        planos.add(p);
    }

    public Plano getPlano(int index) {

        // Evita acesso a posições inexistentes da lista
        if (index < 0 || index >= planos.size())
            return null;

        return planos.get(index);
    }

    // PAGAMENTOS

    public void adicionarPagamento(Pagamento p) {
        pagamentos.add(p);
    }

    //  LISTAGEM

    public void listarPessoas() {

        if (alunos.isEmpty() && professores.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }

        /*
         * Polimorfismo:
         * Aluno e Professor são tratados de forma uniforme
         * através da superclasse Pessoa.
         */
        List<Pessoa> pessoas = new ArrayList<>();

        pessoas.addAll(alunos);
        pessoas.addAll(professores);

        System.out.println("\nPessoas cadastradas");

        for (Pessoa p : pessoas) {

            // Despacho dinâmico:
            // cada objeto executa sua própria implementação
            // de exibirDados()
            p.exibirDados();
        }
    }

    //  VALIDAÇÕES
    /*
     * Verifica se já existe uma pessoa cadastrada
     * utilizando o CPF informado.
     */
    private boolean cpfJaExiste(String cpf) {

        for (Aluno a : alunos)
            if (a.getCpf().equals(cpf))
                return true;

        for (Professor p : professores)
            if (p.getCpf().equals(cpf))
                return true;

        return false;
    }

    // GETTERS

    /*
     * Retornam cópias das listas para preservar
     * o encapsulamento da classe.
     */

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }

    public List<Professor> getProfessores() {
        return new ArrayList<>(professores);
    }

    public List<Plano> getPlanos() {
        return new ArrayList<>(planos);
    }

    public List<Pagamento> getPagamentos() {
        return new ArrayList<>(pagamentos);
    }
}
