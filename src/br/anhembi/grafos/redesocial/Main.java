package br.anhembi.grafos.redesocial;

import br.anhembi.grafos.redesocial.core.RedeSocial;
import br.anhembi.grafos.redesocial.model.*;
import java.util.List;

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
        
        Pessoa marcao = new Pessoa("Marcão", 24);
        redeSocial.insere(marcao);
        
        
        redeSocial.relacionar(sergio, henrique, 3);
        redeSocial.relacionar(sergio, gabriel, 3);
        redeSocial.relacionar(henrique, gabriel, 3);
        redeSocial.relacionar(henrique, marcao, 3);
        
        //redeSocial.remove(sergio);
        
        
        // Issue #3 -----------------------------
//        int indice = 2;
//        Pessoa pessoa = redeSocial.getPessoa(indice);
//        if(pessoa != null) {
//            System.out.println("Amigos de " + pessoa.getNome() + ":");
//            for(Pessoa p : redeSocial.listaAmigos(indice)) {
//                System.out.println(p.toString());
//            }
//        } else {
//            System.out.println("Pessoa não existe!");
//        }
        
        
        // Issue #4 -----------------------------
//        Pessoa joao = new Pessoa("João", 30);
//        int indiceJoao = redeSocial.insere(joao);
//
//        int numeroVertices = redeSocial.numeroVerticesEntreDuasPessoas(1, 0);
//        
//        System.out.println("Número de vértices: " + numeroVertices);
        
        
        
        // Issue #5 -----------------------------
        //System.out.println(redeSocial.getArvoreMinimaPrim(0));
        //System.out.println(redeSocial.getArvoreMinimaKruskal());
        
//        List<Pessoa[]> arvoreMinima = redeSocial.getArvoreMinima();
//        for(Pessoa[] arrayPessoas : arvoreMinima) {
//            System.out.println(arrayPessoas[0].getNome() + " --- " + arrayPessoas[1].getNome());
//        }
        
        
        // Issue #6 -----------------------------
        redeSocial.status(false);
        
    }

}
