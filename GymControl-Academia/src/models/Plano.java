package models;

import interfaces.Exibivel;

// Classe abstrata que serve de base para todos os planos da academia.
public abstract class Plano implements Exibivel {

    private String nomePlano;
    private double valorBase;

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

    // Cada tipo de plano calcula seu valor de forma diferente.
    public abstract double calcularValor();

    // Implementação do método definido na interface Exibivel.
    @Override
    public void exibirInformacoes() {
        System.out.println(nomePlano + " - R$ " + calcularValor());
    }
}
