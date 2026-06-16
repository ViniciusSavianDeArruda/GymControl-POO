package models;

// Representa o plano anual da academia
public class PlanoAnual extends Plano {

    public PlanoAnual() {
        // nome do plano e valor base utilizado nos calculos
        super("Anual", 120);
    }

    @Override
    public double calcularValor() {
        // aplica 25% de desconto sobre o valor base
        return getValorBase() * 0.75;
    }
}
