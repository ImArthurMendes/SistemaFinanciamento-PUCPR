/*
 * ALUNO: ARTHUR MENDES LUCAS
 * CURSO: ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * PERÍODO: SEGUNDO TRIMESTRE
 * FACULDADE: PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 */

package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner = new Scanner(System.in);
    private static final int MAX_PRAZO_FINANCIAMENTO = 35;
    private static final double MAX_TAXA_JUROS = 12.0;

    public double pedirValorImovel() {
        double valorImovel = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Qual o valor do Imóvel? ");
                valorImovel = scanner.nextDouble();
                if (valorImovel <= 0) {
                    throw new IllegalArgumentException("O valor do imóvel deve ser positivo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Valor do imóvel deve ser um número válido.");
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return valorImovel;
    }

    public int pedirPrazoFinanciamento() {
        int prazoFinanciamento = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Qual o prazo do financiamento do Imóvel (em anos)? ");
                prazoFinanciamento = scanner.nextInt();
                if (prazoFinanciamento <= 0 || prazoFinanciamento > MAX_PRAZO_FINANCIAMENTO) {
                    throw new IllegalArgumentException("Prazo de financiamento inválido.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Prazo de financiamento deve ser um número inteiro.");
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return prazoFinanciamento;
    }

    public double pedirTaxaJuros() {
        double taxaJuros = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Qual é a taxa de juros deste financiamento (sem %)? ");
                taxaJuros = scanner.nextDouble();
                if (taxaJuros <= 0 || taxaJuros > MAX_TAXA_JUROS) {
                    throw new IllegalArgumentException("Taxa de juros inválida.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Taxa de juros deve ser um número válido.");
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return taxaJuros;
    }

    public int pedirTipoImovel() {
        int tipoImovel = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.println("Você gostaria de um orçamento para qual tipo de imóvel?");
                System.out.println("1 - Casa");
                System.out.println("2 - Apartamento");
                System.out.println("3 - Terreno");
                tipoImovel = scanner.nextInt();
                if (tipoImovel < 1 || tipoImovel > 3) {
                    throw new IllegalArgumentException("Opção inválida. Escolha entre 1, 2 ou 3.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Opção deve ser um número inteiro.");
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return tipoImovel;
    }

    public double pedirTamanhoAreaConstruida() {
        double tamanhoArea = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Qual o tamanho da área construída (em m²)? ");
                tamanhoArea = scanner.nextDouble();
                if (tamanhoArea <= 0) {
                    throw new IllegalArgumentException("Tamanho da área construída deve ser positivo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Tamanho da área construída deve ser um número válido.");
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return tamanhoArea;
    }

    public double pedirTamanhoTerreno() {
        double tamanhoTerreno = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Qual o tamanho do terreno (em m²)? ");
                tamanhoTerreno = scanner.nextDouble();
                if (tamanhoTerreno <= 0) {
                    throw new IllegalArgumentException("Tamanho do terreno deve ser positivo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Tamanho do terreno deve ser um número válido.");
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return tamanhoTerreno;
    }

    public int pedirNumeroVagasGaragem() {
        int numeroVagas = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Quantas vagas de garagem? ");
                numeroVagas = scanner.nextInt();
                if (numeroVagas < 0) {
                    throw new IllegalArgumentException("Número de vagas de garagem deve ser não negativo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Número de vagas deve ser um número inteiro.");
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return numeroVagas;
    }

    public int pedirNumeroAndar() {
        int numeroAndar = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Em qual andar o apartamento está localizado? ");
                numeroAndar = scanner.nextInt();
                if (numeroAndar < 0) {
                    throw new IllegalArgumentException("Número do andar deve ser não negativo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Número do andar deve ser um número inteiro.");
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return numeroAndar;
    }

    public String pedirTipoZona() {
        String tipoZona = "";
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Qual o tipo da zona (Residencial, Comercial, Industrial)? ");
                tipoZona = scanner.next();
                if (!tipoZona.equalsIgnoreCase("Residencial") &&
                        !tipoZona.equalsIgnoreCase("Comercial") &&
                        !tipoZona.equalsIgnoreCase("Industrial")) {
                    throw new IllegalArgumentException("Tipo de zona inválido.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Tipo de zona deve ser um texto válido.");
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return tipoZona;
    }

    public double pedirDesconto(double jurosMensalidade) {
        double desconto = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Qual o desconto que deseja aplicar (em R$)? ");
                desconto = scanner.nextDouble();
                if (desconto < 0) {
                    throw new IllegalArgumentException("Desconto não pode ser negativo.");
                }
                if (desconto > jurosMensalidade) {
                    throw new IllegalArgumentException("Erro ao aplicar desconto: O valor do desconto não pode ser maior do que os juros da mensalidade.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Desconto deve ser um número válido.");
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return desconto;
    }
}