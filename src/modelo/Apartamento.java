/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package modelo;

public class Apartamento extends Financiamento {
    private int numeroVagasGaragem;
    private int numeroAndar;

    public Apartamento(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, int numeroVagasGaragem, int numeroAndar) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.numeroVagasGaragem = numeroVagasGaragem;
        this.numeroAndar = numeroAndar;
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
        System.out.println("Número de vagas na garagem: " + this.numeroVagasGaragem);
        System.out.println("Número do andar: " + this.numeroAndar);
    }
}







