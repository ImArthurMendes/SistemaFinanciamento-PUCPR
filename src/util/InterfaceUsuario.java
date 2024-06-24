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
        System.out.print("Qual o valor do Imóvel? ");
        return scanner.nextDouble();
    }

    public int pedirPrazoFinanciamento() {
        System.out.print("Qual o prazo do financiamento do Imóvel (em anos)? ");
        int prazoFinanciamento = scanner.nextInt();
        if (prazoFinanciamento > MAX_PRAZO_FINANCIAMENTO) {
            System.out.println("O prazo do financiamento não pode ser superior a " + MAX_PRAZO_FINANCIAMENTO + " anos.");
            System.exit(1);
        }
        return prazoFinanciamento;
    }

    public double pedirTaxaJuros() {
        System.out.print("Qual é a taxa de juros deste financiamento (sem %)? ");
        return scanner.nextDouble();
    }

    public int pedirTipoImovel() {
        System.out.println("Você gostaria de um orçamento para qual tipo de imóvel?");
        System.out.println("1 - Casa");
        System.out.println("2 - Apartamento");
        System.out.println("3 - Terreno");
        return scanner.nextInt();
    }

    public double pedirTamanhoAreaConstruida() {
        System.out.print("Qual o tamanho da área construída (em m²)? ");
        return scanner.nextDouble();
    }

    public double pedirTamanhoTerreno() {
        System.out.print("Qual o tamanho do terreno (em m²)? ");
        return scanner.nextDouble();
    }

    public int pedirNumeroVagasGaragem() {
        System.out.print("Quantas vagas na garagem? ");
        return scanner.nextInt();
    }

    public int pedirNumeroAndar() {
        System.out.print("Qual o número do andar? ");
        return scanner.nextInt();
    }

    public String pedirTipoZona() {
        System.out.print("Qual o tipo de zona (residencial/comercial)? ");
        return scanner.next();
    }
}