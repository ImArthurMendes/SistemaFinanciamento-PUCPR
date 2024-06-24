/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package modelo;

public class Casa extends Financiamento {
    private double tamanhoAreaConstruida;
    private double tamanhoTerreno;

    public Casa(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, double tamanhoAreaConstruida, double tamanhoTerreno) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.tamanhoAreaConstruida = tamanhoAreaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = taxaJurosAnual / 12 / 100;
        int n = prazoFinanciamento * 12;
        return ((valorImovel * Math.pow((1 + taxaMensal), n) * taxaMensal) / (Math.pow((1 + taxaMensal), n) - 1)) + 80;
    }

    @Override
    public void mostrarDadosFinanciamento() {
        System.out.println("\nTipo de imóvel: Casa");
        super.mostrarDadosFinanciamento();
        System.out.println("Tamanho da área construída: " + this.tamanhoAreaConstruida + " m²");
        System.out.println("Tamanho do terreno: " + this.tamanhoTerreno + " m²");
    }
}