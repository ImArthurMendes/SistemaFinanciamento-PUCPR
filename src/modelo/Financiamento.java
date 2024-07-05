/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package modelo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class Financiamento implements Serializable {
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

    public Financiamento(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual) throws IllegalArgumentException {
        if (taxaJurosAnual > 12.0) { //NESTA PARTE DO CÓDIGO, SE A TAXA DO FINANCIAMENTO FOR SUPERIOR A 12%, IRÁ SER IMPRESSO NO TERMINAL A MENSAGEM DE QUE A TAXA É ABUSIVA.
            throw new IllegalArgumentException("A taxa de juros ultrapassa o limite estabelecido pela Lei nº 1.521/51, sendo acima de 12%. Por gentileza, escolha uma taxa de juros válida (sem %).");
        }
        if (prazoFinanciamentoAnos > 35) { //NESTA PARTE DO CÓDIGO, SE O PRAZO DE FINANCIAMENTO FOR SUPERIOR A 35 ANOS, IRÁ SER IMPRESSO NO TERMINAL A MENSAGEM DE QUE O PRAZO É ACIMA DO USUAL NO MERCADO
            throw new IllegalArgumentException("O prazo de financiamento fornecido é acima do utilizado no mercado, sendo superior há 35 anos. Por gentileza, escolha um prazo de financiamento válido.");
        }

        this.valorImovel = valorDesejadoImovel;
        this.prazoFinanciamento = prazoFinanciamentoAnos;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public abstract double calcularPagamentoMensal();

    public double calcularTotalPagamento() {
        return this.calcularPagamentoMensal() * this.prazoFinanciamento * 12;
    }
    //NESTA PARTE DO CÓDIGO, AS INFORMAÇÕES SÃO IMPRESSAS NO TERMINAL, INFORMAÇÕES COMO: VALOR DO IMÓVEL, PRAZO DO FINANCIAMENTO, TAXA DE JUROS ANUAL, PAGAMENTO MENSAL E TOTAL DO PAGAMENTO.
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

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ";" + this.valorImovel + ";" + this.prazoFinanciamento + ";" + this.taxaJurosAnual;
    }

    public static Financiamento fromString(String financiamentoStr) {
        String[] parts = financiamentoStr.split(";");
        String tipoImovel = parts[0];
        double valorImovel = Double.parseDouble(parts[1]);
        int prazoFinanciamento = Integer.parseInt(parts[2]);
        double taxaJuros = Double.parseDouble(parts[3]);

        switch (tipoImovel) {
            case "Casa": //NESTA PARTE DO CÓDIGO, SE O IMÓVEL FOR UMA CASA A MÁQUINA IRÁ SOLICITAR AO USUÁRIO INFORMAÇÕES COMO: TAMANHO DO TERRENO E O TAMANHO DA ÁREA CONSTRUIDA
                double tamanhoAreaConstruida = Double.parseDouble(parts[4]);
                double tamanhoTerreno = Double.parseDouble(parts[5]);
                return new Casa(valorImovel, prazoFinanciamento, taxaJuros, tamanhoAreaConstruida, tamanhoTerreno);
            case "Apartamento": //NESTA PARTE DO CÓDIGO, SE O IMÓVEL FOR UM APARTAMENTO A MÁQUINA IRÁ SOLICITAR AO USUÁRIO INFORMAÇÕES COMO: NÚMERO DE VAGAS E QUAL O ANDAR QUE O APARTAMENTO SE ENCONTRA
                int numeroVagasGaragem = Integer.parseInt(parts[4]);
                int numeroAndar = Integer.parseInt(parts[5]);
                return new Apartamento(valorImovel, prazoFinanciamento, taxaJuros, numeroVagasGaragem, numeroAndar);
            case "Terreno": //NESTA PARTE DO CÓDIGO, SE O IMÓVEL FOR UM TERRENO A MÁQUINA IRÁ SOLICITAR AO USUÁRIO INFORMAÇÕES COMO: QUAL O TIPO DE ZONA DO TERRENO? COMERCIAL, RESIDENCIAL OU INDUSTRIAL?
                String tipoZona = parts[4];
                return new Terreno(valorImovel, prazoFinanciamento, taxaJuros, tipoZona);
            default: //NESTA PARTE DO CÓDIGO, SE O USUÁRIO INSERIR UM IMÓVEL QUE NÃO SEJA UMA CASA, APARTAMENTO OU TERRENO, ELE IRÁ DIZER QUE O IMÓVEL É INCORRETO E SOLICITAR NOVAMENTE AO USUÁRIO QUE ELE INFORME UM IMÓVEL VÁLIDO
                throw new IllegalArgumentException("Tipo de imóvel desconhecido: " + tipoImovel);
        }
    }
}