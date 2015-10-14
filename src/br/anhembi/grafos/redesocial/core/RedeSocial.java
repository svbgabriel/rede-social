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
    
    
    /**
     * Construtor
     * 
     * @param tamanho 
     */
    public RedeSocial(int tamanho) {
        this.listaPessoas = new ListaPessoas(tamanho);
        this.grafo = new Grafo(tamanho);
    }
    
    
    /**
     * Insere uma pessoa no cadastro
     * 
     * @param pessoa
     * @return 
     */
    public boolean insere(Pessoa pessoa) {
        return this.listaPessoas.insere(pessoa);
    }
    
    
    /**
     * Remove uma pessoa do cadastro e seus relacionamentos
     * 
     * @param pessoa
     * @return 
     */
    public boolean remove(Pessoa pessoa) {
        int indicePessoa = listaPessoas.getIndice(pessoa);
        
        int[] relacionamentos = this.grafo.buscaLargura(indicePessoa);
        
        // Remove a pessoa e seus relacionamentos do Grafo
        for(int i = 0; i < relacionamentos.length; i++) {
            if(relacionamentos[i] != 0) {
                this.grafo.removeAresta(indicePessoa, i);
            }
        }
        
        // Remove o "cadastro" da pessoa
        return this.listaPessoas.remove(pessoa);
    }
    
    
    /**
     * Remove uma pessoa do cadastro e seus relacionamentos
     * 
     * @param indice
     * @return 
     */
    public boolean remove(int indice) {
        int[] relacionamentos = this.grafo.buscaLargura(indice);
        
        // Remove a pessoa e seus relacionamentos do Grafo
        for(int i = 0; i < relacionamentos.length; i++) {
            if(relacionamentos[i] != 0) {
                this.grafo.removeAresta(indice, i);
            }
        }
        
        // Remove o "cadastro" da pessoa       
        return this.listaPessoas.remove(indice);
    }
    
    
    /**
     * Relaciona duas pessoas
     * 
     * @param pessoa1
     * @param pessoa2
     * @param anosRelacionamento 
     */
    public void relacionar(Pessoa pessoa1, Pessoa pessoa2, int anosRelacionamento) {
        int indicePessoa1 = listaPessoas.getIndice(pessoa1);
        int indicePessoa2 = listaPessoas.getIndice(pessoa2);
        
        this.grafo.adicionaAresta(indicePessoa1, indicePessoa2, anosRelacionamento);
    }

    
}
