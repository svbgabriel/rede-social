package br.anhembi.grafos.redesocial.core;

/**
 *
 * @author Eduardo Zambom
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class Grafo {

    private int[][] matrizAdjacencia = new int[50][50];

    public Grafo() {

    }

    public int numeroVertices() {
        return matrizAdjacencia.length;
    }

    public int numeroArestas() {
        int num = 0;
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = i; j < matrizAdjacencia[i].length; j++) {
                if (matrizAdjacencia[i][j] != 0) {
                    num = num + matrizAdjacencia[i][j];
                }
            }
        }
        return num;
    }

    public String nomeArestas() {

        String arestas = "";
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = i; j < matrizAdjacencia[i].length; j++) {
                for (int k = 0; k < matrizAdjacencia[i][j]; k++) {
                    arestas = arestas + "[" + i + "," + j + "] ";
                }
            }
        }
        return arestas;
    }

    public int loops() {
        int numLoops = 0;
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            if (matrizAdjacencia[i][i] != 0) {
                numLoops++;
            }
        }
        return numLoops;
    }

    public int numArestasParalelas() {
        int numArestasParalelas = 0;
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = i; j < matrizAdjacencia[i].length; j++) {
                if (matrizAdjacencia[i][j] > 1) {
                    numArestasParalelas++;
                }
            }
        }
        return numArestasParalelas;
    }

    public boolean isConexo() {

        return true;
    }

    public int[] graus() {

        int[] graus = new int[matrizAdjacencia.length];
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            int somaGrau = 0;
            for (int j = i; j < matrizAdjacencia[i].length; j++) {
                if (matrizAdjacencia[i][j] > 0) {
                    somaGrau = somaGrau + matrizAdjacencia[i][j];
                }
                graus[i] = somaGrau;
            }
        }
        return graus;

    }

    public int[][] matrizIncidencia() {

        int[][] matriz = new int[numeroVertices()][numeroArestas()];
        int contArestas = 0;
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = i; j < matrizAdjacencia[i].length; j++) {
                if (matrizAdjacencia[i][j] > 0) {
                    for (int k = 0; k < matrizAdjacencia[i][j]; k++) {
                        matriz[i][contArestas] = 1;
                        matriz[j][contArestas] = 1;
                        contArestas++;
                    }
                }
            }
        }

        return matriz;

    }

}
