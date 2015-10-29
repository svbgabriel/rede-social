package br.anhembi.grafos.redesocial.model;

import java.util.ArrayList;

/**
 *
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class ListaPessoas {

    private final Pessoa[] lista;
    
    public ListaPessoas(int tamanho) {
        lista = new Pessoa[tamanho];
    }
    
    
    public Pessoa getPessoa(int indice) {
        if(indice < 0 || indice > lista.length - 1) {
            return null;
        }
        return this.lista[indice];
    }
    
    
    public boolean insere(Pessoa pessoa) {
        // Procura uma posição no vetor que seja null.
        // Se achar, coloca a pessoa nesta posição.
        for(int i = 0; i < lista.length; i++) {
            if(lista[i] == null) {
                lista[i] = pessoa;
                return true;
            }
        }
        return false;
    }
    
    public boolean remove(Pessoa pessoa) {
        for(int i = 0; i < lista.length; i++) {
            if(lista[i].equals(pessoa)) {
                lista[i] = null;
                return true;
            }
        }
        return false;
    }
    
    
    public boolean remove(int indice) {
        if(indice >= 0 && indice < lista.length) {
            lista[indice] = null;
            return true;
        }
        return false;
    }
    
    
    public int getIndice(Pessoa pessoa) {
        for(int i = 0; i < lista.length; i++) {
            if(lista[i].equals(pessoa)) {
                return i; // achou a pessoa na lista
            }
        }
        return -1; // não achou a pessoa na lista
    }
}
