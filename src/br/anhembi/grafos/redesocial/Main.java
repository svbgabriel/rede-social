package br.anhembi.grafos.redesocial;

import br.anhembi.grafos.redesocial.core.RedeSocial;
import br.anhembi.grafos.redesocial.model.*;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class Main {

    public static void main(String[] args) {

        int op, idade, op_id_ni, id, idade_p1, idade_p2, tempo, id_p1, id_p2, 
                numeroVertices;
        String nome, nome_p1, nome_p2;

        Scanner input = new Scanner(System.in);

        Pessoa p1, p2;

        RedeSocial redeSocial = new RedeSocial(50);

        do {
            System.out.println("----- Rede Social -----");
            System.out.println("----- Menu -----");
            System.out.println("1 - Adicionar pessoa\n"
                    + "2 - Remover pessoa\n"
                    + "3 - Conectar pessoas\n"
                    + "4 - Amigos de uma pessoa\n"
                    + "5 - Distância entre 2 pessoas\n"
                    + "6 - Mostrar árvore mínima\n"
                    + "7 - Status da Rede\n"
                    + "8 - Visualização da Rede\n"
                    + "0 - Sair");
            System.out.println("----- Menu -----");

            op = input.nextInt();
            input.nextLine();

            switch (op) {

                case 1:
                    System.out.println("----- Adicionar -----");
                    System.out.println("Informe o nome da pessoa");
                    nome = input.nextLine();
                    System.out.println("Informe a idade da pessoa");
                    idade = input.nextInt();
                    p1 = new Pessoa(nome, idade);
                    redeSocial.insere(p1);
                    System.out.println(nome + " de " + idade + " anos adicionado");
                    break;
                case 2:
                    System.out.println("----- Remover -----");
                    System.out.println("Deseja remover por: 1 -ID ou 2 - Nome e idade");
                    op_id_ni = input.nextInt();
                    switch (op_id_ni) {
                        case 1:
                            System.out.println("Informe o ID da pessoa");
                            id = input.nextInt();
                            if (redeSocial.remove(id)) {
                                System.out.println("Remoção concluída");
                            } else {
                                System.out.println("Erro na remoção");
                            }
                            break;
                        case 2:
                            System.out.println("Informe o nome da pessoa");
                            nome = input.nextLine();
                            System.out.println("Informe a idade da pessoa");
                            idade = input.nextInt();
                            p1 = new Pessoa(nome, idade);
                            if (redeSocial.remove(p1)) {
                                System.out.println(nome + " de " + idade + " anos removido");
                            } else {
                                System.out.println("Não foi possível remover, favor rever os dados informados");
                            }
                            break;
                        default:
                            System.out.println("Opção inválida,escolha novamente");
                            break;
                    }
                    break;
                case 3:
                    if (redeSocial.getQuantidade() < 2) {
                        System.out.println("É preciso pelo menos duas pessoas na Rede");
                        break;
                    }
                    System.out.println("----- Conectar -----");
                    System.out.println("Deseja conectar por: 1 -ID ou 2 - Nome e idade");
                    op_id_ni = input.nextInt();
                    switch (op_id_ni) {
                        case 1:
                            System.out.println("Informe o ID da primeira pessoa");
                            id_p1 = input.nextInt();
                            p1 = redeSocial.getPessoa(id_p1);
                            System.out.println("Informe o ID da segunda pessoa");
                            id_p2 = input.nextInt();
                            p2 = redeSocial.getPessoa(id_p2);
                            System.out.println("Quanto tempo se conhecem ?");
                            tempo = input.nextInt();
                            redeSocial.relacionar(p1, p2, tempo);
                            System.out.println(p1.getNome() + " e " + p2.getNome() + " forem conectados");
                            break;
                        case 2:
                            System.out.println("Informe o nome da primeira pessoa");
                            nome_p1 = input.nextLine();
                            System.out.println("Informe a idade da primeira pessoa");
                            idade_p1 = input.nextInt();
                            p1 = new Pessoa(nome_p1, idade_p1);
                            System.out.println("Informe o nome da segunda pessoa");
                            nome_p2 = input.nextLine();
                            System.out.println("Informe a idade da segunda pessoa");
                            idade_p2 = input.nextInt();
                            p2 = new Pessoa(nome_p2, idade_p2);
                            System.out.println("Quanto tempo se conhecem ?");
                            tempo = input.nextInt();
                            redeSocial.relacionar(p1, p2, tempo);
                            System.out.println(nome_p1 + " e " + nome_p2 + " forem conectados");
                            break;
                        default:
                            System.out.println("Opção inválida,escolha novamente");
                            break;
                    }
                    break;
                case 4:
                    if (redeSocial.getQuantidade() < 2) {
                        System.out.println("É preciso pelo menos duas pessoas na Rede");
                        break;
                    }
                    System.out.println("----- Amigos de uma pessoa -----");
                    System.out.println("Deseja procurar por: 1 -ID ou 2 - Nome e idade");
                    op_id_ni = input.nextInt();
                    switch (op_id_ni) {
                        case 1:
                            System.out.println("Informe o ID da pessoa");
                            id = input.nextInt();
                            Pessoa pessoa = redeSocial.getPessoa(id);
                            if (pessoa != null) {
                                System.out.println("Amigos de " + pessoa.getNome() + ":");
                                for (Pessoa p : redeSocial.listaAmigos(id)) {
                                    System.out.println(p.toString());
                                }
                            } else {
                                System.out.println("Pessoa não existe!");
                            }
                            break;
                        case 2:
                            System.out.println("Informe o nome da pessoa");
                            nome = input.nextLine();
                            System.out.println("Informe a idade da pessoa");
                            idade = input.nextInt();
                            p1 = new Pessoa(nome, idade);
                            id = redeSocial.getIndice(p1);
                            p1 = redeSocial.getPessoa(id);
                            if (p1 != null) {
                                System.out.println("Amigos de " + p1.getNome() + ":");
                                for (Pessoa p : redeSocial.listaAmigos(id)) {
                                    System.out.println(p.toString());
                                }
                            } else {
                                System.out.println("Pessoa não existe!");
                            }
                            break;
                        default:
                            System.out.println("Opção inválida,escolha novamente");
                            break;
                    }
                    break;
                case 5:
                    if (redeSocial.getQuantidade() < 2) {
                        System.out.println("É preciso pelo menos duas pessoas na Rede");
                        break;
                    }
                    System.out.println("----- Distância entre 2 pessoas -----");
                    System.out.println("Deseja procurar por: 1 -ID ou 2 - Nome e idade");
                    op_id_ni = input.nextInt();
                    switch (op_id_ni) {
                        case 1:
                            System.out.println("Informe o ID da primeira pessoa");
                            id_p1 = input.nextInt();
                            System.out.println("Informe o ID da segunda pessoa");
                            id_p2 = input.nextInt();
                            numeroVertices = redeSocial.numeroVerticesEntreDuasPessoas(id_p1, id_p2);
                            System.out.println("Número de vértices: " + numeroVertices);
                            break;
                        case 2:
                            System.out.println("Informe o nome da primeira pessoa");
                            nome_p1 = input.nextLine();
                            System.out.println("Informe a idade da primeira pessoa");
                            idade_p1 = input.nextInt();
                            p1 = new Pessoa(nome_p1, idade_p1);
                            System.out.println("Informe o nome da segunda pessoa");
                            nome_p2 = input.nextLine();
                            System.out.println("Informe a idade da segunda pessoa");
                            idade_p2 = input.nextInt();
                            p2 = new Pessoa(nome_p2, idade_p2);
                            numeroVertices = redeSocial.numeroVerticesEntreDuasPessoas(p1, p2);
                            System.out.println("Número de vértices: " + numeroVertices);
                            break;
                        default:
                            System.out.println("Opção inválida,escolha novamente");
                            break;
                    }
                    break;
                case 6:
                    System.out.println("-----  Árvore mínima -----");
                    System.out.println("Escolha 1 - Prim 2 - Kruskal");
                    op_id_ni = input.nextInt();
                    switch (op_id_ni) {
                        case 1:
                            System.out.println(redeSocial.getArvoreMinima(0));
                            break;
                        case 2:
                            List<Pessoa[]> arvoreMinima = redeSocial.getArvoreMinima();
                            for (Pessoa[] arrayPessoas : arvoreMinima) {
                                System.out.println(arrayPessoas[0].getNome() + " --- " + arrayPessoas[1].getNome());
                            }
                        default:
                            System.out.println("Opção inválida,escolha novamente");
                            break;
                    }
                    break;
                case 7:
                    System.out.println("-----  Status da Rede -----");
                    redeSocial.status(true);
                    break;
                case 8:
                    System.out.println("-----  Visualização da Rede -----");
                    new Applet().show(redeSocial);
                    break;
                case 0:
                    System.out.println("Encerrando");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida escolha novamente");
                    break;
            }

        } while (op != 0);
    }

}
