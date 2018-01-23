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
import View.Observateur;
import java.util.ArrayList;

/**
 *
 * @author vmachu
 */
public class EchecModel {

    private final EchecController echec_c;
    private final Echiquier echiquier;
    private boolean finPartie;
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

        for (int rangee = 0; rangee <= TAILLE - 1; rangee++) {
            for (int colonne = 0; colonne <= TAILLE - 1; colonne++) {
                echiquier.chercherCase(rangee, colonne).setEnDeplacement(false);
                echiquier.chercherCase(rangee, colonne).setPiece(null);
                if (rangee == 1 || rangee == 6) {
                    if (rangee == 6) {
                        couleur = 0;
                    }
                    echiquier.chercherCase(rangee, colonne).setPiece(new Pion(couleur, echiquier));
                }
            }
            if (rangee == 0 || rangee == 7) {
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

        finPartie = false; // Ce n'est pas la fin de la partie

        echec_c.avertirMajEchiquierObservateurs();
    }

    public void jouer(Case caseDepart, Case caseArrive) {
        if (caseDepart.getPiece().deplacementPossible(caseDepart, caseArrive)) {
            jouerPiece(caseDepart, caseArrive);
            joueurSuivant();
        } else if (caseDepart.getPiece().getType() == 5
                && roquePossible(caseDepart, caseArrive)) {
            roquer(caseDepart, caseArrive);
            joueurSuivant();
        } else if (caseDepart.getPiece().getType() == 0
                && priseEnPassantPossible(caseDepart, caseArrive)) {
            priseEnPassant(caseDepart, caseArrive);
            joueurSuivant();
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
    }

    private boolean roquePossible(Case caseDepart, Case caseArrive) {
        int rangeeDepart = caseDepart.getRangee();
        int colonneDepart = caseDepart.getColonne();
        int colonneArrive = caseArrive.getColonne();
        if (!caseArrive.estOccupe()
                && caseDepart.getPiece().estEnPositionInitiale()
                && caseArrive.getRangee() == rangeeDepart) {
            if (colonneArrive == colonneDepart + 2
                    && echiquier.chercherCase(rangeeDepart, colonneArrive + 1).getPiece().estEnPositionInitiale()
                    && !echiquier.chercherCase(rangeeDepart, colonneArrive - 1).estOccupe()) {
                return true;
            } else if (colonneArrive == colonneDepart - 3
                    && echiquier.chercherCase(rangeeDepart, colonneArrive - 1).getPiece().estEnPositionInitiale()
                    && !echiquier.chercherCase(rangeeDepart, colonneArrive + 1).estOccupe()
                    && !echiquier.chercherCase(rangeeDepart, colonneArrive + 2).estOccupe()) {
                return true;
            }
        }
        return false;
    }

    private void roquer(Case caseDepart, Case caseArrive) {
        int rangeeDepart = caseDepart.getRangee();
        int colonneDepart = caseDepart.getColonne();
        int colonneArrive = caseArrive.getColonne();
        jouerPiece(caseDepart, caseArrive);
        if (colonneDepart < colonneArrive) {
            Case caseTour = echiquier.chercherCase(rangeeDepart, colonneArrive + 1);
            echec_c.avertirEnDeplacementObservateurs(caseTour);
            jouerPiece(caseTour, echiquier.chercherCase(rangeeDepart, colonneArrive - 1));
        } else {
            Case caseTour = echiquier.chercherCase(rangeeDepart, colonneArrive - 1);
            echec_c.avertirEnDeplacementObservateurs(caseTour);
            jouerPiece(caseTour, echiquier.chercherCase(rangeeDepart, colonneArrive + 1));
        }
    }

    private boolean priseEnPassantPossible(Case caseDepart, Case caseArrive) {
        int rangeeDepart = caseDepart.getRangee();
        int rangeeArrive = caseArrive.getRangee();
        int colonneDepart = caseDepart.getColonne();
        int colonneArrive = caseArrive.getColonne();
        Case caseMange = echiquier.chercherCase(rangeeDepart, colonneArrive);
        return (rangeeArrive == rangeeDepart - 1
                || rangeeArrive == rangeeDepart + 1)
                && (colonneArrive == colonneDepart - 1
                || colonneArrive == colonneDepart + 1)
                && caseMange.getPiece().getType() == 0
                && caseMange.getPiece().getNbDeplacement() == 1;
    }

    private void priseEnPassant(Case caseDepart, Case caseArrive) {
        Case caseMange = echiquier.chercherCase(caseDepart.getRangee(), caseArrive.getColonne());
        echec_c.avertirEnDeplacementObservateurs(caseMange);
        jouerPiece(caseMange, caseArrive);
        jouerPiece(caseDepart, caseArrive);
    }

    public boolean[][] deplacementsPossible(Case caseReferente) {
        boolean[][] deplacementsPossible = new boolean[TAILLE][TAILLE];
        for (int rangee = 0; rangee < 8; rangee++) {
            for (int colonne = 0; colonne < 8; colonne++) {//pour chaque case de l'echiquier
                deplacementsPossible[rangee][colonne] = caseReferente.getPiece().deplacementPossible(caseReferente, echiquier.chercherCase(rangee, colonne));//on stock le resultat du test
            }
        }
        return deplacementsPossible;
    }

}
