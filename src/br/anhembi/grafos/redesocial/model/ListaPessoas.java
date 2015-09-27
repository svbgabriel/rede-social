package br.anhembi.grafos.redesocial.model;

import java.util.ArrayList;

/**
 *
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class ListaPessoas extends ArrayList {

    private final ArrayList<Pessoa> lista;
    
    public ListaPessoas() {
        lista = new ArrayList<>(50);
    }
    
    
    public void inserir(Pessoa pessoa) {
        lista.add(pessoa);
    }
    
    public boolean remover(Pessoa pessoa) {
        if(lista.contains(pessoa)) {
            return lista.remove(pessoa);
        }
        return false;
    }
    
}
