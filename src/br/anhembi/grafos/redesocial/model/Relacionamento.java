package br.anhembi.grafos.redesocial.model;

/**
 * Representa um relacionamento entre duas {@link Pessoa}s.
 * 
 * @author  Gabriel Batista
 * @author  Henrique Albanese
 * @author  Sérgio Umlauf
 */
public class Relacionamento {
    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private int anosRelacionamento;
    private int idPessoa1;
    private int idPessoa2;


    public Relacionamento() {
        
    }
    
    public Relacionamento(Pessoa pessoa1, Pessoa pessoa2, int anosRelacionamento) {
        this.pessoa1 = pessoa1;
        this.pessoa2 = pessoa2;
        this.anosRelacionamento = anosRelacionamento;
    }

    public Relacionamento(int idPessoa1, int idPessoa2, int anosRelacionamento) {
        this.idPessoa1 = idPessoa1;
        this.idPessoa2 = idPessoa2;
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

    /**
     * @return the idPessoa1
     */
    public int getIdPessoa1() {
        return idPessoa1;
    }

    /**
     * @param idPessoa1 the idPessoa1 to set
     */
    public void setIdPessoa1(int idPessoa1) {
        this.idPessoa1 = idPessoa1;
    }

    /**
     * @return the idPessoa2
     */
    public int getIdPessoa2() {
        return idPessoa2;
    }

    /**
     * @param idPessoa2 the idPessoa2 to set
     */
    public void setIdPessoa2(int idPessoa2) {
        this.idPessoa2 = idPessoa2;
    }
    
    
}
