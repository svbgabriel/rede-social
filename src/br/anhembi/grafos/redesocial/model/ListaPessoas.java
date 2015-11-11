package br.anhembi.grafos.redesocial.model;

/**
 * Manipula a lista de pessoas da rede social. Esta classe apenas insere ou
 * remove pessoas da rede, como um cadastro na rede. Ele não associa pessoas.
 * Para isso, use {@link RedeSocial}.
 *
 * @author  Gabriel Batista
 * @author  Henrique Albanese
 * @author  Sérgio Umlauf
 */
public class ListaPessoas {

    private final Pessoa[] lista;
    private int quantidade = 0;

    /**
     * Construtor
     *
     * @param tamanho   Quantidade máxima de pessoas da rede.
     */
    public ListaPessoas(int tamanho) {
        lista = new Pessoa[tamanho];
    }

    /**
     * Quantidade de pessoas na lista.
     *
     * @return          A quantidade de pessoas.
     */
    public int getQuantidade() {
        return this.quantidade;
    }

    /**
     * Busca uma pessoa por seu índice.
     *
     * @param indice    Índice da pessoa
     * @return          Uma instância de {@link model.Pessoa} ou null, se não achar.
     */
    public Pessoa getPessoa(int indice) {
        if (indice < 0 || indice > this.lista.length - 1) {
            return null;
        }
        return this.lista[indice];
    }

    /**
     * Insere uma pessoa na lista (pessoa se "cadastra na rede"). Este método
     * procura por uma posição "vazia" na lista e insere a pessoa na primeira
     * que achar.
     *
     * @param pessoa    Uma instância de Pessoa
     * @return          O índice da pessoa na lista ou -1, se não foi possível inseri-la.
     */
    public int insere(Pessoa pessoa) {
        // Procura uma posição no vetor que seja null.
        // Se achar, coloca a pessoa nesta posição.
        for (int i = 0; i < this.lista.length; i++) {
            if (this.lista[i] == null) {
                pessoa.setId(i);
                this.lista[i] = pessoa;
                this.quantidade++;
                return i;
            }
        }
        return -1;
    }

    /**
     * Remove a pessoa da lista (pessoa é "escluída da rede").
     *
     * @param indice    Índice da pessoa
     * @return          true, se a remoção foi possível; false, caso contrário.
     */
    public boolean remove(int indice) {
        if (indice >= 0 && indice < this.lista.length) {
            this.lista[indice] = null;
            this.quantidade--;
            return true;
        }
        return false;
    }

    /**
     * Remove a pessoa da lista (pessoa é "escluída da rede").
     *
     * @param pessoa    Uma instância de Pessoa
     * @return          true, se a remoção foi possível; false, caso contrário.
     * @see             remove(int)
     */
    public boolean remove(Pessoa pessoa) {
        int indice = this.getIndice(pessoa);
        return this.remove(indice);
    }

    /**
     * Retorna o índice de uma pessoa na lista. Utilize este método quando você
     * precisa do índice da pessoa para manipulá-la na rede, mas só tem uma
     * instância de {@link model.Pessoa}.
     *
     * @param pessoa    uma instância de {@link model.Pessoa}
     * @return          o índice da pessoa na rede social (lista).
     */
    public int getIndice(Pessoa pessoa) {
        for (int i = 0; i < this.lista.length; i++) {
            if (this.lista[i] != null && this.lista[i].equals(pessoa)) {
                return i; // achou a pessoa na lista
            }
        }
        return -1; // não achou a pessoa na lista
    }

    /**
     * @return          the lista
     */
    public Pessoa[] getLista() {
        return this.lista;
    }

    
    /**
     * Procura uma pessoa por parte do nome.
     * 
     * @param nome      Todo ou parte do nome (case insensitive)
     * @return          Uma {@link Pessoa} ou null, se não achar
     */
    public Pessoa search(String nome) {
        for (int i = 0; i < this.lista.length; i++) {
            if (this.lista[i] != null && this.lista[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
                return this.lista[i]; // achou a pessoa na lista
            }
        }
        return null;
    }
}
