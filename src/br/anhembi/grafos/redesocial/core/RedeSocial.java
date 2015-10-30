package br.anhembi.grafos.redesocial.core;

import br.anhembi.grafos.redesocial.model.*;
import java.util.ArrayList;

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
    private GrafoSocial grafo;
    
    
   
    
    /**
     * Construtor
     * 
     * @param   tamanho Quantidade máxima de pessoas da rede.
     */
    public RedeSocial(int tamanho) {
        this.listaPessoas = new ListaPessoas(tamanho);
        this.grafo = new GrafoSocial(new int[tamanho][tamanho]);
    }
    
    
    /**
     * Insere uma pessoa no cadastro
     * 
     * @param   pessoa Uma {@link Pessoa}
     * @return  true, se foi inserida com sucesso, ou false, caso tenha falhado.
     */
    public int insere(Pessoa pessoa) {
        return this.listaPessoas.insere(pessoa);
    }
    
    
    /**
     * Remove uma pessoa do cadastro e seus relacionamentos
     * 
     * @param   pessoa {@link Pessoa} a ser removida.
     * @return  true, se foi removida com sucesso, ou false, caso tenha falhado.
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
     * Remove uma pessoa da rede social e seus relacionamentos
     * 
     * @param   indice Índice da pessoa.
     * @return  true, se foi removida com sucesso, ou false, caso tenha falhado.
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
     * Relaciona duas pessoas.
     * 
     * @param   pessoa1 Pessoa 1
     * @param   pessoa2 Pessoa 2
     * @param   anosRelacionamento Anos de relacionamento entre elas
     */
    public void relacionar(Pessoa pessoa1, Pessoa pessoa2, int anosRelacionamento) {
        int indicePessoa1 = listaPessoas.getIndice(pessoa1);
        int indicePessoa2 = listaPessoas.getIndice(pessoa2);
        
        this.grafo.adicionaAresta(indicePessoa1, indicePessoa2, anosRelacionamento);
    }

    /**
     * Retorna uma pessoa da rede.
     * 
     * @param   indice Índice da pessoa
     * @return  A {@link Pessoa} procurada ou null, se não encontrar.
     */
    public Pessoa getPessoa(int indice) {
        return this.listaPessoas.getPessoa(indice);
    }
    
    /**
     * Lista os amigos diretos de uma pessoa.
     * 
     * @param   indice Índice da pessoa.
     * @return  Um ArrayList de {@link Pessoa}s, amigas da pessoa.
     */
    public ArrayList<Pessoa> listaAmigos(int indice) {
        ArrayList<Pessoa> result = new ArrayList<Pessoa>();
        ArrayList<Integer> nosVizinhos = this.grafo.vizinhos(indice);
        for(int i : nosVizinhos) {
            result.add(getPessoa(i));
        }
        return result;
    }

    
    /**
     * Quantidade de vértices entre duas pessoas.
     * 
     * @param   indice1 Índice da pessoa 1
     * @param   indice2 Índice da pessoa 2
     * @return  a quantidade de vértices entre as duas pessoas; 
     *          0, se as duas pessaos estiverem conectadas diretamente; 
     *          -1 se não estiverem conectadas. 
     */
    public int numeroVerticesEntreDuasPessoas(int indice1, int indice2) {
        int[] antecessores = this.grafo.listaAntecessores(indice1);
        
        if(antecessores[indice2] == 0) {
            // A pessoa de indice2 não está conectada a ninguém. (Forever Alone)
            return -1;
        }
        
        int result = -1;
        int antecessor = indice2;
        while(antecessor != indice1) {
            antecessor = antecessores[antecessor];
            result++;
        }
        
        return result;
    }
    
    
    /**
     * Quantidade de vértices entre duas pessoas.
     * 
     * @param   pessoa1 Pessoa 1
     * @param   pessoa2 Pessoa 2
     * @return  a quantidade de vértices entre as duas pessoas; 
     *          0, se as duas pessaos estiverem conectadas diretamente; 
     *          -1 se não estiverem conectadas. 
     * @see     #numeroVerticesEntreDuasPessoas(int, int)
     */
    public int numeroVerticesEntreDuasPessoas(Pessoa pessoa1, Pessoa pessoa2) {
        int indicePessoa1 = this.listaPessoas.getIndice(pessoa1);
        int indicePessoa2 = this.listaPessoas.getIndice(pessoa2);
        
        return this.numeroVerticesEntreDuasPessoas(indicePessoa1, indicePessoa2);
    }
}
