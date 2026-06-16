package models;

import java.util.ArrayList;
import java.util.List;

/*
 * classe responsável por gerenciar os dados e as regras de negócio da academia.
 * ela controla alunos, professores, planos e pagamentos.
 *
 * a classe Main apenas recebe as entradas do usuário,
 * enquanto esta classe executa as operações do sistema.
 */
public class Academia {

    // listas que armazenam os dados cadastrados em memória
    private List<Aluno> alunos = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();
    private List<Plano> planos = new ArrayList<>();
    private List<Pagamento> pagamentos = new ArrayList<>();

    // métodos relacionados aos alunos

    public void adicionarAluno(Aluno a) {

        // impede o cadastro de cpfs repetidos
        if (cpfJaExiste(a.getCpf()))
            throw new IllegalArgumentException("Ja existe uma pessoa com este CPF");

        alunos.add(a);
    }

    public boolean removerAluno(String cpf) {

        // procura o aluno pelo cpf informado
        Aluno a = buscarAlunoCpf(cpf);

        if (a != null) {
            alunos.remove(a);
            return true;
        }

        return false;
    }

    public Aluno buscarAlunoCpf(String cpf) {

        // percorre a lista procurando um cpf igual
        for (Aluno a : alunos) {
            if (a.getCpf().equals(cpf))
                return a;
        }

        return null;
    }

    // métodos relacionados aos professores

    public void adicionarProfessor(Professor p) {

        // garante que o cpf seja único no sistema
        if (cpfJaExiste(p.getCpf()))
            throw new IllegalArgumentException("Ja existe uma pessoa com este CPF");

        professores.add(p);
    }

    public boolean removerProfessor(String cpf) {

        // procura o professor pelo cpf
        Professor p = buscarProfessorCpf(cpf);

        if (p != null) {
            professores.remove(p);
            return true;
        }

        return false;
    }

    public Professor buscarProfessorCpf(String cpf) {

        // percorre a lista de professores procurando o cpf
        for (Professor p : professores) {
            if (p.getCpf().equals(cpf))
                return p;
        }

        return null;
    }

    // métodos relacionados aos planos

    public void adicionarPlano(Plano p) {
        planos.add(p);
    }

    public Plano getPlano(int index) {

        // evita acessar posições inválidas da lista
        if (index < 0 || index >= planos.size())
            return null;

        return planos.get(index);
    }

    // métodos relacionados aos pagamentos

    public void adicionarPagamento(Pagamento p) {
        pagamentos.add(p);
    }

    // exibe todos os alunos e professores cadastrados

    public void listarPessoas() {

        if (alunos.isEmpty() && professores.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }

        /*
         * exemplo de polimorfismo:
         * alunos e professores são tratados como Pessoa.
         */
        List<Pessoa> pessoas = new ArrayList<>();

        pessoas.addAll(alunos);
        pessoas.addAll(professores);

        System.out.println("\nPessoas cadastradas");

        for (Pessoa p : pessoas) {

            /*
             * cada objeto executa sua própria versão
             * do método exibirDados().
             */
            p.exibirDados();
        }
    }

    // verifica se o cpf já está cadastrado

    private boolean cpfJaExiste(String cpf) {

        for (Aluno a : alunos)
            if (a.getCpf().equals(cpf))
                return true;

        for (Professor p : professores)
            if (p.getCpf().equals(cpf))
                return true;

        return false;
    }

    // retorna cópias das listas para manter o encapsulamento

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
