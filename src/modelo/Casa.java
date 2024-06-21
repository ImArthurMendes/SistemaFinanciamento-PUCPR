/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package modelo;

public class Casa extends Financiamento {
    public Casa(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual);
    }

    @Override
    public double calcularPagamentoMensal() {
        return super.calcularPagamentoMensal() + 80;
    }

    @Override
    public void mostrarDadosFinanciamento() {
        System.out.println("\nTipo de imóvel: Casa");
        super.mostrarDadosFinanciamento();
    }
}