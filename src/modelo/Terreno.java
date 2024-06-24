/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package modelo;

public class Terreno extends Financiamento {
    private String tipoZona;

    public Terreno(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, String tipoZona) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = taxaJurosAnual / 12 / 100;
        int n = prazoFinanciamento * 12;
        return ((valorImovel * Math.pow((1 + taxaMensal), n) * taxaMensal) / (Math.pow((1 + taxaMensal), n) - 1)) * 1.02;
    }

    @Override
    public void mostrarDadosFinanciamento() {
        System.out.println("\nTipo de imóvel: Terreno");
        super.mostrarDadosFinanciamento();
        System.out.println("Tipo de zona: " + this.tipoZona);
    }
}