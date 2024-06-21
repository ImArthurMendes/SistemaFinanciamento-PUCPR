/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package main;
import modelo.*;
import util.InterfaceUsuario;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        ArrayList<Financiamento> financiamentos = new ArrayList<>();

        // Pedir dados ao usuário para um financiamento
        System.out.println("\n---------------------------------------");
        System.out.println("Insira os dados para o financiamento:");
        double taxaJuros = interfaceUsuario.pedirTaxaJuros();
        int prazoFinanciamentoEmAnos = interfaceUsuario.pedirPrazoFinanciamento();
        double valorImovel = interfaceUsuario.pedirValorImovel();

        // Adiciona o financiamento do usuário
        System.out.println("\n");
        Financiamento financiamentoUsuario = new Financiamento (valorImovel, prazoFinanciamentoEmAnos, taxaJuros);
        financiamentos.add(financiamentoUsuario);

        // Adiciona os demais financiamentos diretamente
        financiamentos.add(new Casa(300000, 20, 8.5));
        financiamentos.add(new Casa(250000, 15, 7.0));
        financiamentos.add(new Apartamento(450000, 15, 10));
        financiamentos.add(new Apartamento(200000, 10, 9));
        financiamentos.add(new Terreno(150000, 12, 11));

        double totalValorImoveis = 0;
        double totalValorFinanciamentos = 0;

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        // Imprimir dados dos financiamentos
        for (Financiamento f : financiamentos) {
            f.mostrarDadosFinanciamento();
            totalValorImoveis += f.getValorImovel();
            totalValorFinanciamentos += f.calcularTotalPagamento();
        }

        System.out.println("\n========================================");
        System.out.println("Total de todos os imóveis: " + currencyFormatter.format(totalValorImoveis));
        System.out.println("Total de todos os financiamentos: " + currencyFormatter.format(totalValorFinanciamentos));
        System.out.println("========================================");
    }
}