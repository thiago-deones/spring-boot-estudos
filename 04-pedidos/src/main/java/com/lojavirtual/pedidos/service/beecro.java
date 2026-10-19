package com.lojavirtual.pedidos.service;

import java.util.Scanner;

public class beecro {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            int linhas = scanner.nextInt();
            int colunas = scanner.nextInt();

            if (linhas == 0 && colunas == 0) {
                break;
            }

            int[][] matriz = new int[linhas][colunas];

            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    matriz[i][j] = scanner.nextInt();
                }
            }

            int maiorComprimento = 1;

            // Escolhe a coluna inicial
            for (int colunaInicial = 0; colunaInicial < colunas; colunaInicial++) {

                // Escolhe a coluna final
                for (int colunaFinal = colunaInicial; colunaFinal < colunas; colunaFinal++) {

                    int largura = colunaFinal - colunaInicial + 1;
                    int comprimentoAtual = 0;

                    // Percorre as linhas
                    for (int linha = 0; linha < linhas; linha++) {

                        boolean crescente = true;

                        // Verifica se a linha escolhida é crescente
                        for (int coluna = colunaInicial + 1;
                             coluna <= colunaFinal;
                             coluna++) {

                            if (matriz[linha][coluna] <= matriz[linha][coluna - 1]) {
                                crescente = false;
                                break;
                            }
                        }

                        // Se a própria linha não é crescente,
                        // não podemos continuar o retângulo.
                        if (!crescente) {
                            comprimentoAtual = 0;
                            continue;
                        }

                        // Verifica a passagem da linha anterior
                        if (linha > 0 && comprimentoAtual > 0) {

                            if (matriz[linha][colunaInicial]
                                    <= matriz[linha - 1][colunaFinal]) {

                                comprimentoAtual = 0;
                                continue;
                            }
                        }

                        comprimentoAtual += largura;

                        maiorComprimento =
                                Math.max(maiorComprimento, comprimentoAtual);
                    }
                }
            }

            System.out.println(maiorComprimento);
        }

        scanner.close();
    }
}