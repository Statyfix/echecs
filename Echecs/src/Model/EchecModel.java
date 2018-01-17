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

    public EchecModel() {
        // Initialisation de la liste des observateurs
        this.observateurs = new ArrayList<Observateur>();// Initialisation de la liste des observateurs

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

        int couleur = 1;

        for (int rangee = 0; rangee <= TAILLE - 1; rangee++) {
            for (int colonne = 0; colonne <= TAILLE - 1; colonne++) {
                echiquier[rangee][colonne].setEtat(0);
                echiquier[rangee][colonne].setPiece(null);
                if (rangee == 1 || rangee == 6) {
                    if (rangee == 6) {
                        couleur = 0;
                    }
                    echiquier[rangee][colonne].setPiece(new Pion(couleur));
                }
            }
            if (rangee == 0 || rangee == 7) {
                echiquier[rangee][0].setPiece(new Tour(couleur));
                echiquier[rangee][1].setPiece(new Cavalier(couleur));
                echiquier[rangee][2].setPiece(new Fou(couleur));
                echiquier[rangee][3].setPiece(new Reine(couleur));
                echiquier[rangee][4].setPiece(new Roi(couleur));
                echiquier[rangee][5].setPiece(new Fou(couleur));
                echiquier[rangee][6].setPiece(new Cavalier(couleur));
                echiquier[rangee][7].setPiece(new Tour(couleur));
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

    public void jouer(Case caseReferente, Case caseEnDeplacement) {
        caseReferente.setPiece(caseEnDeplacement.getPiece());
        caseEnDeplacement.setEtat(0);
        caseEnDeplacement.setPiece(null);
        avertirObservateurs(caseReferente);
    }

}
