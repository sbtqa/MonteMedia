/* @(#)MovieConverterXMain.java
 * Copyright © 2011 Werner Randelshofer, Switzerland. 
 * You may only use this software in accordance with the license terms.
 */
package org.monte.movieconverter;

import ru.sbtqa.monte.media.DefaultMovie;
import ru.sbtqa.monte.media.Movie;
import ru.sbtqa.monte.media.MovieReader;
import ru.sbtqa.monte.media.Registry;
import ru.sbtqa.monte.media.gui.Worker;
import ru.sbtqa.monte.media.gui.datatransfer.DropFileTransferHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 * MovieConverterXMain.
 *
 * @author Werner Randelshofer
 * @version 1.0 2011-09-04 Created.
 */
public class MovieConverterXMain extends javax.swing.JFrame {
    private final static long serialVersionUID = 1L;

    private class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            File f=new File(e.getActionCommand());
            if (isEnabled())
            setMovieFile(f);
        }
        
    }
    private Handler handler=new Handler();
    
    /** Creates new form MovieConverterXMain */
    public MovieConverterXMain() {
        initComponents();
        DropFileTransferHandler dfth=new DropFileTransferHandler(JFileChooser.FILES_ONLY);
        dfth.setActionListener(handler);
        setTransferHandler(dfth);
        movieConverterPanel.setTransferHandler(dfth);
    }
    
    public void setMovieFile(final File newFile) {
        setEnabled(false);
        setTitle(null);
        getRootPane().putClientProperty("Window.documentFile", null);
        new Worker<Movie>() {

            @Override
            protected Movie construct() throws Exception {
                
                MovieReader r=Registry.getInstance().getReader(newFile);
                if (r==null)throw new IOException("no reader");
                DefaultMovie m=new DefaultMovie();
                m.setReader(r);
                return m;
            }

            @Override
            protected void done(Movie movie) {
        getRootPane().putClientProperty("Window.documentFile", newFile);
        setTitle(newFile.getName());
        movieConverterPanel.setMovie(movie);
            }

            @Override
            protected void finished() {
              setEnabled(true);
            }
            
            
        }.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        movieConverterPanel = new org.monte.movieconverter.MovieConverterPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(movieConverterPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MovieConverterXMain().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.monte.movieconverter.MovieConverterPanel movieConverterPanel;
    // End of variables declaration//GEN-END:variables
}
