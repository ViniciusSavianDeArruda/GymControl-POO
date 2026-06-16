package models;

import interfaces.Exibivel;

/*
 * representa um plano da academia.
 *
 * esta é uma classe abstrata que serve como base
 * para os diferentes tipos de plano disponíveis
 * no sistema.
 *
 * as classes filhas são responsáveis por definir
 * como o valor final do plano será calculado.
 */
public abstract class Plano implements Exibivel {

    private String nomePlano;
    private double valorBase;

    /*
     * construtor utilizado pelas subclasses
     * para definir o nome e o valor base
     * do plano.
     */
    public Plano(String nomePlano, double valorBase) {
        this.nomePlano = nomePlano;
        this.valorBase = valorBase;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public double getValorBase() {
        return valorBase;
    }

    /*
     * método abstrato que deve ser implementado
     * pelas classes filhas.
     *
     * cada tipo de plano possui sua própria
     * regra de cálculo de valor.
     */
    public abstract double calcularValor();

    /*
     * implementação do método definido
     * pela interface Exibivel.
     *
     * exibe o nome do plano e o valor
     * calculado pela respectiva subclasse.
     */
    @Override
    public void exibirInformacoes() {
        System.out.println(nomePlano + " - R$ " + calcularValor());
    }
}
