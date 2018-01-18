/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Class.Case;
import Controller.EchecCaseController;
import Model.EchecModel;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
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

    //[couleur case][couleur pièce][type pièce]
    private static ImageIcon imgPieces[][][];

    //Nb de lignes et colonnes de l'échiquier
    private static final int TAILLE = 8;

    //le menu
    private JMenuBar jMenuBar1;
    private JMenu nouvellePartie;
    private JMenu quitter;

    //L'échiquier
    private JPanel jPanelEchiquier;
    private JLabel[][] jlEchiquier; // Le plateau

    private EchecModel m_echec; // Le jeu

    public EchecView(EchecModel _echec) {

        this.imgPieces = new ImageIcon[3][2][9];

        imgPieces[0][0][0] = new ImageIcon("./src/imgs/blanches/PB_B.png");
        imgPieces[1][0][0] = new ImageIcon("./src/imgs/blanches/PB_N.png");
        imgPieces[0][0][1] = new ImageIcon("./src/imgs/blanches/TB_B.png");
        imgPieces[1][0][1] = new ImageIcon("./src/imgs/blanches/TB_N.png");
        imgPieces[0][0][2] = new ImageIcon("./src/imgs/blanches/CB_B.png");
        imgPieces[1][0][2] = new ImageIcon("./src/imgs/blanches/CB_N.png");
        imgPieces[0][0][3] = new ImageIcon("./src/imgs/blanches/FB_B.png");
        imgPieces[1][0][3] = new ImageIcon("./src/imgs/blanches/FB_N.png");
        imgPieces[0][0][4] = new ImageIcon("./src/imgs/blanches/DB_B.png");
        imgPieces[1][0][4] = new ImageIcon("./src/imgs/blanches/DB_N.png");
        imgPieces[0][0][5] = new ImageIcon("./src/imgs/blanches/RB_B.png");
        imgPieces[1][0][5] = new ImageIcon("./src/imgs/blanches/RB_N.png");
        imgPieces[0][1][0] = new ImageIcon("./src/imgs/noires/PN_B.png");
        imgPieces[1][1][0] = new ImageIcon("./src/imgs/noires/PN_N.png");
        imgPieces[0][1][1] = new ImageIcon("./src/imgs/noires/TN_B.png");
        imgPieces[1][1][1] = new ImageIcon("./src/imgs/noires/TN_N.png");
        imgPieces[0][1][2] = new ImageIcon("./src/imgs/noires/CN_B.png");
        imgPieces[1][1][2] = new ImageIcon("./src/imgs/noires/CN_N.png");
        imgPieces[0][1][3] = new ImageIcon("./src/imgs/noires/FN_B.png");
        imgPieces[1][1][3] = new ImageIcon("./src/imgs/noires/FN_N.png");
        imgPieces[0][1][4] = new ImageIcon("./src/imgs/noires/DN_B.png");
        imgPieces[1][1][4] = new ImageIcon("./src/imgs/noires/DN_N.png");
        imgPieces[0][1][5] = new ImageIcon("./src/imgs/noires/RN_B.png");
        imgPieces[1][1][5] = new ImageIcon("./src/imgs/noires/RN_N.png");
        imgPieces[2][0][0] = new ImageIcon("./src/imgs/blanches transparentes/PB.png");
        imgPieces[2][0][1] = new ImageIcon("./src/imgs/blanches transparentes/TB.png");
        imgPieces[2][0][2] = new ImageIcon("./src/imgs/blanches transparentes/CB.png");
        imgPieces[2][0][3] = new ImageIcon("./src/imgs/blanches transparentes/FB.png");
        imgPieces[2][0][4] = new ImageIcon("./src/imgs/blanches transparentes/DB.png");
        imgPieces[2][0][5] = new ImageIcon("./src/imgs/blanches transparentes/RB.png");
        imgPieces[2][1][0] = new ImageIcon("./src/imgs/noires transparentes/PN.png");
        imgPieces[2][1][1] = new ImageIcon("./src/imgs/noires transparentes/TN.png");
        imgPieces[2][1][2] = new ImageIcon("./src/imgs/noires transparentes/CN.png");
        imgPieces[2][1][3] = new ImageIcon("./src/imgs/noires transparentes/FN.png");
        imgPieces[2][1][4] = new ImageIcon("./src/imgs/noires transparentes/DN.png");
        imgPieces[2][1][5] = new ImageIcon("./src/imgs/noires transparentes/RN.png");

        this.setTitle("Jeu d'échec");
        this.setResizable(false);

        this.jMenuBar1 = new javax.swing.JMenuBar();
        this.nouvellePartie = new javax.swing.JMenu();
        this.quitter = new javax.swing.JMenu();

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

        jPanelEchiquier = new JPanel();
        jPanelEchiquier.setLayout(new GridLayout(TAILLE, TAILLE));//définit la taille de la grille de 8 sur 8

        this.m_echec = _echec;

        this.jlEchiquier = new JLabel[TAILLE][TAILLE];

//        Pour chaque case du tableau
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {

                if ((i + j) % 2 == 0) {//on change la couleur une fois sur deux
                    jlEchiquier[i][j] = new JLabel(CASE_BLANC);
                } else {
                    jlEchiquier[i][j] = new JLabel(CASE_NOIR);
                }
                //Ajout de la case à l'échiquier
                jPanelEchiquier.add(jlEchiquier[i][j]);

                //Ajout d'un mouse listener
                jlEchiquier[i][j].addMouseListener(
                        new EchecCaseController(m_echec.chercherCase(i, j), m_echec));
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
        for (int rangee = 0; rangee < TAILLE; rangee++) {
            for (int colonne = 0; colonne < TAILLE; colonne++) {
                Case[][] echiquier = EchecModel.getEchiquier();//recuperation des pieces sur l'echiquier
                if ((rangee + colonne) % 2 == 0) { //changement de la couleur une fois sur deux
                    if (echiquier[rangee][colonne].estOccupe()) {
                        int couleurPiece = echiquier[rangee][colonne].getPiece().getCouleur();
                        int typePiece = echiquier[rangee][colonne].getPiece().getType();
                        jlEchiquier[rangee][colonne].setIcon(imgPieces[0][couleurPiece][typePiece]);
                    } else {
                        jlEchiquier[rangee][colonne].setIcon(CASE_BLANC);
                    }
                } else {
                    if (echiquier[rangee][colonne].estOccupe()) {
                        int couleurPiece = echiquier[rangee][colonne].getPiece().getCouleur();
                        int typePiece = echiquier[rangee][colonne].getPiece().getType();
                        jlEchiquier[rangee][colonne].setIcon(imgPieces[1][couleurPiece][typePiece]);
                    } else {
                        jlEchiquier[rangee][colonne].setIcon(CASE_NOIR);
                    }
                }
            }
        }
    }

    public void majGraphique(int i, int j) {
    }

    @Override
    public void avertirEnDeplacement(Case caseReferente) {
        //on change le curseur de la souris en fonction de la piece en déplacement
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                imgPieces[2][caseReferente.getPiece().getCouleur()][caseReferente.getPiece().getType()].getImage(),
                new Point(0, 0),
                "custom cursor"
        ));

        //on affiche une case vide à la place de la case avec la pièce
        int rangee = caseReferente.getRangee();
        int colonne = caseReferente.getColonne();
        if ((rangee + colonne) % 2 == 0) {
            jlEchiquier[rangee][colonne].setIcon(CASE_BLANC);
        } else {
            jlEchiquier[rangee][colonne].setIcon(CASE_NOIR);
        }
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

    @Override
    public void avertir(Case caseReferente) {
        //on remet le curseur par défault
        this.setCursor(Cursor.getDefaultCursor());

        //on affiche la case avecla pièce
        int rangee = caseReferente.getRangee();
        int colonne = caseReferente.getColonne();
        if ((rangee + colonne) % 2 == 0) {
            jlEchiquier[rangee][colonne].setIcon(
                    imgPieces[0][caseReferente.getPiece().getCouleur()][caseReferente.getPiece().getType()]
            );
        } else {
            jlEchiquier[rangee][colonne].setIcon(
                    imgPieces[1][caseReferente.getPiece().getCouleur()][caseReferente.getPiece().getType()]
            );
        }
    }

}
