package br.anhembi.grafos.redesocial.core;

import br.anhembi.grafos.redesocial.model.*;

/**
 * Classe para gerenciar a adição e remoção de relacionamentos
 * na rede social.
 * 
 * Esta classe manipula a classe Grafo, que deve ser mantida o
 * mais próximo possível da original disponibilizada pelo
 * professor.
 * 
 * A ideia é utilizar apenas esta classe na classe principal 
 * do programa (Main).
 * 
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class RedeSocial {

    private ListaPessoas listaPessoas;
    private Grafo grafo;
    
    
    public RedeSocial(int tamanho) {
        this.listaPessoas = new ListaPessoas(tamanho);
        this.grafo = new Grafo(tamanho);
    }
    
    
    public boolean insere(Pessoa pessoa) {
        return this.listaPessoas.insere(pessoa);
    }
    
    
    public boolean remove(Pessoa pessoa) {
        return this.listaPessoas.remove(pessoa);
    }
    
    
    public boolean remove(int indice) {
        return this.listaPessoas.remove(indice);
    }
    
    
    public void relacionar(Pessoa pessoa1, Pessoa pessoa2, int anosRelacionamento) {
        int indicePessoa1 = listaPessoas.getIndice(pessoa1);
        int indicePessoa2 = listaPessoas.getIndice(pessoa2);
        
        this.grafo.adicionaAresta(indicePessoa1, indicePessoa2, anosRelacionamento);
    }
    
}
