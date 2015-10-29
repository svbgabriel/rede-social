package br.anhembi.grafos.redesocial.core;

import grafo.Grafo;
import java.util.ArrayList;


/**
 * Esta classe extende a classe {@link Grafo}, com métodos específicos
 * para a manipulação do grafo.
 * 
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class GrafoSocial extends Grafo {

    private int[][] matrizAdjacencia;
    
    public GrafoSocial(int[][] matrizAdjacencia) {
        super(matrizAdjacencia);
        this.matrizAdjacencia = matrizAdjacencia;
    }

    
    public void adicionaAresta(int i, int j, int peso) {
        this.matrizAdjacencia[i][j] = peso;
        this.matrizAdjacencia[j][i] = peso;
    }
    
    public void removeAresta(int i, int j) {
        this.matrizAdjacencia[i][j] = 0;
        this.matrizAdjacencia[j][i] = 0;
    }

    /**
     * Retorna os vizinhos de um nó.
     * @param vertice Índice do nó
     * @return Um ArrayList de índices dos nós vizinhos
     */
    public ArrayList<Integer> vizinhos(int vertice) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int j = 0; j < matrizAdjacencia.length; j++) {
            if(matrizAdjacencia[vertice][j] > 0) {
                result.add(j);
            }
        }
        return result;
    }
    
}
