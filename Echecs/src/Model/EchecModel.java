/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Class.Case;
import Class.Cavalier;
import Class.Echiquier;
import Class.Fou;
import Class.Pion;
import Class.Reine;
import Class.Roi;
import Class.Tour;
import Controller.EchecController;

/**
 *
 * @author vmachu
 */
public class EchecModel {

    private final EchecController echec_c;
    private final Echiquier echiquier;
    private int joueurEnJeu;
    private static final int TAILLE = 8;

    public EchecModel(EchecController _echec_c) {
        this.echec_c = _echec_c;
        this.joueurEnJeu = 0;
        this.echiquier = new Echiquier();
    }

    public Echiquier getEchiquier() {
        return echiquier;
    }

    public int getJoueurEnJeu() {
        return joueurEnJeu;
    }

    public void nouvellePartie() {

        joueurEnJeu = 0;
        int couleur = 1;

        for (int rangee = 0; rangee <= TAILLE - 1; rangee++) {//pour chaque rangée
            for (int colonne = 0; colonne <= TAILLE - 1; colonne++) {//pour chaque colonne
                echiquier.chercherCase(rangee, colonne).setEnDeplacement(false);
                echiquier.chercherCase(rangee, colonne).setPiece(null);
                if (rangee == 1 || rangee == 6) {//si la case se situe sur la ligne de pions
                    if (rangee == 6) {
                        couleur = 0;
                    }
                    //on initialise le pion
                    echiquier.chercherCase(rangee, colonne).setPiece(new Pion(couleur, echiquier));
                }
            }
            if (rangee == 0 || rangee == 7) {//si la case se situe sur la ligne de pièces
                //on initialise les pièces
                echiquier.chercherCase(rangee, 0).setPiece(new Tour(couleur, echiquier));
                echiquier.chercherCase(rangee, 1).setPiece(new Cavalier(couleur, echiquier));
                echiquier.chercherCase(rangee, 2).setPiece(new Fou(couleur, echiquier));
                echiquier.chercherCase(rangee, 3).setPiece(new Reine(couleur, echiquier));
                echiquier.chercherCase(rangee, 4).setPiece(new Roi(couleur, echiquier));
                echiquier.chercherCase(rangee, 5).setPiece(new Fou(couleur, echiquier));
                echiquier.chercherCase(rangee, 6).setPiece(new Cavalier(couleur, echiquier));
                echiquier.chercherCase(rangee, 7).setPiece(new Tour(couleur, echiquier));
            }
        }

        echec_c.avertirMajEchiquierObservateurs();
    }

    public void jouer(Case caseDepart, Case caseArrive) {
        if (caseDepart.getPiece().deplacementPossible(caseDepart, caseArrive)) {// si le déplacement est possible
            jouerPiece(caseDepart, caseArrive);
            joueurSuivant();
        } else if (caseDepart.getPiece().getType() == 5// si la pièce en déplacement est un roi
                && echiquier.roquePossible(caseDepart, caseArrive)) {// et que le roque est possible
            roquer(caseDepart, caseArrive);
            joueurSuivant();
        } else if (caseDepart.getPiece().getType() == 0//si la pièce est un pion
                && echiquier.priseEnPassantPossible(caseDepart, caseArrive)) {//et que la prise en passant est possible
            priseEnPassant(caseDepart, caseArrive);
            joueurSuivant();
        }
        if (echiquier.verifierEchecEtMat(joueurEnJeu)) {
            echec_c.avertirFinPartieAllObservateurs();
        }
    }

    public void joueurSuivant() {
        joueurEnJeu = joueurEnJeu == 0 ? 1 : 0;
    }

    public void jouerPiece(Case caseDepart, Case caseArrive) {
        caseArrive.setPiece(caseDepart.getPiece());
        caseDepart.setEnDeplacement(false);
        caseDepart.setPiece(null);
        caseArrive.getPiece().incrementeNbDeplacement();
        echec_c.avertirObservateurs(caseArrive);
        if (echiquier.verifierEchec()) {
            if(echiquier.verifierEchecEtMat(joueurEnJeu))
            echec_c.avertirEchecObservateurs();
        }
    }

    public void roquer(Case caseDepart, Case caseArrive) {
        int rangeeDepart = caseDepart.getRangee();
        int colonneDepart = caseDepart.getColonne();
        int colonneArrive = caseArrive.getColonne();
        jouerPiece(caseDepart, caseArrive);
        if (colonneDepart < colonneArrive) {
            Case caseTour = echiquier.chercherCase(rangeeDepart, colonneArrive + 1);
            echec_c.avertirEnDeplacementObservateurs(caseTour);
            jouerPiece(caseTour, echiquier.chercherCase(rangeeDepart, colonneArrive - 1));
        } else {
            Case caseTour = echiquier.chercherCase(rangeeDepart, colonneArrive - 2);
            echec_c.avertirEnDeplacementObservateurs(caseTour);
            jouerPiece(caseTour, echiquier.chercherCase(rangeeDepart, colonneArrive + 1));
        }
    }

    public void priseEnPassant(Case caseDepart, Case caseArrive) {
        Case caseMange = echiquier.chercherCase(caseDepart.getRangee(), caseArrive.getColonne());
        echec_c.avertirEnDeplacementObservateurs(caseMange);
        jouerPiece(caseMange, caseArrive);
        jouerPiece(caseDepart, caseArrive);
    }
}
