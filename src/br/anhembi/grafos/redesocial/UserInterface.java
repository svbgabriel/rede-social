package br.anhembi.grafos.redesocial;

import br.anhembi.grafos.redesocial.model.Pessoa;
import br.anhembi.grafos.redesocial.model.Relacionamento;
import java.util.Scanner;

/**
 * Classe para manipulação da entrada de dados pelo usuário.
 * 
 * @author  Gabriel Batista
 * @author  Henrique Albanese
 * @author  Sérgio Umlauf
 */
public class UserInterface {
    
    private final Scanner scanner;
    
    
    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }
    
    public void mostraMensagem(String mensagem) {
        System.out.println(mensagem);
    }
    
    public void mostraMenuPrinciapal() {
        mostraMensagem("\n--------------------------------");
        mostraMensagem("          Rede Social       ");
        mostraMensagem("--------------------------------");
        mostraMensagem("              Menu         ");
        mostraMensagem("--------------------------------");
        mostraMensagem("1 - Adicionar pessoa\n"
                + "2 - Remover pessoa\n"
                + "3 - Conectar pessoas\n"
                + "4 - Amigos de uma pessoa\n"
                + "5 - Distância entre 2 pessoas\n"
                + "6 - Mostrar árvore mínima\n"
                + "7 - Status da Rede\n"
                + "8 - Procurar pessoa\n"
                + "9 - Visualização da Rede\n"
                + "0 - Sair");
        mostraMensagem("--------------------------------\n");
    }
    
    public int pegaOpcaoInt() {
        int res;
        try {
            res = scanner.nextInt();
        } catch(Exception e) {
            res = -1;
        }
        scanner.nextLine();
        return res;
    }
    
    public String pegaNomePessoa() {
        mostraMensagem("Informe o nome da pessoa");
        return scanner.nextLine();
    }
    
    public int pegaIdadePessoa() {
        mostraMensagem("Informe a idade da pessoa");
        int res = scanner.nextInt();
        scanner.nextLine();
        return res;
    }
    
    public Pessoa pegaPessoaPorNomeIdade(String nomePessoa) {
        mostraMensagem("Informe o nome da " + nomePessoa);
        String nome = scanner.nextLine();
        mostraMensagem("Informe a idade da " + nomePessoa);
        int idade = scanner.nextInt();
        scanner.nextLine();
        Pessoa p = new Pessoa(nome, idade);
        return p;
    }
    
    public int pegaIDPessoa() {
        mostraMensagem("Informe o ID da pessoa");
        int res = scanner.nextInt();
        scanner.nextLine();
        return res;
    }
    
    public Relacionamento pegaDadosRelacionarPorID() {
        Relacionamento res = new Relacionamento();
        mostraMensagem("Informe o ID da primeira pessoa");
        res.setIdPessoa1(scanner.nextInt());
        mostraMensagem("Informe o ID da segunda pessoa");
        res.setIdPessoa2(scanner.nextInt());
        mostraMensagem("Há quantos anos se conhecem?");
        res.setAnosRelacionamento(scanner.nextInt());
        return res;
    }
    
    public Relacionamento pegaDadosRelacionarPorNomeIdade() {
        Relacionamento res = new Relacionamento();
        
        mostraMensagem("Informe o nome da primeira pessoa");
        String nome = scanner.nextLine();
        mostraMensagem("Informe a idade da primeira pessoa");
        int idade = scanner.nextInt();
        scanner.nextLine();
        Pessoa p1 = new Pessoa(nome, idade);
        
        mostraMensagem("Informe o nome da segunda pessoa");
        nome = scanner.nextLine();
        System.out.println("Informe a idade da segunda pessoa");
        idade = scanner.nextInt();
        scanner.nextLine();
        Pessoa p2 = new Pessoa(nome, idade);
        
        System.out.println("Há quantos anos se conhecem?");
        int tempo = scanner.nextInt();
        scanner.nextLine();
        
        res.setPessoa1(p1);
        res.setPessoa2(p2);
        res.setAnosRelacionamento(tempo);
        
        return res;
    }
    
    public int[] pegaIDDuasPessoas() {
        int[] res = new int[2];
        mostraMensagem("Informe o ID da primeira pessoa");
        res[0] = scanner.nextInt();
        scanner.nextLine();
        mostraMensagem("Informe o ID da segunda pessoa");
        res[1] = scanner.nextInt();
        scanner.nextLine();
        return res;
    }
}
