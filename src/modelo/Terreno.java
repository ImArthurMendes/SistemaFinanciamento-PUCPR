/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package modelo;

import java.io.Serializable;

public class Terreno extends Financiamento implements Serializable {
    private String tipoZona;

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoZona) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    public String getTipoZona() {
        return tipoZona;
    }
    //NESTA PARTE DO CÓDIGO, É REALIZADO O CÁLCULO DO PAGAMENTO MENSAL DAS PARCELAS DO APARTAMENTO, DE ACORDO COM AS INFORMAÇÕES INSERIDAS PELO USUÁRIO NO ARQUIVO MAIN.JAVA
    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = Math.pow(1 + this.taxaJurosAnual / 100, 1.0 / 12) - 1;
        int numeroMeses = this.prazoFinanciamento * 12;
        return this.valorImovel * taxaMensal / (1 - Math.pow(1 + taxaMensal, -numeroMeses));
    }

    @Override
    public String toString() {
        return super.toString() + ";" + this.tipoZona;
    }
}