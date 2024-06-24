/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package main;

import modelo.*;
import util.DescontoMaiorDoQueJurosException;
import util.InterfaceUsuario;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        ArrayList<Financiamento> financiamentos = new ArrayList<>();

        // Pedir dados ao usuário para um financiamento
        System.out.println("\n---------------------------------------");
        System.out.println("Insira os dados para o financiamento:");
        double taxaJuros = interfaceUsuario.pedirTaxaJuros();
        int prazoFinanciamentoEmAnos = interfaceUsuario.pedirPrazoFinanciamento();
        double valorImovel = interfaceUsuario.pedirValorImovel();
        int tipoImovel = interfaceUsuario.pedirTipoImovel();

        Financiamento financiamentoUsuario = null;
        switch (tipoImovel) {
            case 1:
                double tamanhoAreaConstruida = interfaceUsuario.pedirTamanhoAreaConstruida();
                double tamanhoTerreno = interfaceUsuario.pedirTamanhoTerreno();
                financiamentoUsuario = new Casa(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, tamanhoAreaConstruida, tamanhoTerreno);
                financiamentos.add(financiamentoUsuario);
                break;
            case 2:
                int numeroVagasGaragem = interfaceUsuario.pedirNumeroVagasGaragem();
                int numeroAndar = interfaceUsuario.pedirNumeroAndar();
                financiamentoUsuario = new Apartamento(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, numeroVagasGaragem, numeroAndar);
                financiamentos.add(financiamentoUsuario);
                break;
            case 3:
                String tipoZona = interfaceUsuario.pedirTipoZona();
                financiamentoUsuario = new Terreno(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, tipoZona);
                financiamentos.add(financiamentoUsuario);
                break;
            default:
                System.out.println("Tipo de imóvel inválido.");
                System.exit(1);
        }

        // Aplicar desconto, se for uma casa
        if (financiamentoUsuario instanceof Casa) {
            double desconto = 0;
            boolean descontoValido = false;
            double jurosMensalidade = financiamentoUsuario.calcularPagamentoMensal() - (financiamentoUsuario.getValorImovel() / (financiamentoUsuario.getPrazoFinanciamento() * 12));
            while (!descontoValido) {
                try {
                    desconto = interfaceUsuario.pedirDesconto(jurosMensalidade);
                    ((Casa) financiamentoUsuario).aplicarDesconto(desconto);
                    descontoValido = true;
                } catch (DescontoMaiorDoQueJurosException e) {
                    System.out.println("Erro ao aplicar desconto: O valor do desconto não pode ser maior do que os juros da mensalidade, que foi de R$ " + String.format("%.2f", jurosMensalidade));
                }
            }
        }

        double totalValorImoveis = 0;
        double totalValorFinanciamentos = 0;
        double totalJuros = 0;

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        // Imprimir dados dos financiamentos
        for (Financiamento f : financiamentos) {
            f.mostrarDadosFinanciamento();
            totalValorImoveis += f.getValorImovel();
            totalValorFinanciamentos += f.calcularTotalPagamento();
            totalJuros += f.getValorImovel() * taxaJuros;
        }

        System.out.println("\n========================================");
        System.out.println("Total de todos os imóveis: " + currencyFormatter.format(totalValorImoveis));
        System.out.println("Total de todos os financiamentos: " + currencyFormatter.format(totalValorFinanciamentos));
        System.out.println("========================================");
    }
}