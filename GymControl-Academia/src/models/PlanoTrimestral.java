package models;

// Representa o plano trimestral da academia
public class PlanoTrimestral extends Plano {

    public PlanoTrimestral() {
        // nome do plano e valor base
        super("Trimestral", 120);
    }

    @Override
    public double calcularValor() {
        // aplica 10% de desconto sobre o valor base
        return getValorBase() * 0.9;
    }
}
