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
    
    /**
     * Construtor
     * 
     * @param   matrizAdjacencia Matriz de adjacência
     */
    public GrafoSocial(int[][] matrizAdjacencia) {
        super(matrizAdjacencia);
        this.matrizAdjacencia = matrizAdjacencia;
    }

    
    /**
     * Adiciona uma aresta ao grafo.
     * 
     * @param   i Vértice 1 da aresta
     * @param   j Vértice 2 da aresta
     * @param   peso Peso da aresta
     */
    public void adicionaAresta(int i, int j, int peso) {
        this.matrizAdjacencia[i][j] = peso;
        this.matrizAdjacencia[j][i] = peso;
    }
    
    
    /**
     * Remove uma aresta do grafo.
     * 
     * @param   i Vértice 1 da aresta
     * @param   j Vértice 2 da aresta
     */
    public void removeAresta(int i, int j) {
        this.matrizAdjacencia[i][j] = 0;
        this.matrizAdjacencia[j][i] = 0;
    }

    /**
     * Retorna os vizinhos de um nó.
     * 
     * @param   vertice Índice do nó
     * @return  Um ArrayList de índices dos nós vizinhos
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
    
    
    /**
     * Retorna uma lista com os nós antecessores de um vértice.
     * Utiliza o algoritmo de Dijkstra.
     * 
     * @param   verticeInicial Vértice inicial para análise.
     * @return  Um Array com os índices dos vértices antecessores.
     * @see     grafo.Grafo#djikistra(int)
     */
    public int[] listaAntecessores(int verticeInicial) {

        // 1 - tirei a fila, agora verifica no vetor de distâncias.
        // 2 - Adicionei o vetor antecessor
        
        int distancia[] = new int[numeroVertices()];
        int antecessor[] = new int[numeroVertices()];
        boolean verificado[] = new boolean[numeroVertices()];
        
        for (int i = 0; i < distancia.length; i++) {
            distancia[i] = Integer.MAX_VALUE;
        }
        
        distancia[verticeInicial] = 0;
        

        for (int h = 0; h < distancia.length; h++) {

            int menor = Integer.MAX_VALUE;
            int menorI = 0;
            for (int i = 0; i < distancia.length; i++) {
                if (distancia[i] < menor && !verificado[i]) {
                    menor = distancia[i];
                    menorI = i;
                }                
            }
            
            int verticeAnalisado = menorI;
            verificado[verticeAnalisado] = true;
            for (int i = 0; i < matrizAdjacencia[verticeAnalisado].length; i++) {
                if (matrizAdjacencia[verticeAnalisado][i] != 0 
                        && !verificado[i] 
                        && distancia[i] > distancia[verticeAnalisado] + matrizAdjacencia[verticeAnalisado][i]) {
                    distancia[i] = distancia[verticeAnalisado] + matrizAdjacencia[verticeAnalisado][i];
                    antecessor[i] = verticeAnalisado;
                }
            }            
        }
        
        // Descomentar para listar no console a matriz de antecessores
//        for (int i = 0; i < antecessor.length; i++) {
//            System.out.println("I: " + i + " A: " + antecessor[i]);
//        }
        
        return antecessor;
    }
}
