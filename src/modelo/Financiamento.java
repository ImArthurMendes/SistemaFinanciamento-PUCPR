/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package modelo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class Financiamento {
    protected double valorImovel;
    protected int prazoFinanciamento;
    protected double taxaJurosAnual;

    public double getValorImovel() {
        return this.valorImovel;
    }

    public int getPrazoFinanciamento() {
        return this.prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return this.taxaJurosAnual;
    }

    public Financiamento(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual) {
        this.valorImovel = valorDesejadoImovel;
        this.prazoFinanciamento = prazoFinanciamentoAnos;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    // Método abstrato para ser implementado nas subclasses
    public abstract double calcularPagamentoMensal();

    public double calcularTotalPagamento() {
        return this.calcularPagamentoMensal() * this.prazoFinanciamento * 12;
    }

    public void mostrarDadosFinanciamento() {
        double pagamentoMensal = this.calcularPagamentoMensal();
        double totalPagamento = this.calcularTotalPagamento();

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        DecimalFormat decimalFormatter = new DecimalFormat("0.0#");

        System.out.println("Valor do imóvel: " + currencyFormatter.format(this.valorImovel));
        System.out.println("Prazo do financiamento: " + this.prazoFinanciamento + " anos");
        System.out.println("Taxa de juros anual: " + decimalFormatter.format(this.taxaJurosAnual) + "%");
        System.out.println("Pagamento mensal: " + currencyFormatter.format(pagamentoMensal));
        System.out.println("Total do pagamento: " + currencyFormatter.format(totalPagamento));
    }
}