package models;

// Representa o plano mensal da academia
public class PlanoMensal extends Plano {

    public PlanoMensal() {
        // nome do plano e valor base
        super("Mensal", 120);
    }

    @Override
    public double calcularValor() {
        // no plano mensal não há desconto
        return getValorBase();
    }
}
