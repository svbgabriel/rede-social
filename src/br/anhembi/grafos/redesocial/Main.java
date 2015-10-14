package br.anhembi.grafos.redesocial;

import br.anhembi.grafos.redesocial.core.RedeSocial;
import br.anhembi.grafos.redesocial.model.*;

/**
 *
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class Main {

    public static void main(String[] args) {
        
        RedeSocial redeSocial = new RedeSocial(50);
        
        
        Pessoa sergio = new Pessoa("Sergio", 47);
        Pessoa paulao = new Pessoa("Paulão", 25);
        Pessoa henrique = new Pessoa("Henrique", 22);
        Pessoa gabriel = new Pessoa("Gabriel", 21);
        
        redeSocial.insere(sergio);
        redeSocial.insere(paulao);
        redeSocial.insere(henrique);
        redeSocial.insere(gabriel);
        
        redeSocial.remove(1);
        
        redeSocial.insere(new Pessoa("Marcão", 24));
        
        
        redeSocial.relacionar(sergio, henrique, 3);
        redeSocial.relacionar(sergio, gabriel, 3);
        redeSocial.relacionar(henrique, gabriel, 3);
        
        redeSocial.remove(sergio);
        
    }

}
