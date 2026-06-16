package models;
/*
 * representa o plano anual da academia.
 *
 * esta classe herda da classe Plano
 * e define a regra de cálculo específica
 * para o plano anual.
 */
public class PlanoAnual extends Plano {

    /*
     * cria um plano anual com nome
     * e valor base pré-definidos.
     */
    public PlanoAnual() {
        super("Anual", 120);
    }

    /*
     * calcula o valor do plano anual.
     *
     * neste plano é aplicado um desconto
     * de 25% sobre o valor base.
     */
    @Override
    public double calcularValor() {
        return getValorBase() * 0.75;
    }
}
