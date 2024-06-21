/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package util;
import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner = new Scanner(System.in);
    private static final int MAX_PRAZO_FINANCIAMENTO = 35;
    private static final double MAX_TAXA_JUROS = 12.0;

    public double pedirValorImovel() {
        double valorImovel;
        do {
            System.out.print("Qual o valor do Imóvel? ");
            valorImovel = scanner.nextDouble();
            if (valorImovel <= 0) {
                System.out.println("Erro: O valor do imóvel deve ser positivo. Por favor, insira novamente.\n");
            }
        } while (valorImovel <= 0);
        return valorImovel;
    }

    public int pedirPrazoFinanciamento() {
        int prazoFinanciamento;
        do {
            System.out.print("Qual o prazo do financiamento do Imóvel (em anos)? ");
            prazoFinanciamento = scanner.nextInt();
            if (prazoFinanciamento <= 0) {
                System.out.println("Erro: O prazo de financiamento deve ser positivo. Por favor, insira novamente.\n");
            } else if (prazoFinanciamento > MAX_PRAZO_FINANCIAMENTO) {
                System.out.println("Erro: O prazo de financiamento não pode ser superior a " + MAX_PRAZO_FINANCIAMENTO + " anos. Por favor, insira novamente.\n");
            }
        } while (prazoFinanciamento <= 0 || prazoFinanciamento > MAX_PRAZO_FINANCIAMENTO);
        return prazoFinanciamento;
    }

    public double pedirTaxaJuros() {
        double taxaJuros;
        do {
            System.out.print("Qual é a taxa de juros deste financiamento (sem %)? ");
            taxaJuros = scanner.nextDouble();
            if (taxaJuros <= 0) {
                System.out.println("Erro: A taxa de juros deve ser positiva. Por favor, insira novamente.\n");
            } else if (taxaJuros > MAX_TAXA_JUROS) {
                System.out.println("Erro: A taxa de juros não pode ser superior a " + MAX_TAXA_JUROS + "% ao ano (lei nº1.521/51). Por favor, insira novamente.\n");
            }
        } while (taxaJuros <= 0 || taxaJuros > MAX_TAXA_JUROS);
        return taxaJuros;
    }
}