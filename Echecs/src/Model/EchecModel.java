/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Class.Case;
import Class.Cavalier;
import Class.Fou;
import Class.Pion;
import Class.Reine;
import Class.Roi;
import Class.Tour;
import View.Observateur;
import java.util.ArrayList;

/**
 *
 * @author vmachu
 */
public class EchecModel {

    private static Case[][] echiquier;
    private static final int TAILLE = 8;
    private boolean finPartie;
    private ArrayList<Observateur> observateurs;
    private int joueurEnJeu;

    public EchecModel() {
        // Initialisation de la liste des observateurs
        this.observateurs = new ArrayList<Observateur>();// Initialisation de la liste des observateurs
        this.joueurEnJeu = 0;

        this.echiquier = new Case[TAILLE][TAILLE];
        for (int rangee = 0; rangee <= TAILLE - 1; rangee++) {
            for (int colonne = 0; colonne <= TAILLE - 1; colonne++) {
                echiquier[rangee][colonne] = new Case(rangee, colonne);
            }
        }
    }

    public static Case[][] getEchiquier() {
        return echiquier;
    }

    public int getJoueurEnJeu() {
        return joueurEnJeu;
    }

    public void ajouterObservateur(Observateur observateur) {
        this.observateurs.add(observateur);
    }

    public void retirerObservateur(Observateur observateur) {
        this.observateurs.remove(observateur);
    }

    public void avertirObservateurs(Case caseReferente) {
        for (Observateur o : this.observateurs) {
            o.avertir(caseReferente);
        }
    }

    public void avertirEnDeplacementObservateurs(Case caseReferente) {
        for (Observateur o : this.observateurs) {
            o.avertirEnDeplacement(caseReferente);
        }
    }

    public void avertirNouvellePartieObservateurs() {
        for (Observateur o : this.observateurs) {
            o.avertirNouvellePartie();
        }
    }

    public void avertirFinPartieAllObservateurs() {
        for (Observateur o : this.observateurs) {
            o.avertirFinPartie();
        }
    }

    public void nouvellePartie() {

        joueurEnJeu = 0;
        int couleur = 1;

        for (int rangee = 0; rangee <= TAILLE - 1; rangee++) {
            for (int colonne = 0; colonne <= TAILLE - 1; colonne++) {
                echiquier[rangee][colonne].setEnDeplacement(false);
                echiquier[rangee][colonne].setPiece(null);
                if (rangee == 1 || rangee == 6) {
                    if (rangee == 6) {
                        couleur = 0;
                    }
                    echiquier[rangee][colonne].setPiece(new Pion(couleur, this));
                }
            }
            if (rangee == 0 || rangee == 7) {
                echiquier[rangee][0].setPiece(new Tour(couleur, this));
                echiquier[rangee][1].setPiece(new Cavalier(couleur, this));
                echiquier[rangee][2].setPiece(new Fou(couleur, this));
                echiquier[rangee][3].setPiece(new Reine(couleur, this));
                echiquier[rangee][4].setPiece(new Roi(couleur, this));
                echiquier[rangee][5].setPiece(new Fou(couleur, this));
                echiquier[rangee][6].setPiece(new Cavalier(couleur, this));
                echiquier[rangee][7].setPiece(new Tour(couleur, this));
            }
        }

        finPartie = false; // Ce n'est pas la fin de la partie

        avertirNouvellePartieObservateurs();
    }

    public Case chercherCase(int rangee, int colonne) {
        return echiquier[rangee][colonne];
    }

    public Case rechercherPieceEnDeplacement() {
        for (int rangee = 0; rangee <= TAILLE - 1; rangee++) {
            for (int colonne = 0; colonne <= TAILLE - 1; colonne++) {
                if (echiquier[rangee][colonne].estEnDeplacement()) {
                    return echiquier[rangee][colonne];
                }
            }
        }
        return null;
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
        avertirObservateurs(caseArrive);
    }

    private boolean roquePossible(Case caseDepart, Case caseArrive) {
        int rangeeDepart = caseDepart.getRangee();
        int colonneDepart = caseDepart.getColonne();
        int colonneArrive = caseArrive.getColonne();
        if (!caseArrive.estOccupe()
                && caseDepart.getPiece().estEnPositionInitiale()
                && caseArrive.getRangee() == rangeeDepart) {
            if (colonneArrive == colonneDepart + 2
                    && chercherCase(rangeeDepart, colonneArrive + 1).getPiece().estEnPositionInitiale()
                    && !chercherCase(rangeeDepart, colonneArrive - 1).estOccupe()) {
                return true;
            } else if (colonneArrive == colonneDepart - 3
                    && chercherCase(rangeeDepart, colonneArrive - 1).getPiece().estEnPositionInitiale()
                    && !chercherCase(rangeeDepart, colonneArrive + 1).estOccupe()
                    && !chercherCase(rangeeDepart, colonneArrive + 2).estOccupe()) {
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
            Case caseTour = chercherCase(rangeeDepart, colonneArrive + 1);
            avertirEnDeplacementObservateurs(caseTour);
            jouerPiece(caseTour, chercherCase(rangeeDepart, colonneArrive - 1));
        } else {
            Case caseTour = chercherCase(rangeeDepart, colonneArrive - 1);
            avertirEnDeplacementObservateurs(caseTour);
            jouerPiece(caseTour, chercherCase(rangeeDepart, colonneArrive + 1));
        }
    }

    private boolean priseEnPassantPossible(Case caseDepart, Case caseArrive) {
        int rangeeDepart = caseDepart.getRangee();
        int rangeeArrive = caseArrive.getRangee();
        int colonneDepart = caseDepart.getColonne();
        int colonneArrive = caseArrive.getColonne();
        Case caseMange = chercherCase(rangeeDepart, colonneArrive);
        return (rangeeArrive == rangeeDepart - 1
                || rangeeArrive == rangeeDepart + 1)
                && (colonneArrive == colonneDepart - 1
                || colonneArrive == colonneDepart + 1)
                && caseMange.getPiece().getType() == 0
                && caseMange.getPiece().getNbDeplacement() == 1;
    }

    private void priseEnPassant(Case caseDepart, Case caseArrive) {
        Case caseMange = chercherCase(caseDepart.getRangee(), caseArrive.getColonne());
        avertirEnDeplacementObservateurs(caseMange);
        jouerPiece(caseMange, caseArrive);
        jouerPiece(caseDepart, caseArrive);
    }

}
