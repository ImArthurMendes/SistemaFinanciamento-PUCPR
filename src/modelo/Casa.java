/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package modelo;

import util.DescontoMaiorDoQueJurosException;

public class Casa extends Financiamento {
    private double tamanhoAreaConstruida;
    private double tamanhoTerreno;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double tamanhoAreaConstruida, double tamanhoTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tamanhoAreaConstruida = tamanhoAreaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    public double getTamanhoAreaConstruida() {
        return tamanhoAreaConstruida;
    }

    public double getTamanhoTerreno() {
        return tamanhoTerreno;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = Math.pow(1 + this.taxaJurosAnual / 100, 1.0 / 12) - 1;
        int numeroMeses = this.prazoFinanciamento * 12;
        return this.valorImovel * taxaMensal / (1 - Math.pow(1 + taxaMensal, -numeroMeses));
    }

    public void aplicarDesconto(double desconto) throws DescontoMaiorDoQueJurosException {
        double pagamentoMensalSemDesconto = this.calcularPagamentoMensal();
        double pagamentoMensalComDesconto = pagamentoMensalSemDesconto - desconto;
        if (pagamentoMensalComDesconto < 0) {
            throw new DescontoMaiorDoQueJurosException("O desconto é maior do que o valor do pagamento mensal.");
        }
        //NESTA PARTE DO CÓDIGO, É LANÇADO A EXCESÃO, O DESCONTO É APLICADO AO PAGAMENTO MENSAL
    }

    @Override
    public String toString() {
        return super.toString() + ";" + this.tamanhoAreaConstruida + ";" + this.tamanhoTerreno;
    }
}