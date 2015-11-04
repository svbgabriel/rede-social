package br.anhembi.grafos.redesocial;

import br.anhembi.grafos.redesocial.core.RedeSocial;
import br.anhembi.grafos.redesocial.model.Pessoa;
import br.anhembi.grafos.redesocial.model.Relacionamento;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

/**
 * Exibe um applet com o grafo da rede.
 * 
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class Applet extends JApplet
{
    private static final long serialVersionUID = 3256444702936019250L;
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 530);
    private JGraphModelAdapter m_jgAdapter;
    

    /**
     * Exibe o applet.
     * 
     * @param   redeSocial A {@link RedeSocial}
     */
    public void show(RedeSocial redeSocial)
    { 
        Applet applet = new Applet();
        
        applet.init(redeSocial);
        
        JFrame frame = new JFrame();

        frame.getContentPane().add(applet);
        frame.setTitle("Rede Social");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    
    /**
     * Monta o grafo.
     * 
     * @param   redeSocial A {@link RedeSocial}
     */
    public void init(RedeSocial redeSocial) {
        // create a JGraphT graph
        ListenableGraph g = new ListenableUndirectedWeightedGraph ( DefaultEdge.class );

        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter( g );

        JGraph jgraph = new JGraph( m_jgAdapter );

        adjustDisplaySettings( jgraph );
        getContentPane(  ).add( jgraph );
        resize( DEFAULT_SIZE );
        
        // Adiciona os vertices
        Pessoa[] pessoas = redeSocial.getListaPessoas().getLista();
        for(Pessoa pessoa : pessoas) {
            if(pessoa != null) {
                g.addVertex( pessoa.getNome() );
            }
        }
        
        // Adiciona as arestas
        List<Relacionamento> relacionamentos = redeSocial.relacionamentos();
        for(Relacionamento relacionamento : relacionamentos) {
            g.addEdge( 
                    relacionamento.getPessoa1().getNome(),
                    relacionamento.getPessoa2().getNome(),
                    relacionamento.getAnosRelacionamento()
                    );
        }
        
        // Posiciona os vertices
        int posX = 100, posY = 100;
        for(Pessoa pessoa : pessoas) {
            if(pessoa != null) {
                positionVertexAt( pessoa.getNome(), posX, posY );
                posX += 70;
                posY += 70;
            }
        }

//        // add some sample data (graph manipulated via JGraphT)
//        g.addVertex( "Sérgio" );
//        g.addVertex( "Henrique" );
//        g.addVertex( "Gabriel" );
//        g.addVertex( "Marcão" );
//
//        g.addEdge( "Sérgio", "Henrique", 3 );
//        g.addEdge( "Henrique", "Gabriel", 3 );
//        g.addEdge( "Gabriel", "Sérgio", 3 );
//        g.addEdge( "Marcão", "Gabriel", 3 );
//
//        // position vertices nicely within JGraph component
//        positionVertexAt( "Sérgio", 130, 40 );
//        positionVertexAt( "Henrique", 60, 200 );
//        positionVertexAt( "Gabriel", 310, 230 );
//        positionVertexAt( "Marcão", 380, 70 );
    }

    
    
    private void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }
    
    
    private void positionVertexAt( Object vertex, int x, int y ) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        Map              attr = cell.getAttributes(  );
        Rectangle2D      r2d  = GraphConstants.getBounds( attr );

        GraphConstants.setBounds( attr, new Rectangle( x, y, (int)r2d.getWidth(), (int)r2d.getHeight() ) );

        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        m_jgAdapter.edit( cellAttr, null, null, null );
    }
}
