/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package modelo;

public class Apartamento extends Financiamento {
    public Apartamento(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual);
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = taxaJurosAnual / 12 / 100;
        int n = prazoFinanciamento * 12;
        return (valorImovel * Math.pow((1 + taxaMensal), n) * taxaMensal) / (Math.pow((1 + taxaMensal), n) - 1);
    }

    @Override
    public void mostrarDadosFinanciamento() {
        System.out.println("\nTipo de imóvel: Apartamento");
        super.mostrarDadosFinanciamento();
    }
}
