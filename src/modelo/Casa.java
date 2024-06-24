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
    private double descontoAplicado; // Variável para armazenar o desconto aplicado

    public Casa(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, double tamanhoAreaConstruida, double tamanhoTerreno) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.tamanhoAreaConstruida = tamanhoAreaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
        this.descontoAplicado = 0; // Inicializa o desconto aplicado como zero
    }

    public double getTamanhoAreaConstruida() {
        return tamanhoAreaConstruida;
    }

    public double getTamanhoTerreno() {
        return tamanhoTerreno;
    }

    public double getDescontoAplicado() {
        return descontoAplicado;
    }

    @Override
    public double calcularPagamentoMensal() {
        // Calcula o valor dos juros mensais
        double taxaMensal = getTaxaJurosAnual() / 12 / 100;
        int n = getPrazoFinanciamento() * 12;
        double jurosMensal = (getValorImovel() * Math.pow((1 + taxaMensal), n) * taxaMensal) / (Math.pow((1 + taxaMensal), n) - 1);

        // Calcula o pagamento mensal considerando o desconto
        return jurosMensal - descontoAplicado;
    }

    public void aplicarDesconto(double desconto) throws DescontoMaiorDoQueJurosException {
        // Calcula o valor dos juros mensais
        double taxaMensal = getTaxaJurosAnual() / 12 / 100;
        int n = getPrazoFinanciamento() * 12;
        double jurosMensal = (getValorImovel() * Math.pow((1 + taxaMensal), n) * taxaMensal) / (Math.pow((1 + taxaMensal), n) - 1);

        // Verifica se o desconto é maior que os juros mensais
        if (desconto > jurosMensal) {
            throw new DescontoMaiorDoQueJurosException("O valor do desconto não pode ser maior do que os juros da mensalidade.");
        }

        // Aplica o desconto
        this.descontoAplicado = desconto;
    }

    @Override
    public void mostrarDadosFinanciamento() {
        System.out.println("\nTipo de imóvel: Casa");
        super.mostrarDadosFinanciamento();
        System.out.println("Tamanho da área construída: " + tamanhoAreaConstruida + " m²");
        System.out.println("Tamanho do terreno: " + tamanhoTerreno + " m²");
        System.out.println("Desconto aplicado: " + getDescontoAplicado());
    }
}