/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.EchecCaseController;
import Model.EchecModel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author UTILISATEUR
 */
public class EchecView extends JFrame implements Observateur {

    //Chargement des images
    private static final ImageIcon CASE_BLANC = new ImageIcon("./src/imgs/case_blanche.png");
    private static final ImageIcon CASE_NOIR = new ImageIcon("./src/imgs/case_noire.png");
    private static final ImageIcon CB_B = new ImageIcon("./src/imgs/blanches/CB_B.png");
    private static final ImageIcon CB_N = new ImageIcon("./src/imgs/blanches/CB_N.png");
    private static final ImageIcon DB_B = new ImageIcon("./src/imgs/blanches/DB_B.png");
    private static final ImageIcon DB_N = new ImageIcon("./src/imgs/blanches/DB_N.png");
    private static final ImageIcon FB_B = new ImageIcon("./src/imgs/blanches/FB_B.png");
    private static final ImageIcon FB_N = new ImageIcon("./src/imgs/blanches/FB_N.png");
    private static final ImageIcon PB_B = new ImageIcon("./src/imgs/blanches/PB_B.png");
    private static final ImageIcon PB_N = new ImageIcon("./src/imgs/blanches/PB_N.png");
    private static final ImageIcon RB_B = new ImageIcon("./src/imgs/blanches/RB_B.png");
    private static final ImageIcon RB_N = new ImageIcon("./src/imgs/blanches/RB_N.png");
    private static final ImageIcon TB_B = new ImageIcon("./src/imgs/blanches/TB_B.png");
    private static final ImageIcon TB_N = new ImageIcon("./src/imgs/blanches/TB_N.png");

    //Nb de lignes et colonnes de l'échiquier
    private static final int taille = 8;

    //L'échiquier
    private JLabel[][] jlEchiquier; // Le plateau

    private EchecModel m_echec; // Le jeu

    public EchecView(EchecModel _echec) {

        this.setTitle("Jeu d'échec");

        JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        JMenu nouvellePartie = new javax.swing.JMenu();
        JMenu quitter = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nouvellePartie.setText("Nouvelle partie");
        nouvellePartie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nouvellePartieMouseClicked(evt);
            }
        });
        jMenuBar1.add(nouvellePartie);

        quitter.setText("Quitter");
        quitter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quitterMouseClicked(evt);
            }
        });
        jMenuBar1.add(quitter);

        this.setJMenuBar(jMenuBar1);

        JPanel jPanelEchiquier = new JPanel();
        jPanelEchiquier.setLayout(new GridLayout(taille, taille));//définit la taille de la grille de 8 sur 8

        this.m_echec = _echec;

        this.jlEchiquier = new JLabel[taille][taille];

//        Pour chaque case du tableau
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {

                if ((i + j) % 2 == 0) {//on change la couleur une fois sur deux
                    jlEchiquier[i][j] = new JLabel(CASE_BLANC);
                } else {
                    jlEchiquier[i][j] = new JLabel(CASE_NOIR);
                }
                //Ajout de la case à l'échiquier
                jPanelEchiquier.add(jlEchiquier[i][j]);

                //Ajout d'un mouse listener
                jlEchiquier[i][j].addMouseListener( 
                       new EchecCaseController(i, j, m_echec));
            }
        }
        this.add(jPanelEchiquier);
        // La fenêtre va se redimensionner par rapport à tous ses composants
        this.pack();
    }

    private void nouvellePartieMouseClicked(java.awt.event.MouseEvent evt) {
        m_echec.nouvellePartie();
    }

    private void quitterMouseClicked(java.awt.event.MouseEvent evt) {
        this.dispose();
    }

    void initiatlisationEchiquier() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if ((i + j) % 2 == 0) {//on change la couleur une fois sur deux
                    jlEchiquier[i][j].setIcon(CASE_BLANC);
                } else {
                    jlEchiquier[i][j].setIcon(CASE_NOIR);
                }
            }
        }
    }

    public void majGraphique(int i, int j) {
        System.out.println("coucou");
    }

    @Override
    public void avertir(int i, int j) {
        majGraphique(i, j);
    }

    @Override
    public void avertirNouvellePartie() {
        initiatlisationEchiquier();
    }

    @Override
    public void avertirFinPartie() {
        JOptionPane.showMessageDialog(null, "Echec et Mat !",
                "Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
    }

}
