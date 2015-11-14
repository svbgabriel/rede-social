package br.anhembi.grafos.redesocial;

import br.anhembi.grafos.redesocial.core.RedeSocial;
import br.anhembi.grafos.redesocial.model.*;
import java.util.List;

/**
 *
 * @author  Gabriel Batista
 * @author  Henrique Albanese
 * @author  Sérgio Umlauf
 */
public class Main {
    
    private static UserInterface ui;
    private static RedeSocial redeSocial;

    public static void main(String[] args) {

        redeSocial = new RedeSocial(50);
        ui = new UserInterface();
        int opcao;
        
        // Teste
//        redeSocial.insere(new Pessoa("Sérgio", 47));
//        redeSocial.insere(new Pessoa("Gabriel", 21));
//        redeSocial.insere(new Pessoa("Henrique", 22));
//        redeSocial.relacionar(0, 1, 3);
//        redeSocial.relacionar(1, 2, 3);
        // ------

        do {
            ui.mostraMenuPrinciapal();
            opcao = ui.pegaOpcaoInt();

            switch (opcao) {
                case 1: adicionarPessoa(); break;
                case 2: removerPessoa(); break;
                case 3: conectarPessoas(); break;
                case 4: mostrarAmigosDaPessoa(); break;
                case 5: mostrarDistanciaEntreDuasPessoas(); break;
                case 6: mostraArvoreMinima(); break;
                case 7: mostraStatusRede(); break;
                case 8: procurarPessoa(); break;
                case 9: visualizarRede(); break;
                case 0:
                    ui.mostraMensagem("Bye.");
                    System.exit(0);
                    break;
                default:
                    ui.mostraMensagem("Opção inválida. Escolha novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    
    

    // Private Methods ---------------------------------------------------------
    
    private static void adicionarPessoa() {
        ui.mostraMensagem("============== Adicionar pessoa ==============");
        Pessoa p = ui.pegaPessoaPorNomeIdade("pessoa");
        redeSocial.insere(p);
        ui.mostraMensagem(p.getNome() + " de " + p.getIdade() + " anos adicionado");
        ui.mostraMensagem("==============================================");
    }

    
    private static void removerPessoa() {
        ui.mostraMensagem("============== Remover pessoa ==============");
        ui.mostraMensagem("Deseja remover por: [1] ID ou [2] Nome e idade?");
        int opcao = ui.pegaOpcaoInt();
        switch (opcao) {
            case 1:
                int id = ui.pegaIDPessoa();
                if (redeSocial.remove(id)) {
                    ui.mostraMensagem("Remoção concluída");
                } else {
                    ui.mostraMensagem("Erro na remoção");
                }
                break;
            case 2:
                Pessoa p = ui.pegaPessoaPorNomeIdade("pessoa");
                if (redeSocial.remove(p)) {
                   ui.mostraMensagem(p + " removido(a).");
                } else {
                    ui.mostraMensagem("Não foi possível remover, favor rever os dados informados");
                }
                break;
            default:
                ui.mostraMensagem("Opção inválida. Escolha novamente.");
                break;
        }
        ui.mostraMensagem("============================================");
    }

    
    private static void conectarPessoas() {
        if (redeSocial.getQuantidade() < 2) {
            ui.mostraMensagem("É preciso pelo menos duas pessoas na Rede");
            return;
        }
        
        ui.mostraMensagem("============= Conectar pessoas =============");
        ui.mostraMensagem("Deseja conectar por: [1] ID ou [2] Nome e idade?");
        int opcao = ui.pegaOpcaoInt();
        
        Relacionamento rel;
        boolean ok;
        
        switch (opcao) {
            case 1:
                rel = ui.pegaDadosRelacionarPorID();
                ok = redeSocial.relacionar(rel.getIdPessoa1(), rel.getIdPessoa2(), rel.getAnosRelacionamento());
                if (ok) {
                    ui.mostraMensagem("Pessoas foram conectadas.");
                } else {
                    ui.mostraMensagem("Não foi possível relacionar as pessoas informadas.");
                }
                break;
            case 2:
                rel = ui.pegaDadosRelacionarPorNomeIdade();
                ok = redeSocial.relacionar(rel.getPessoa1(), rel.getPessoa2(), rel.getAnosRelacionamento());
                if (ok) {
                    ui.mostraMensagem(rel.getPessoa1() + " e " + rel.getPessoa2() + " forem conectados");
                } else {
                    ui.mostraMensagem("Não foi possível relacionar as pessoas informadas.");
                }
                break;
            default:
                ui.mostraMensagem("Opção inválida. Escolha novamente.");
                break;
        }
        ui.mostraMensagem("============================================");
    }

    
    private static void mostrarAmigosDaPessoa() {
        if (redeSocial.getQuantidade() < 2) {
            ui.mostraMensagem("É preciso pelo menos duas pessoas na Rede");
            return;
        }
        
        ui.mostraMensagem("=========== Amigos de uma pessoa ===========");
        ui.mostraMensagem("Deseja procurar por: [1] ID ou [2] Nome e idade?");
        int opcao = ui.pegaOpcaoInt();
        
        int id;
        Pessoa p;
        
        switch (opcao) {
            case 1:
                id = ui.pegaIDPessoa();
                Pessoa pessoa = redeSocial.getPessoa(id);
                if (pessoa != null) {
                    ui.mostraMensagem("Amigos de " + pessoa.getNome() + ":");
                    for (Pessoa pe : redeSocial.listaAmigos(id)) {
                        ui.mostraMensagem(pe.toString());
                    }
                } else {
                    ui.mostraMensagem("Pessoa não existe!");
                }
                break;
            case 2:
                p = ui.pegaPessoaPorNomeIdade("pessoa");
                id = redeSocial.getIndice(p);
                if (id >= 0) {
                    ui.mostraMensagem("Amigos de " + p.getNome() + ":");
                    for (Pessoa pe : redeSocial.listaAmigos(id)) {
                        ui.mostraMensagem(pe.toString());
                    }
                } else {
                    ui.mostraMensagem("Pessoa não existe!");
                }
                break;
            default:
                ui.mostraMensagem("Opção inválida. Escolha novamente.");
                break;
        }
        ui.mostraMensagem("============================================");
    }

    
    private static void mostrarDistanciaEntreDuasPessoas() {
        if (redeSocial.getQuantidade() < 2) {
            ui.mostraMensagem("É preciso pelo menos duas pessoas na Rede");
            return;
        }
        
        ui.mostraMensagem("======== Distância entre 2 pessoas ========");
        ui.mostraMensagem("Deseja procurar por: [1] ID ou [2] Nome e idade?");
        int opcao = ui.pegaOpcaoInt();
        
        int numeroVertices;
        
        switch (opcao) {
            case 1:
                int[] ids = ui.pegaIDDuasPessoas();
                numeroVertices = redeSocial.numeroVerticesEntreDuasPessoas(ids[0], ids[1]);
                ui.mostraMensagem("Número de vértices: " + numeroVertices);
                break;
            case 2:
                Pessoa p1 = ui.pegaPessoaPorNomeIdade("primeira pessoa");
                Pessoa p2 = ui.pegaPessoaPorNomeIdade("segunda pessoa");
                numeroVertices = redeSocial.numeroVerticesEntreDuasPessoas(p1, p2);
                ui.mostraMensagem("Número de vértices: " + numeroVertices);
                break;
            default:
                ui.mostraMensagem("Opção inválida. Escolha novamente.");
                break;
        }
        ui.mostraMensagem("============================================");
    }

    
    private static void mostraArvoreMinima() {
        ui.mostraMensagem("==============  Árvore mínima ==============");
        ui.mostraMensagem("Escolha [1] Prim ou [2] Kruskal");
        int opcao = ui.pegaOpcaoInt();
        
        List<Pessoa[]> arvoreMinima;
        
        switch (opcao) {
            case 1:
                arvoreMinima = redeSocial.getArvoreMinima(0);
                for (Pessoa[] arrayPessoas : arvoreMinima) {
                    System.out.println(arrayPessoas[0].getNome() + " --- " + arrayPessoas[1].getNome());
                }
                break;
            case 2:
                arvoreMinima = redeSocial.getArvoreMinima(-1);
                for (Pessoa[] arrayPessoas : arvoreMinima) {
                    System.out.println(arrayPessoas[0].getNome() + " --- " + arrayPessoas[1].getNome());
                }
                break;
            default:
                ui.mostraMensagem("Opção inválida. Escolha novamente.");
                break;
        }
        ui.mostraMensagem("============================================");
    }

    
    private static void mostraStatusRede() {
        ui.mostraMensagem("==============  Status da Rede =============");
        redeSocial.status(true);
        ui.mostraMensagem("============================================");
    }

    
    private static void visualizarRede() {
        ui.mostraMensagem("===========  Visualização da Rede ==========");
        new Applet().show(redeSocial);
        ui.mostraMensagem("============================================");
    }

    
    private static void procurarPessoa() {
        ui.mostraMensagem("=============  Procurar pessoa =============");
        String nome = ui.pegaNomePessoa();
        List<Pessoa> pessoas = redeSocial.procurarPessoa(nome);
        if(pessoas != null && pessoas.size() > 0) {
            for(Pessoa p : pessoas) {
                ui.mostraMensagem(p.toString());
            }
        } else {
            ui.mostraMensagem("Pessoa não encontrada.");
        }
        ui.mostraMensagem("============================================");
    }
    
}
