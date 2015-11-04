package br.anhembi.grafos.redesocial.model;

/**
 * Representa um relacionamento entre duas {@link Pessoa}s.
 * 
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class Relacionamento {
    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private int anosRelacionamento;

    
    public Relacionamento(Pessoa pessoa1, Pessoa pessoa2, int anosRelacionamento) {
        this.pessoa1 = pessoa1;
        this.pessoa2 = pessoa2;
        this.anosRelacionamento = anosRelacionamento;
    }
    
    /**
     * @return the pessoa1
     */
    public Pessoa getPessoa1() {
        return pessoa1;
    }

    /**
     * @param pessoa1 the pessoa1 to set
     */
    public void setPessoa1(Pessoa pessoa1) {
        this.pessoa1 = pessoa1;
    }

    /**
     * @return the pessoa2
     */
    public Pessoa getPessoa2() {
        return pessoa2;
    }

    /**
     * @param pessoa2 the pessoa2 to set
     */
    public void setPessoa2(Pessoa pessoa2) {
        this.pessoa2 = pessoa2;
    }

    /**
     * @return the anosRelacionamento
     */
    public int getAnosRelacionamento() {
        return anosRelacionamento;
    }

    /**
     * @param anosRelacionamento the anosRelacionamento to set
     */
    public void setAnosRelacionamento(int anosRelacionamento) {
        this.anosRelacionamento = anosRelacionamento;
    }
    
    
}
