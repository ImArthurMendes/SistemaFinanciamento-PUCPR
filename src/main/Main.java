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

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        ArrayList<Financiamento> financiamentos = new ArrayList<>();

        try {
            //NESTA PARTE DO CÓDIGO, EU PEÇO PARA O USUÁRIO AS INFORMAÇÕES DO FINANCIAMENTO PARA OS USUÁRIOS
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

            //NESTA PARTE DO CÓDIGO, EU APLICO UM DESCONTO NO VALOR DA MENSALIDADE CASO O IMÓVEL FOR UMA CASA, SE NÃO FOR, ESTA PARTE DO CÓDIGO É IGNORADO
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

            //IMPRIMO OS DADOS DO FINANCIAMENTO PARA O USUÁRIO
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

            //NESTA PARTE DO CÓDIGO, AS INFORMAÇÕES SÃO SALVAS E LIDAS NOS ARQUIVOS TXT
            String filePath = "financiamentos.txt";
            salvarDadosEmArquivoTexto(financiamentos, filePath);
            ArrayList<Financiamento> financiamentosLidos = lerDadosDeArquivoTexto(filePath);

            System.out.println("\n========================================");
            System.out.println("Dados lidos do arquivo de texto:");
            for (Financiamento f : financiamentosLidos) {
                f.mostrarDadosFinanciamento();
            }
            System.out.println("========================================");

            //NESTA PARTE DO CÓDIGO, AS INFORMAÇÕES SÃO SALVAS E LIDAS NOS ARQUIVOS SERIALIZADOS
            String fileSerializedPath = "financiamentos_serializados.dat";
            salvarDadosSerializados(financiamentos, fileSerializedPath);
            ArrayList<Financiamento> financiamentosSerializadosLidos = lerDadosSerializados(fileSerializedPath);

            System.out.println("\n========================================");
            System.out.println("Dados lidos do arquivo serializado:");
            for (Financiamento f : financiamentosSerializadosLidos) {
                f.mostrarDadosFinanciamento();
            }
            System.out.println("========================================");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void salvarDadosEmArquivoTexto(ArrayList<Financiamento> financiamentos, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Financiamento f : financiamentos) {
                writer.write(f.toString());
                writer.newLine();
            }
            System.out.println("Dados salvos em arquivo de texto com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Financiamento> lerDadosDeArquivoTexto(String filePath) {
        ArrayList<Financiamento> financiamentos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Financiamento financiamento = Financiamento.fromString(line);
                financiamentos.add(financiamento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return financiamentos;
    }

    private static void salvarDadosSerializados(ArrayList<Financiamento> financiamentos, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(financiamentos);
            System.out.println("Dados serializados salvos com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Financiamento> lerDadosSerializados(String filePath) {
        ArrayList<Financiamento> financiamentos = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            financiamentos = (ArrayList<Financiamento>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return financiamentos;
    }
}