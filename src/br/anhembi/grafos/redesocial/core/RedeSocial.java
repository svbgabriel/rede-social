package br.anhembi.grafos.redesocial.core;

import br.anhembi.grafos.redesocial.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Classe para gerenciar a adição e remoção de relacionamentos na rede social.
 *
 * Esta classe manipula a classe GrafoSocial, que extende a classe Grafo,
 * que deve ser mantida o mais próximo possível da original disponibilizada 
 * pelo professor.
 *
 * A ideia é utilizar apenas esta classe na classe principal do programa (Main).
 *
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class RedeSocial {

    private int tamanho = 0;
    private ListaPessoas listaPessoas;
    private GrafoSocial grafo;

    /**
     * Construtor
     *
     * @param tamanho Quantidade máxima de pessoas da rede.
     */
    public RedeSocial(int tamanho) {
        this.tamanho = tamanho;
        this.listaPessoas = new ListaPessoas(tamanho);
        this.grafo = new GrafoSocial(new int[tamanho][tamanho]);
    }

    /**
     * Insere uma pessoa no cadastro
     *
     * @param pessoa Uma {@link Pessoa}
     * @return true, se foi inserida com sucesso, ou false, caso tenha falhado.
     */
    public int insere(Pessoa pessoa) {
        return this.getListaPessoas().insere(pessoa);
    }

    /**
     * Remove uma pessoa do cadastro e seus relacionamentos
     *
     * @param pessoa {@link Pessoa} a ser removida.
     * @return true, se foi removida com sucesso, ou false, caso tenha falhado.
     */
    public boolean remove(Pessoa pessoa) {
        int indicePessoa = this.getIndice(pessoa);

        if (indicePessoa == -1) {
            return false;
        }

        int[] relacionamentos = this.grafo.buscaLargura(indicePessoa);

        // Remove a pessoa e seus relacionamentos do Grafo
        for (int i = 0; i < relacionamentos.length; i++) {
            if (relacionamentos[i] != 0) {
                this.grafo.removeAresta(indicePessoa, i);
            }
        }

        // Remove o "cadastro" da pessoa
        return this.getListaPessoas().remove(pessoa);
    }

    /**
     * Remove uma pessoa da rede social e seus relacionamentos
     *
     * @param indice Índice da pessoa.
     * @return true, se foi removida com sucesso, ou false, caso tenha falhado.
     */
    public boolean remove(int indice) {
        int[] relacionamentos = this.grafo.buscaLargura(indice);

        Pessoa p = this.getPessoa(indice);

        if (p == null) {
            return false;
        }

        // Remove a pessoa e seus relacionamentos do Grafo
        for (int i = 0; i < relacionamentos.length; i++) {
            if (relacionamentos[i] != 0) {
                this.grafo.removeAresta(indice, i);
            }
        }

        // Remove o "cadastro" da pessoa       
        return this.getListaPessoas().remove(indice);
    }

    /**
     * Relaciona duas pessoas.
     *
     * @param pessoa1 Pessoa 1
     * @param pessoa2 Pessoa 2
     * @param anosRelacionamento Anos de relacionamento entre elas
     * @return True se o relacionamento foi feito com sucesso.
     */
    public boolean relacionar(Pessoa pessoa1, Pessoa pessoa2, int anosRelacionamento) {
        int indicePessoa1 = getListaPessoas().getIndice(pessoa1);
        int indicePessoa2 = getListaPessoas().getIndice(pessoa2);
        if (indicePessoa1 >= 0 && indicePessoa2 >= 0) {
            this.grafo.adicionaAresta(indicePessoa1, indicePessoa2, anosRelacionamento);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Relaciona duas pessoas.
     *
     * @param indicePessoa1 Índice da pessoa 1
     * @param indicePessoa2 Índice da pessoa 2
     * @param anosRelacionamento Anos de relacionamento entre elas
     * @return True se o relacionamento foi feito com sucesso.
     */
    public boolean relacionar(int indicePessoa1, int indicePessoa2, int anosRelacionamento) {
        Pessoa p = getPessoa(indicePessoa1);
        if (p == null) {
            return false;
        }
        p = getPessoa(indicePessoa2);
        if (p == null) {
            return false;
        }
        if (indicePessoa1 >= 0 && indicePessoa2 >= 0) {
            this.grafo.adicionaAresta(indicePessoa1, indicePessoa2, anosRelacionamento);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retorna uma pessoa da rede.
     *
     * @param indice Índice da pessoa
     * @return A {@link Pessoa} procurada ou null, se não encontrar.
     */
    public Pessoa getPessoa(int indice) {
        return this.getListaPessoas().getPessoa(indice);
    }

    /**
     * Retorna o índice de uma pessoa na lista. Utilize este método quando você
     * precisa do índice da pessoa para manipulá-la na rede, mas só tem uma
     * instância de {@link model.Pessoa}.
     *
     * @param pessoa uma instância de {@link model.Pessoa}
     * @return o índice da pessoa na rede social (lista).
     */
    public int getIndice(Pessoa pessoa) {
        return this.getListaPessoas().getIndice(pessoa);
    }

    /**
     * Lista os amigos diretos de uma pessoa.
     *
     * @param indice Índice da pessoa.
     * @return Um ArrayList de {@link Pessoa}s, amigas da pessoa.
     */
    public List<Pessoa> listaAmigos(int indice) {
        List<Pessoa> result = new ArrayList<Pessoa>();
        List<Integer> nosVizinhos = this.grafo.vizinhos(indice);
        for (int i : nosVizinhos) {
            result.add(getPessoa(i));
        }
        return result;
    }

    /**
     * Quantidade de vértices entre duas pessoas.
     *
     * @param indice1 Índice da pessoa 1
     * @param indice2 Índice da pessoa 2
     * @return a quantidade de vértices entre as duas pessoas; 0, se as duas
     * pessaos estiverem conectadas diretamente; -1 se não estiverem conectadas.
     */
    public int numeroVerticesEntreDuasPessoas(int indice1, int indice2) {
        if (indice1 == -1 || indice2 == -1) {
            return -1;
        }
        
        int[] antecessores = this.grafo.listaAntecessores(indice1);

        if (antecessores[indice2] == 0 && listaPessoas.getPessoa(indice2) == null) {
            // A pessoa de indice2 não está conectada a ninguém. (Forever Alone)
            return -1;
        }

        int result = -1;
        int antecessor = indice2;
        while (antecessor != indice1) {
            antecessor = antecessores[antecessor];
            result++;
        }

        return result;
    }

    /**
     * Quantidade de vértices entre duas pessoas.
     *
     * @param pessoa1 Pessoa 1
     * @param pessoa2 Pessoa 2
     * @return a quantidade de vértices entre as duas pessoas; 0, se as duas
     * pessaos estiverem conectadas diretamente; -1 se não estiverem conectadas.
     * @see #numeroVerticesEntreDuasPessoas(int, int)
     */
    public int numeroVerticesEntreDuasPessoas(Pessoa pessoa1, Pessoa pessoa2) {
        int indicePessoa1 = this.getListaPessoas().getIndice(pessoa1);
        int indicePessoa2 = this.getListaPessoas().getIndice(pessoa2);

        return this.numeroVerticesEntreDuasPessoas(indicePessoa1, indicePessoa2);
    }

    /**
     * Retorna a árvore mínima da rede. Se <code>verticeInicial</code> estiver
     * fora do intervalo entre 0 e [tamanho da rede - 1], o algoritmo de Kruskal
     * é utilizado. Caso contrário, é utilizado o algoritmo de Prim.
     *
     * Nota: A string retorna o tamanho da rede. Caso não haja aresta entre dois
     * índices, retorna "[0,0]".
     *
     * @param verticeInicial Vértice inicial
     * @return uma string com os vértices da árvore mínima. Exemplo: "[0,2],
     * [0,3] [1,2], [0,0], [0,0], "
     */

    /**
     * Retorna a lista de {@link Pessoa}s da árvore mínima.
     *
     * @return uma lista de duplas de {@link Pessoa}s.
     */
    public List<Pessoa[]> getArvoreMinima(int verticeInicial) {
        List<Pessoa[]> result = new ArrayList<Pessoa[]>();

        String arvore;
        if (verticeInicial < 0 || verticeInicial > this.tamanho - 1) {
            arvore = this.grafo.kruskal();
        } else {
            arvore = this.grafo.prim(verticeInicial);
        }

        // A string da árvore mínima está retornando com ", " no final.
        // Aqui removemos o ", ".
        if (arvore.endsWith(", ")) {
            arvore = arvore.substring(0, arvore.length() - 2);
        }

        // [0,2], [0,3], [1,2] => [0,2],[0,3],[1,2]
        arvore = arvore.replace(" ", "");
        // [0,2],[0,3],[1,2] => [0,2];[0,3];[1,2]
        arvore = arvore.replace("],", "];");

        // Cada token vai conter: "[i,j]"
        StringTokenizer token = new StringTokenizer(arvore, ";");
        while (token.hasMoreTokens()) {
            String t = token.nextToken();
            t = t.replace("[", "");
            t = t.replace("]", "");
            String[] indices = t.split(",");

            if (indices[0].equals(indices[1])) {
                // Árvore tem um nó [0,0], por exemplo.
                continue;
            }

            Pessoa[] pessoas = new Pessoa[2];
            for (int i = 0; i < indices.length; i++) {
                pessoas[i] = this.getPessoa(Integer.parseInt(indices[i]));
            }
            result.add(pessoas);
        }

        return result;
    }

    /**
     * Quantidade de pessoas cadastradas na rede.
     *
     * @return a quantidade de pessoas.
     */
    public int getQuantidade() {
        return this.getListaPessoas().getQuantidade();
    }

    /**
     * Número de conexões da rede. Sergio/Henrique e Henrique/Sergio é
     * considerada uma conexão.
     *
     * @return a quantidade de conexões da rede.
     */
    public int numeroConexoes() {
        return this.grafo.quantidadeArestas();
    }

    /**
     * Lista de pares de {@link Pessoa}s. O para de pessoas é um array de 2
     * posições.
     *
     * @param repetirConexoes True, se desejar que a lista retorne conexões
     * repetidas (por exemplo, Sergio-Gabriel e Gabriel-Sergio).
     * @return uma lista de pares de pessoas (amigas entre si).
     */
    public List<Pessoa[]> conexoes(boolean repetirConexoes) {
        List<Pessoa[]> result = new ArrayList<Pessoa[]>();

        int[][] conexoes = this.grafo.conexoes(repetirConexoes);

        for (int i = 0; i < conexoes.length; i++) {
            for (int j = 0; j < conexoes[i].length; j++) {
                if (conexoes[i][j] >= 0) {
                    Pessoa[] pessoas = new Pessoa[2];
                    pessoas[0] = this.getPessoa(i);
                    pessoas[1] = this.getPessoa(conexoes[i][j]);
                    result.add(pessoas);
                }
            }
        }

        return result;
    }

    /**
     * Lista de {@link Relacionamento}s da rede.
     *
     * @return uma lista de {@link Relacionamento}s.
     */
    public List<Relacionamento> relacionamentos() {
        List<Relacionamento> result = new ArrayList<Relacionamento>();

        int[][] matriz = this.grafo.getMatrizAdjacencia();

        for (int i = 0; i < matriz.length; i++) {
            for (int j = i + 1; j < matriz[i].length; j++) {
                if (matriz[i][j] > 0) {
                    Pessoa pessoa1 = getPessoa(i);
                    Pessoa pessoa2 = getPessoa(j);
                    Relacionamento relacionamento = new Relacionamento(pessoa1, pessoa2, matriz[i][j]);
                    result.add(relacionamento);
                }
            }
        }
        return result;
    }

    /**
     * Imprime o status da rede social. Usuários cadastrados Somatório dos pesos
     * das arestas Númeor de conexões Pessoas conectadas
     *
     * @param repetirConexoes True, se desejar que a lista retorne conexões
     * repetidas (por exemplo, Sergio-Gabriel e Gabriel-Sergio).
     */
    public void status(boolean repetirConexoes) {
        System.out.println("Usuários cadastrados:\t\t\t" + this.getQuantidade());
        System.out.println("Somatório dos pesos das arestas:\t" + this.grafo.numeroArestas());
        System.out.println("Conexões:\t\t\t\t" + this.numeroConexoes());

        System.out.println("\nPessoas conectadas: ");
        List<Pessoa[]> conexoes = this.conexoes(repetirConexoes);
        for (Pessoa[] arrayPessoas : conexoes) {
            System.out.println("\t" + arrayPessoas[0].getNome() + " está conectado a " + arrayPessoas[1].getNome());
        }
    }

    /**
     * Lista de pessoas da rede. A lista contém apenas as pessoas; não inclui o
     * relacionamento entre elas.
     *
     * @return Uma {@link ListaPessoas}
     */
    public ListaPessoas getListaPessoas() {
        return listaPessoas;
    }
    
    
    public Pessoa procurarPessoa(String nome) {
        return this.listaPessoas.search(nome);
    }
}
