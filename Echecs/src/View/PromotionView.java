/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Class.Case;
import Class.Cavalier;
import Class.Echiquier;
import Class.Fou;
import Class.Reine;
import Class.Tour;
import Controller.EchecController;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author victor
 */
public class PromotionView extends JFrame {

    private final JPanel jPanelChoix;
    private final EchecController echec_c;
    private final Case caseReferente;
    private final Echiquier echiquier;
    private final int couleurJoueur;
    private final JLabel jlTour;
    private final JLabel jlFou;
    private final JLabel jlCavalier;
    private final JLabel jlReine;
    private final ImageIcon[][] imgPieces;

    public PromotionView(EchecController _echec_c, Case _caseReferente, Echiquier _echiquier, int _couleurJoueur) {
        this.echec_c = _echec_c;
        this.couleurJoueur = _couleurJoueur;
        this.caseReferente = _caseReferente;
        this.echiquier = _echiquier;

        this.imgPieces = new ImageIcon[2][5];

        imgPieces[0][1] = new ImageIcon("./src/imgs/blanches transparentes/TB.png");
        imgPieces[0][2] = new ImageIcon("./src/imgs/blanches transparentes/CB.png");
        imgPieces[0][3] = new ImageIcon("./src/imgs/blanches transparentes/FB.png");
        imgPieces[0][4] = new ImageIcon("./src/imgs/blanches transparentes/DB.png");
        imgPieces[1][1] = new ImageIcon("./src/imgs/noires transparentes/TN.png");
        imgPieces[1][2] = new ImageIcon("./src/imgs/noires transparentes/CN.png");
        imgPieces[1][3] = new ImageIcon("./src/imgs/noires transparentes/FN.png");
        imgPieces[1][4] = new ImageIcon("./src/imgs/noires transparentes/DN.png");

        this.setTitle("Promotion");
        this.setResizable(false);

        this.jPanelChoix = new JPanel();
        jPanelChoix.setLayout(new GridLayout(1, 4));
        this.add(jPanelChoix);

        this.jlTour = new JLabel(imgPieces[couleurJoueur][1]);
        this.jlFou = new JLabel(imgPieces[couleurJoueur][2]);
        this.jlCavalier = new JLabel(imgPieces[couleurJoueur][3]);
        this.jlReine = new JLabel(imgPieces[couleurJoueur][4]);

        jlTour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                promouvoir(1);
            }
        });
        jlCavalier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                promouvoir(2);
            }
        });
        jlFou.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                promouvoir(3);
            }
        });
        jlReine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                promouvoir(4);
            }

        });

        jPanelChoix.add(jlTour);
        jPanelChoix.add(jlFou);
        jPanelChoix.add(jlCavalier);
        jPanelChoix.add(jlReine);

        this.pack();

        this.setVisible(true);

    }

    public void promouvoir(int type) {
        if (type == 1) {
            caseReferente.setPiece(new Tour(couleurJoueur, echiquier));
        } else if (type == 2) {
            caseReferente.setPiece(new Cavalier(couleurJoueur, echiquier));
        } else if (type == 3) {
            caseReferente.setPiece(new Fou(couleurJoueur, echiquier));
        } else if (type == 4) {
            caseReferente.setPiece(new Reine(couleurJoueur, echiquier));
        }
        echec_c.avertirObservateurs(caseReferente);
        this.dispose();
        if (echiquier.verifierEchec()) {
            if (echiquier.verifierEchecEtMat(couleurJoueur == 0 ? 1 : 0)) {
                echec_c.avertirFinPartieAllObservateurs();
            } else {
                echec_c.avertirEchecObservateurs();
            }
        }
    }
}
